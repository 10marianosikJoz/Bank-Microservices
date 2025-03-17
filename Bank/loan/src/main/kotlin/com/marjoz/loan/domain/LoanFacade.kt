package org.example.com.marjoz.loan.domain

import org.example.com.marjoz.loan.domain.dto.*
import org.example.com.marjoz.loan.domain.dto.toLoanEntity
import org.example.com.marjoz.loan.domain.exception.LoanDomainException

class LoanFacade internal constructor(private val loanRepository: LoanRepository) {

    fun createLoan(loanRequestDto: LoanRequestDto) : LoanResponseDto {
        return loanRepository.save(loanRequestDto.toLoanEntity()).toLoanResponseDto()
    }

    fun fetchLoanDetails(email: String) : LoanResponseDto {
        val loan = loanRepository.findByCustomerEmail(email) ?: throw LoanDomainException("Loan with customer email: $email does not exist.")
        return loan.toLoanResponseDto()
    }

    fun updateLoanDetails(loanRequestDto: LoanRequestDto) {
        val loan = loanRepository.findByLoanNumber(loanRequestDto.loanNumber) ?: throw LoanDomainException("Loan with loanNumber: ${loanRequestDto.loanNumber} does not exist.")
        val updatedLoan = loan.copy(customerEmail = loanRequestDto.customerEmail,
                                    loanType = Loan.LoanType.valueOf(loanRequestDto.loanType),
                                    totalLoan = loanRequestDto.totalLoan,
                                    amountPaid = loanRequestDto.amountPaid
        )

        loanRepository.save(updatedLoan)
    }

    fun deleteLoan(email: String) {
        val loan = loanRepository.findByCustomerEmail(email) ?: throw LoanDomainException("Loan with customer email: $email does not exist.")
        loanRepository.deleteById(loan.loanId)
    }
}