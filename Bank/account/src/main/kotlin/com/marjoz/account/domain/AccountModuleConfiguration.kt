package org.example.com.marjoz.account.domain

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
internal class AccountModuleConfiguration {

    @Bean
    internal fun accountFacade(accountRepository: AccountRepository,
                               customerRepository: CustomerRepository,
                               loanFeignClient: LoanFeignClient,
                               cardFeignClient: CardFeignClient,
                               streamBridge: StreamBridge) : AccountFacade {

        return AccountFacade(accountRepository, customerRepository, loanFeignClient, cardFeignClient, streamBridge)
    }

    @Bean
    internal fun loanFeignFallback() : LoanFeignFallback {
        return LoanFeignFallback()
    }

    @Bean
    internal fun cardFeignFallback() : CardFeignFallback {
        return CardFeignFallback()
    }
}