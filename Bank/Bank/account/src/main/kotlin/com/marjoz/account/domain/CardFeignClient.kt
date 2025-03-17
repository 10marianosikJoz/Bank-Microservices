package org.example.com.marjoz.account.domain

import org.example.com.marjoz.account.domain.dto.CardResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("card", fallback = CardFeignFallback::class)
internal interface CardFeignClient {

    @GetMapping("/api/v1/cards/fetch/{customerEmail}")
    fun fetchCardDetails(@RequestHeader("marjoz-correlation-id") correlationId: String, @RequestParam customerEmail : String) : ResponseEntity<CardResponseDto>
}