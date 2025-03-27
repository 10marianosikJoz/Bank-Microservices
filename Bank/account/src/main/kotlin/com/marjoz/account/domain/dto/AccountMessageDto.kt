package org.example.com.marjoz.account.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "AccountMessageDto", description = "Schema to hold response information.")
data class AccountMessageDto(val accountNumber : Long,
                             val name : String,
                             val email : String)