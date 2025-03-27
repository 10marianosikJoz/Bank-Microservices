package org.example.com.marjoz.account.domain

import jakarta.persistence.*

@Entity
@Table(name = "accounts")
internal data class Account(@Id
                            @GeneratedValue(strategy = GenerationType.IDENTITY)
                            val accountNumber: Long,
                            val customerId: Long,
                            @Enumerated(value = EnumType.STRING)
                            val accountType: AccountType) {

    internal constructor(customerId: Long,
                         accountType: AccountType) : this(0, customerId, accountType)

    internal enum class AccountType {
        BUSINESS, PERSONAL
    }
}