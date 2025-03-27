package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.Account
import org.example.com.marjoz.account.domain.AccountRepository

internal class InMemoryAccountRepository : AccountRepository {

    companion object {
        private val database = mutableMapOf<Long, Account>()
    }

    fun truncate() {
        database.clear()
    }

    override fun findByCustomerId(id: Long): Account? {
        return database.values.find { it.customerId == id }
    }

    override fun findByAccountNumber(accountNumber: Long): Account? {
        return database.values.find { it.accountNumber == accountNumber }
    }

    override fun save(account: Account): Account {
        database[account.accountNumber] = account
        return account
    }
}