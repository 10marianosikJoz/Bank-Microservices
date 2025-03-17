package com.marjoz.loan.domain

import org.example.com.marjoz.loan.domain.LoanFacade
import org.example.com.marjoz.loan.domain.LoanModuleConfiguration

internal class LoanModuleTestConfiguration {

    private val loanModuleConfiguration = LoanModuleConfiguration()

    internal fun loanFacade(inMemoryLoanRepository: InMemoryLoanRepository) : LoanFacade {
        return loanModuleConfiguration.loanFacade(inMemoryLoanRepository)
    }

    internal fun inMemoryLoanRepository() : InMemoryLoanRepository {
        return InMemoryLoanRepository()
    }
}