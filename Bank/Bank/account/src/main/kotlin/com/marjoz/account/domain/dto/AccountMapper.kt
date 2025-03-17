package com.marjoz.account.domain.dto

import org.example.com.marjoz.account.domain.Account

internal fun Account.toAccountDto() = AccountRequestDto(accountNumber = this.accountNumber,
                                                        accountType = this.accountType.name)

internal fun Account.toAccountResponseDto(customerName: String,
                                          customerEmail: String,
                                          customerAddress: String ) = AccountResponseDto(accountNumber = this.accountNumber,
                                                                                         accountType = this.accountType.name,
                                                                                         customerAddress = customerAddress,
                                                                                         customerName = customerName,
                                                                                         customerEmail = customerEmail)