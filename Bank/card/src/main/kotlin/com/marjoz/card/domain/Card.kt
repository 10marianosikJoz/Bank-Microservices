package org.example.com.marjoz.card.domain

import jakarta.persistence.*

@Entity
@Table(name = "Cards")
internal data class Card(@Id
                         @GeneratedValue(strategy = GenerationType.IDENTITY)
                         val cardId : Long,
                         @Column(unique = true)
                         val customerEmail: String,
                         @Column(unique = true)
                         val cardNumber: String,
                         @Enumerated(EnumType.STRING)
                         val cardType: CardType,
                         val totalLimit: Int,
                         val availableAmount: Int) {

    internal constructor(customerEmail: String,
                         cardNumber: String,
                         cardType: CardType,
                         totalLimit: Int,
                         availableAmount: Int) : this(0, customerEmail, cardNumber, cardType, totalLimit, availableAmount)

    internal enum class CardType {
        DEBIT, CREDIT
    }
}