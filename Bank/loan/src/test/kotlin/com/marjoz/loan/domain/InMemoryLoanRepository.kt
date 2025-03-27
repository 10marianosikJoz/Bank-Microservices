package com.marjoz.loan.domain

import org.example.com.marjoz.loan.domain.Loan
import org.example.com.marjoz.loan.domain.LoanRepository

internal class InMemoryLoanRepository : LoanRepository {

    companion object {
        private val database = mutableMapOf<Long, Loan>()
    }

    internal fun truncate() {
        database.clear()
    }

    override fun findByCustomerEmail(email: String): Loan? {
        return database.values.find { it.customerEmail == email }
    }

    override fun findByLoanNumber(loanNumber: String): Loan? {
        return database.values.find { it.loanNumber == loanNumber }
    }

    override fun deleteById(loanId: Long) {
        database.remove(loanId)
    }

    override fun save(loan: Loan): Loan {
        database[loan.loanId] = loan
        return loan
    }
}