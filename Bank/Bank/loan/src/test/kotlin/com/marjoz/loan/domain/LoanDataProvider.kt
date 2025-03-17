package com.marjoz.loan.domain

import org.example.com.marjoz.loan.domain.dto.LoanRequestDto

internal class LoanDataProvider {

    internal fun loanData(loanType: String) : LoanRequestDto {
        return LoanRequestDto(loanNumber = "1234",
                              customerEmail = "example@gmail.com",
                              loanType = loanType,
                              totalLoan = 1000,
                              amountPaid = 500
        )
    }
}