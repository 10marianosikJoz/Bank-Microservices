package org.example.com.marjoz.account.domain

import org.example.com.marjoz.account.domain.dto.LoanResponseDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient("loan", fallback = LoanFeignFallback::class)
internal interface LoanFeignClient {

    @GetMapping("/api/v1/loans/fetch/{customerEmail}")
    fun fetchLoanDetails(@RequestParam customerEmail : String) : ResponseEntity<LoanResponseDto>
}