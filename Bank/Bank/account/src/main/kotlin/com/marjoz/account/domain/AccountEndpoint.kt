package org.example.com.marjoz.account.domain

import com.marjoz.account.domain.dto.AccountRequestDto
import com.marjoz.account.domain.dto.AccountResponseDto
import org.example.com.marjoz.account.domain.dto.CustomerRequestDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.example.com.marjoz.account.domain.dto.CustomerInformationDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(name = "REST APIs for Account microservice.",
     description = "Available operations: CREATE, GET AND UPDATE.")

@RestController
@RequestMapping("/api/v1/accounts")
@Validated
internal class AccountEndpoint(private val accountFacade: AccountFacade) {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(AccountEndpoint::class.java)
    }

    @Operation(
        summary = "Create Card REST API.",
        description = "REST API to create account & customer."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AccountRequestDto::class)
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorDetail::class)
                )]
            )
        ]
    )
    @PostMapping("/create")
    fun createAccount(@Valid @RequestBody customerRequestDto: CustomerRequestDto) : ResponseEntity<AccountResponseDto> {
        val account = accountFacade.createAccount(customerRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(account)
    }

    @Operation(
        summary = "Get Account & Customer REST API.",
        description = "REST API to get Account & Customer based on email."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AccountRequestDto::class)
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorDetail::class)
                )]
            )
        ]
    )
    @GetMapping("/fetch/{email}")
    fun fetchAccountDetails(@PathVariable email : String) : ResponseEntity<AccountResponseDto> {
        val customerDto = accountFacade.fetchAccountDetails(email)
        return ResponseEntity.status(HttpStatus.OK)
                             .body(customerDto)
    }

    @Operation(
        summary = "Update Account & Customer REST API.",
        description = "REST API to update account & customer."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = AccountRequestDto::class)
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorDetail::class)
                )]
            )
        ]
    )
    @PutMapping("/update/{customerId}")
    fun updateAccountDetails(@Valid @RequestBody customerRequestDto: CustomerRequestDto, @PathVariable customerId : Long) : ResponseEntity<AccountResponseDto> {
        accountFacade.updateAccountDetails(customerRequestDto, customerId)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Operation(
        summary = "Get Customer details REST API.",
        description = "REST API to fetch Customer details based on a email."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CustomerInformationDto::class)
                )]
            ),
            ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = ErrorDetail::class)
                )]
            )
        ]
    )
    @GetMapping("/fetch")
    fun fetchCustomerDetails(@RequestHeader("marjoz-correlation-id") correlationId: String, @RequestParam email : String) : ResponseEntity<CustomerInformationDto> {
        LOGGER.info("Correlation id: $correlationId")
        val customerInformationDto = accountFacade.fetchCustomerInformation(email, correlationId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(customerInformationDto)
    }

    @Schema(name = "ErrorDetail",
            description = "Schema to hold error details information.")

    internal data class ErrorDetail(@Schema(description = "Error code representing the error happened.")
                                    val errorCode: HttpStatus,
                                    @Schema(description = "Error message representing the error happened.")
                                    val errorMessage: String)
}