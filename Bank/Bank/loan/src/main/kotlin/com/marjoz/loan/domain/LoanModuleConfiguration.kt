package org.example.com.marjoz.loan.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
internal class LoanModuleConfiguration {

    @Bean
    fun loanFacade(loanRepository: LoanRepository) : LoanFacade {
        return LoanFacade(loanRepository)
    }
}