package org.example.com.marjoz.loan.domain.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(name = "LoanResponseDto", description = "Schema to hold response information.")
data class LoanResponseDto(val customerEmail: String,
                           val loanNumber: String,
                           val loanType: String,
                           val totalLoan: Int)