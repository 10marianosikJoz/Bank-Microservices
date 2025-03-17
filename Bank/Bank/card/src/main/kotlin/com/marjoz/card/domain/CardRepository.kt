package org.example.com.marjoz.card.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
internal class SqlCardRepository(private val cardJpaRepository: CardJpaRepository) : CardRepository {

    override fun findByCustomerEmail(email: String): Card? {
        return cardJpaRepository.findByCustomerEmail(email)
    }

    override fun findByCardNumber(cardNumber: String): Card? {
        return cardJpaRepository.findByCardNumber(cardNumber)
    }

    override fun deleteById(cardId: Long) {
        cardJpaRepository.deleteById(cardId)
    }

    override fun save(card: Card) : Card {
        return cardJpaRepository.save(card)
    }
}

internal interface CardRepository {

    fun findByCustomerEmail(email: String) : Card?

    fun findByCardNumber(cardNumber: String) : Card?

    fun deleteById(cardId : Long)

    fun save(card : Card) : Card
}

internal interface CardJpaRepository : JpaRepository<Card, Long> {

    fun findByCustomerEmail(customerEmail: String) : Card?

    fun findByCardNumber(cardNumber: String) : Card?
}