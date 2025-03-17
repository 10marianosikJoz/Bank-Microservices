package org.example.com.marjoz.account.domain

import org.example.com.marjoz.account.domain.dto.CardResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

internal class CardFeignFallback : CardFeignClient {

    override fun fetchCardDetails(correlationId: String, customerEmail: String): ResponseEntity<CardResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(CardResponseDto("","", "", 1, 1))

    }
}