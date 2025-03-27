package com.marjoz.notification.domain

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
internal class NotificationModuleConfiguration {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(NotificationModuleConfiguration::class.java)
    }

    @Bean
    fun email(): (AccountMessageDto) -> AccountMessageDto {
        return { accountMessageDto ->
            LOGGER.info("Sending email with the details : $accountMessageDto")
            accountMessageDto
        }
    }

    @Bean
    fun sms(): (AccountMessageDto) -> Long {
        return { accountMessageDto ->
            LOGGER.info("Sending sms with the details : $accountMessageDto")
            accountMessageDto.accountNumber
        }
    }
}