package org.example.com.marjoz.account.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
internal class AccountModuleConfiguration {

    @Bean
    fun accountFacade(accountRepository: AccountRepository,
                      customerRepository: CustomerRepository,
                      loanFeignClient: LoanFeignClient,
                      cardFeignClient: CardFeignClient) : AccountFacade {

        return AccountFacade(accountRepository, customerRepository, loanFeignClient, cardFeignClient)
    }

    @Bean
    fun LoanFeignFallback() : LoanFeignFallback {
        return LoanFeignFallback()
    }

    @Bean
    fun CardFeignFallback() : CardFeignFallback {
        return CardFeignFallback()
    }
}