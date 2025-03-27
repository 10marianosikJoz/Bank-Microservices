package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.dto.toCustomerEntity
import org.junit.jupiter.api.AfterEach
import kotlin.test.Test

internal class AccountFacadeTest {

    private val accountModuleTestConfiguration = AccountModuleTestConfiguration()
    private val inMemoryAccountRepository = accountModuleTestConfiguration.inMemoryAccountRepository()
    private val inMemoryCustomerRepository = accountModuleTestConfiguration.inMemoryCustomerRepository()
    private val loanFeignClientMock = accountModuleTestConfiguration.loanFeignClientMock()
    private val cardFeignClientMock = accountModuleTestConfiguration.cardFeignClientMock()
    private val streamBridgeMock = accountModuleTestConfiguration.streamBridge()

    private val accountFacade = accountModuleTestConfiguration.accountFacade(inMemoryAccountRepository,
                                                                             inMemoryCustomerRepository,
                                                                             loanFeignClientMock,
                                                                             cardFeignClientMock,
                                                                             streamBridgeMock)

    private val dataProvider = DataProvider()

    @AfterEach
    internal fun tearDown() {
        inMemoryAccountRepository.truncate()
        inMemoryCustomerRepository.truncate()
    }

    @Test
    internal fun `should create account`() {
        //given
        val initialCustomer = dataProvider.customerRequestDto()

        //when
        val createdAccount = accountFacade.createAccount(initialCustomer)

        //then
        assert(inMemoryAccountRepository.findByAccountNumber(createdAccount.accountNumber) != null)
    }

    @Test
    internal fun `should fetch account details`() {
        //given
        val initialCustomer = dataProvider.customer()
        val initialAccount = dataProvider.account()

        inMemoryCustomerRepository.save(initialCustomer)
        inMemoryAccountRepository.save(initialAccount)

        //when
        val expectedAccount = accountFacade.fetchAccountDetails(initialCustomer.email)

        //then
        assert(expectedAccount.accountNumber == initialAccount.accountNumber)
    }

    @Test
    internal fun `should update account details`() {
        //given
        val initialCustomerRequestDto = dataProvider.customerRequestDto()
        val initialCustomer = initialCustomerRequestDto.toCustomerEntity()
        val initialAccount = dataProvider.account()

        val savedCustomer = inMemoryCustomerRepository.save(initialCustomer)
        inMemoryAccountRepository.save(initialAccount)

        //when
        accountFacade.updateAccountDetails(initialCustomerRequestDto, savedCustomer.customerId)

        //then
        assert(inMemoryCustomerRepository.findByEmail(savedCustomer.email)?.name == initialCustomer.name)
    }

    @Test
    internal fun `should fetch customer details`() {
        //given
        val initialCustomer = dataProvider.customer()
        val initialAccount = dataProvider.account()

        inMemoryCustomerRepository.save(initialCustomer)
        inMemoryAccountRepository.save(initialAccount)

        //when
        val expectedCustomer = accountFacade.fetchCustomerInformation(initialCustomer.email)

        //then
        assert(expectedCustomer.name == initialCustomer.name)
    }
}