package org.example.com.marjoz.account.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal class SqlAccountRepository(private val accountJpaRepository: AccountJpaRepository) : AccountRepository {

    override fun save(account: Account) : Account {
        return accountJpaRepository.save(account)
    }

    override fun findByCustomerId(id: Long) : Account? {
        return accountJpaRepository.findByCustomerId(id)
    }
}

internal interface AccountRepository {

    fun findByCustomerId(id: Long) : Account?

    fun save(account: Account) : Account
}

internal interface AccountJpaRepository : JpaRepository<Account, Long> {

    fun findByCustomerId(id: Long) : Account?
}