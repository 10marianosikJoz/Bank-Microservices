package org.example.com.marjoz.account.domain.dto

import com.marjoz.account.domain.dto.AccountResponseDto
import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "CustomerInformationDto", description = "Schema to hold Customer, Account, Cards and Loans information.")
data class CustomerInformationDto(val name: String,
                                  val email: String,
                                  val accountsDto: AccountResponseDto,
                                  val loansDto: LoanResponseDto?,
                                  val cardsDto: CardResponseDto?)