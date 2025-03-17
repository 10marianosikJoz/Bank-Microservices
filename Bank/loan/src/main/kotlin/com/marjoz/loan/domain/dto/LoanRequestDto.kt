package org.example.com.marjoz.loan.domain.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.PositiveOrZero

@Schema(name = "LoanRequestDto", description = "Schema to hold request information.")
data class LoanRequestDto(@NotEmpty(message = "Email can not be a null or empty.")
                          @Email(message = "Email is not valid.")
                          @Schema(description = "Customer email.", example = "example@gmail.com")
                          val customerEmail: String,

                          @NotEmpty(message = "Loan number can not be null or empty.")
                          @Pattern(regexp="(^$|[0-9]{12})",message = "LoanNumber must be 12 digits.")
                          @Schema(description = "Loan number of the customer.", example = "548732457654")
                          val loanNumber: String,

                          @NotEmpty(message = "LoanType can not be a null or empty.")
                          @Schema(description = "Type of the loan.", example = "PERSONAL")
                          val loanType: String,

                          @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero.")
                          @Schema(description = "Total loan amount paid.", example = "1000")
                          val totalLoan: Int,

                          @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero.")
                          @Schema(description = "Total outstanding amount against a loan.", example = "99000")
                          val amountPaid: Int)