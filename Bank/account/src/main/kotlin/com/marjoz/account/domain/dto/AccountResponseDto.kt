package com.marjoz.account.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "AccountResponseDto", description = "Schema to hold response information.")
data class AccountResponseDto(val accountNumber: Long,
                              val accountType: String,
                              val customerAddress: String,
                              val customerName: String,
                              val customerEmail: String)