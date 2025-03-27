package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.LoanFeignClient
import org.example.com.marjoz.account.domain.dto.LoanResponseDto
import org.springframework.http.ResponseEntity

internal class LoanFeignClientMock : LoanFeignClient {

    override fun fetchLoanDetails(customerEmail: String): ResponseEntity<LoanResponseDto> {
        return ResponseEntity.ok(LoanResponseDto("john@gmail.com", "22", "PERSONAL", 200))
    }
}