package org.example.com.marjoz.card.domain

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.example.com.marjoz.card.domain.dto.CardRequestDto
import org.example.com.marjoz.card.domain.dto.CardResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Tag(name = "CRUD REST API for Card microservice.",
    description = "Available operations: CREATE, GET, DELETE AND UPDATE.")

@RestController
@RequestMapping("/api/v1/cards")
@Validated
internal class CardEndpoint(private val cardFacade: CardFacade) {

    @Operation(
        summary = "Create Card REST API.",
        description = "REST API to create card."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "HTTP Status Created",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CardRequestDto::class)
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
    fun createCard(@Valid @RequestBody cardRequestDto: CardRequestDto) : ResponseEntity<CardResponseDto> {
        val createdCard = cardFacade.createCard(cardRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(createdCard)
    }

    @Operation(
        summary = "Get Card details REST API.",
        description = "REST API to get card details based on a customer email."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status Created",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CardRequestDto::class)
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
    fun fetchCardDetails(@PathVariable customerEmail : String) : ResponseEntity<CardResponseDto> {
        val loanDto = cardFacade.fetchCardDetails(customerEmail)
        return ResponseEntity.status(HttpStatus.OK)
            .body(loanDto)
    }

    @Operation(
        summary = "Delete Card REST API.",
        description = "REST API to delete card based on a mobile number."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CardRequestDto::class)
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
    @DeleteMapping("/delete/{mobileNumber}")
    fun deleteCard(@PathVariable mobileNumber: String) : ResponseEntity<CardRequestDto> {
        cardFacade.deleteCard(mobileNumber)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Operation(
        summary = "Update Card REST API",
        description = "REST API to update card."
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CardRequestDto::class)
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
    fun updateCardDetails(@Valid @RequestBody cardRequestDto: CardRequestDto) : ResponseEntity<CardRequestDto> {
        cardFacade.updateCardDetails(cardRequestDto)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Schema(name = "ErrorDetail",
        description = "Schema to hold error details information")

    internal data class ErrorDetail(@Schema(description = "Error code representing the error happened.")
                                    val errorCode: HttpStatus,
                                    @Schema(description = "Error message representing the error happened.")
                                    val errorMessage: String)
}