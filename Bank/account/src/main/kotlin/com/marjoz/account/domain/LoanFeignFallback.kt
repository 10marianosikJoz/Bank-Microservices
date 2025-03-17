package org.example.com.marjoz.account.domain

import org.example.com.marjoz.account.domain.dto.LoanResponseDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

internal class LoanFeignFallback : LoanFeignClient {

    override fun fetchLoanDetails(correlationId: String, customerEmail: String): ResponseEntity<LoanResponseDto> {
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanResponseDto("","", "", 1))
    }
}