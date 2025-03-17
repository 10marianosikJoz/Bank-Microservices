package org.example.com.marjoz.loan.domain

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.example.com.marjoz.loan.domain.dto.LoanRequestDto
import org.example.com.marjoz.loan.domain.dto.LoanResponseDto
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(name = "REST API for Loan microservice.",
    description = "Available operations: CREATE, GET, DELETE AND UPDATE.")

@RestController
@RequestMapping("/api/v1/loans")
@Validated
internal class LoanEndpoint(private val loanFacade: LoanFacade) {

    companion object {
        internal val LOGGER = LoggerFactory.getLogger(LoanEndpoint::class.java)
    }

    @Operation(
        summary = "Create Loan REST API.",
        description = "REST API to create loan."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "HTTP Status Created",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = LoanRequestDto::class)
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
    fun createLoan(@Valid @RequestBody loanRequestDto: LoanRequestDto) : ResponseEntity<LoanResponseDto> {
        val createdLoan = loanFacade.createLoan(loanRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdLoan)
    }


    @Operation(
        summary = "Get Loan details REST API.",
        description = "REST API to get loan details based on customer email."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status Created",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = LoanRequestDto::class)
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
    @GetMapping("/fetch/{customerEmail}")
    fun fetchLoanDetails(@RequestHeader("marjoz-correlation-id") correlationId: String, @PathVariable customerEmail : String) : ResponseEntity<LoanResponseDto> {
        LOGGER.info("Correlation id: $correlationId")
        val loanDto = loanFacade.fetchLoanDetails(customerEmail)
        return ResponseEntity.status(HttpStatus.OK)
                             .body(loanDto)
    }

    @Operation(
        summary = "Delete Loan REST API.",
        description = "REST API to delete loan based on customer email."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = LoanRequestDto::class)
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
    @DeleteMapping("/delete/{customerEmail}")
    fun deleteLoan(@PathVariable customerEmail: String) : ResponseEntity<LoanRequestDto> {
        loanFacade.deleteLoan(customerEmail)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Operation(
        summary = "Update Loan REST API.",
        description = "REST API to update loan."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = LoanRequestDto::class)
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
    @PutMapping("/update")
    fun updateLoanDetails(@Valid @RequestBody loanRequestDto: LoanRequestDto) : ResponseEntity<LoanRequestDto> {
        loanFacade.updateLoanDetails(loanRequestDto)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Schema(name = "ErrorDetail",
            description = "Schema to hold error details information.")

    internal data class ErrorDetail(@Schema(description = "Error code representing the error happened.")
                                    val errorCode: HttpStatus,
                                    @Schema(description = "Error message representing the error happened.")
                                    val errorMessage: String)
}