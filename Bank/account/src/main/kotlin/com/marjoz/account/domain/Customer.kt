package org.example.com.marjoz.account.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "customers")
internal data class Customer(@Id
                             @GeneratedValue(strategy = GenerationType.IDENTITY)
                             val customerId: Long,
                             val name: String,
                             @Column(unique = true)
                             val email: String,
                             val address: String) {

    internal constructor(name: String, email: String, address: String) : this(0, name, email, address)
}