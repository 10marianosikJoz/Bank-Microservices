package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.Account
import org.example.com.marjoz.account.domain.Customer
import org.example.com.marjoz.account.domain.dto.CustomerRequestDto

internal class DataProvider {

    fun customerRequestDto() : CustomerRequestDto {
        return CustomerRequestDto("John", "john@gmail.com", "New York 44")
    }

    fun account(): Account {
        return Account(1, Account.AccountType.PERSONAL)
    }

    fun customer() : Customer {
        return Customer("John", "john@gmail.com", "NewYork 44")
    }
}