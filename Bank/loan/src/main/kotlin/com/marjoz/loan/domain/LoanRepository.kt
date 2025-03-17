package org.example.com.marjoz.loan.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal class SqlLoanRepository(private val loanJpaRepository: LoanJpaRepository) : LoanRepository {

    override fun findByCustomerEmail(email: String): Loan? {
        return loanJpaRepository.findByCustomerEmail(email)
    }

    override fun findByLoanNumber(loanNumber: String): Loan? {
        return loanJpaRepository.findByLoanNumber(loanNumber)
    }

    override fun deleteById(loanId: Long) {
        return loanJpaRepository.deleteById(loanId)
    }

    override fun save(loan: Loan) : Loan {
        return loanJpaRepository.save(loan)
    }
}

internal interface LoanRepository {

    fun findByCustomerEmail(email: String) : Loan?

    fun findByLoanNumber(loanNumber: String): Loan?

    fun deleteById(loanId: Long)

    fun save(loan: Loan) : Loan
}

internal interface LoanJpaRepository: JpaRepository<Loan, Long> {

    fun findByCustomerEmail(email: String) : Loan?

    fun findByLoanNumber(loanNumber: String): Loan?
}