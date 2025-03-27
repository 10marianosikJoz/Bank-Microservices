package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.CardFeignClient
import org.example.com.marjoz.account.domain.dto.CardResponseDto
import org.springframework.http.ResponseEntity

internal class CardFeignClientMock : CardFeignClient {

    override fun fetchCardDetails(customerEmail: String): ResponseEntity<CardResponseDto> {
        return ResponseEntity.ok(CardResponseDto("john@gmail.com", "22", "PERSONAL", 200, 50))
    }
}