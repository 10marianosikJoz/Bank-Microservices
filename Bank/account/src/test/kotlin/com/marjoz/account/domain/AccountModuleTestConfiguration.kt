package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.AccountFacade
import org.example.com.marjoz.account.domain.AccountModuleConfiguration
import org.example.com.marjoz.account.domain.CardFeignClient
import org.example.com.marjoz.account.domain.LoanFeignClient
import org.mockito.Mockito.mock
import org.springframework.cloud.stream.function.StreamBridge

internal class AccountModuleTestConfiguration {

    private val accountModuleConfiguration = AccountModuleConfiguration();

    internal fun accountFacade(inMemoryAccountRepository: InMemoryAccountRepository,
                               inMemoryCustomerRepository: InMemoryCustomerRepository,
                               loanFeignClientMock: LoanFeignClient,
                               cardFeignClientMock: CardFeignClient,
                               streamBridge: StreamBridge) : AccountFacade {

        return accountModuleConfiguration.accountFacade(inMemoryAccountRepository,
                                                        inMemoryCustomerRepository,
                                                        loanFeignClientMock,
                                                        cardFeignClientMock,
                                                        streamBridge)
    }

    internal fun inMemoryAccountRepository() : InMemoryAccountRepository {
        return InMemoryAccountRepository()
    }

    internal fun inMemoryCustomerRepository() : InMemoryCustomerRepository {
        return InMemoryCustomerRepository()
    }

    internal fun loanFeignClientMock() : LoanFeignClient {
        return LoanFeignClientMock()
    }

    internal fun cardFeignClientMock() : CardFeignClient {
        return CardFeignClientMock()
    }

    internal fun streamBridge() : StreamBridge {
        return mock(StreamBridge::class.java)
    }
}