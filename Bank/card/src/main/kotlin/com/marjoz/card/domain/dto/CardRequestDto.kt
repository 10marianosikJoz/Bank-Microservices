package org.example.com.marjoz.card.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero

@Schema(name = "Card", description = "Schema to hold request information.")
data class CardRequestDto(@NotEmpty(message = "Email can not be a null or empty.")
                          @Email(message = "Email is not valid.")
                          @Schema(description = "Customer email.", example = "example@gmail.com")
                          val customerEmail: String,

                          @NotEmpty(message = "Card Number can not be a null or empty.")
                          @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
                          @Schema(description = "Card Number of the customer.", example = "100646930341")
                          val cardNumber: String,

                          @NotEmpty(message = "CardType can not be a null or empty.")
                          @Schema(description = "Type of the card.", example = "CREDIT")
                          val cardType: String,

                          @Positive(message = "Total card limit should be greater than zero.")
                          @Schema(description = "Total amount limit available against a card.", example = "100000")
                          val totalLimit: Int,

                          @PositiveOrZero(message = "Total available amount should be equal or greater than zero.")
                          @Schema(description = "Total available amount against a card.", example = "90000")
                          val availableAmount: Int)