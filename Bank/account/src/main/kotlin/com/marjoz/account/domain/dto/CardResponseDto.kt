package org.example.com.marjoz.account.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "CardResponseDto", description = "Schema to hold response information.")
data class CardResponseDto(val customerEmail: String,
                           val cardNumber: String,
                           val cardType: String,
                           val totalLimit: Int,
                           val availableAmount: Int)