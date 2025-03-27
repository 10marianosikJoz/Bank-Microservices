package org.example.com.marjoz.card.domain.dto

import org.example.com.marjoz.card.domain.Card

internal fun CardRequestDto.toCardEntity() = Card(customerEmail = this.customerEmail,
                                                  cardNumber = this.cardNumber,
                                                  cardType = Card.CardType.valueOf(this.cardType),
                                                  totalLimit = this.totalLimit,
                                                  availableAmount = this.availableAmount)

internal fun Card.toCardResponseDto() = CardResponseDto(customerEmail = this.customerEmail,
                                                        cardNumber = this.cardNumber,
                                                        cardType = this.cardType.name,
                                                        totalLimit = this.totalLimit,
                                                        availableAmount = this.availableAmount)