package org.example.com.marjoz.account.domain.dto

import com.marjoz.account.domain.dto.AccountResponseDto
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

@Schema(name = "CustomerDetails", description = "Schema to hold Customer, Account, Cards and Loans information")
data class CustomerInformationDto(@Schema(description = "Name of the customer.", example = "Marcin")
                                  @NotEmpty(message = "Name can not be a null or empty.")
                                  @Size(min = 5, max = 30, message = "The length of the customer name should be between 5 and 30.")
                                  val name: String,

                                  @Schema(description = "Email address of the customer.", example = "marcin@gmail.com")
                                  @NotEmpty(message = "Email address can not be a null or empty.")
                                  @Email(message = "Email address should be a valid value.")
                                  val email: String,

                                  @Schema(description = "Account details of the Customer.")
                                  val accountsDto: AccountResponseDto,

                                  @Schema(description = "Loans details of the Customer.")
                                  val loansDto: LoanResponseDto,

                                  @Schema(description = "Cards details of the Customer.")
                                  val cardsDto: CardResponseDto)