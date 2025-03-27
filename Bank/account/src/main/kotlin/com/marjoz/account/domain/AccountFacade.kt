package org.example.com.marjoz.account.domain

import com.marjoz.account.domain.dto.AccountResponseDto
import com.marjoz.account.domain.dto.toAccountResponseDto
import com.marjoz.account.domain.exception.AccountDomainException
import org.example.com.marjoz.account.domain.dto.*
import org.springframework.cloud.stream.function.StreamBridge

class AccountFacade internal constructor(private val accountRepository: AccountRepository,
                                         private val customerRepository: CustomerRepository,
                                         private val loanFeignClient: LoanFeignClient,
                                         private val cardFeignClient: CardFeignClient,
                                         private val streamBridge: StreamBridge) {

    fun createAccount(customerRequestDto: CustomerRequestDto) : AccountResponseDto {
        val savedCustomer = customerRepository.save(customerRequestDto.toCustomerEntity())
        val account = Account(savedCustomer.customerId, Account.AccountType.PERSONAL)

        val savedAccount =  accountRepository.save(account)

        publishNotification(savedAccount, savedCustomer)

        return savedAccount.toAccountResponseDto(savedCustomer.name, savedCustomer.email, savedCustomer.address)
    }

    fun fetchAccountDetails(email: String) : AccountResponseDto {
        val customer = customerRepository.findByEmail(email) ?: throw AccountDomainException("Customer with email: $email does not exist")
        val account = accountRepository.findByCustomerId(customer.customerId) ?: throw AccountDomainException("Account with customerId: ${customer.customerId} does not exist")

        return AccountResponseDto(account.accountNumber,
                                  account.accountType.name,
                                  customer.address,
                                  customer.name,
                                  customer.email)
    }

    fun updateAccountDetails(customerRequestDto: CustomerRequestDto, customerId : Long) {
        val fetchedAccount = accountRepository.findByCustomerId(customerId) ?: throw AccountDomainException("Account with customerId: $customerId does not exist")
        val account = Account(fetchedAccount.accountNumber,
                              customerId,
                              Account.AccountType.valueOf(fetchedAccount.accountType.name))

        accountRepository.save(account)

        val customer = customerRepository.findByEmail(customerRequestDto.email) ?: throw AccountDomainException("Customer with email: ${customerRequestDto.email} does not exist")
        val updatedCustomer = customer.copy(name = customerRequestDto.name)

        customerRepository.save(updatedCustomer)
    }

    fun fetchCustomerInformation(email: String) : CustomerInformationDto {
        val customer = customerRepository.findByEmail(email) ?: throw AccountDomainException("Customer with email: $email does not exist")
        val account = accountRepository.findByCustomerId(customer.customerId) ?: throw AccountDomainException("Account with customerId: ${customer.customerId} does not exist")

        val accountDto = account.toAccountResponseDto(customer.name, customer.email, customer.address)

        val loan = loanFeignClient.fetchLoanDetails(email).body ?: throw AccountDomainException("Loan associated with email: $email does not exist")
        val card = cardFeignClient.fetchCardDetails(email).body ?: throw AccountDomainException("Card associated with email: $email does not exist")

        return CustomerInformationDto(customer.name, customer.email, accountDto, loan, card)
    }

    private fun publishNotification(account: Account, customer: Customer) {
        val accountMessageDto = AccountMessageDto(account.accountNumber, customer.name, customer.email)

        streamBridge.send("publishNotification-out-0", accountMessageDto)
    }
}