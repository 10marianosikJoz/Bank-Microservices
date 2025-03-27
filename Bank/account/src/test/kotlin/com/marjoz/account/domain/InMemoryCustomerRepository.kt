package com.marjoz.account.domain

import org.example.com.marjoz.account.domain.Customer
import org.example.com.marjoz.account.domain.CustomerRepository

internal class InMemoryCustomerRepository : CustomerRepository {

    companion object {
        private val database = mutableMapOf<Long, Customer>()
    }

    fun truncate() {
        database.clear()
    }

    override fun save(customer: Customer): Customer {
        val customerWithId = customer.copy(customerId = 1)

        database[customerWithId.customerId] = customerWithId
        return customerWithId
    }

    override fun findByEmail(email: String): Customer? {
        return database.values.find { it.email == email }
    }
}