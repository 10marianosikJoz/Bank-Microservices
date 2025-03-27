package org.example.com.marjoz.card.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
internal class CardModuleConfiguration {

    @Bean
    internal fun cardFacade(cardRepository: CardRepository) : CardFacade {
        return CardFacade(cardRepository)
    }
}