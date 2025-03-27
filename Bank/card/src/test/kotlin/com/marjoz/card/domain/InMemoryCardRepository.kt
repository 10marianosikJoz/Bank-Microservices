package com.marjoz.card.domain

import org.example.com.marjoz.card.domain.Card
import org.example.com.marjoz.card.domain.CardRepository

internal class InMemoryCardRepository : CardRepository {

    companion object {
        private val database = mutableMapOf<Long, Card>()
    }

    fun truncate() {
        database.clear()
    }

    override fun findByCustomerEmail(email: String): Card? {
        return database.values.find { it.customerEmail == email }
    }

    override fun findByCardNumber(cardNumber: String): Card? {
        return database.values.find { it.cardNumber == cardNumber }
    }

    override fun deleteById(cardId: Long) {
        database.remove(cardId)
    }

    override fun save(card: Card): Card {
        database[card.cardId] = card
        return card
    }
}