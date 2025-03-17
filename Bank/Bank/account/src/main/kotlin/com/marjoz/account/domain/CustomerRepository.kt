package org.example.com.marjoz.account.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal class SqlCustomerRepository(private val customerJpaRepository: CustomerJpaRepository) : CustomerRepository {

    override fun save(customer: Customer): Customer {
        return customerJpaRepository.save(customer)
    }

    override fun findByEmail(email: String): Customer? {
        return customerJpaRepository.findByEmail(email)
    }
}

internal interface CustomerRepository {

    fun save(customer: Customer) : Customer

    fun findByEmail(email : String) : Customer?
}

internal interface CustomerJpaRepository : JpaRepository<Customer, Long> {

    fun findByEmail(email : String) : Customer?
}