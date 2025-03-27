package org.example.com.marjoz.apigateway.domain

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.joda.time.LocalDateTime
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import reactor.core.publisher.Mono
import java.time.Duration

@Configuration(proxyBeanMethods = false)
internal class ApiGatewayModuleConfiguration {

    @Bean
    internal fun routeLocator(routeLocatorBuilder: RouteLocatorBuilder, applicationContext: ApplicationContext) : RouteLocator {
        return routeLocatorBuilder.routes()
            .route { p -> p.path("/marjoz/account/**")
                            .filters { f -> f.rewritePath("/marjoz/account/(?<segment>.*)", "/\${segment}")
                            .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                            .circuitBreaker { config -> config.setName("accountCircuitBreaker")
                                                              .setFallbackUri("forward:/fallbacks/support") }
                    }.uri("lb://ACCOUNT")
            }
            .route { p -> p.path("/marjoz/loan/**")
                            .filters { f -> f.rewritePath("/marjoz/loan/(?<segment>.*)", "/\${segment}")
                            .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                            .retry { retryConfig -> retryConfig.setRetries(3)
                                                               .setMethods(HttpMethod.GET)
                                                               .setBackoff(Duration.ofMillis(100), Duration.ofMillis(1000), 2, true) }
                    }.uri("lb://LOAN")
            }
            .route { p -> p.path("/marjoz/card/**")
                    .filters { f -> f.rewritePath("/marjoz/card/(?<segment>.*)", "/\${segment}")
                            .addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
                            .requestRateLimiter { requestRateLimiter -> requestRateLimiter.setRateLimiter(redisRateLimiter(applicationContext))
                                                                                          .setKeyResolver(userKeyResolver()) }
                    }.uri("lb://CARD")
            }
            .build()
    }

    @Bean
    internal fun defaultCustomizer(): Customizer<ReactiveResilience4JCircuitBreakerFactory> {
        return Customizer<ReactiveResilience4JCircuitBreakerFactory> { factory ->
            factory.configureDefault { id ->
                Resilience4JConfigBuilder(id)
                    .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                    .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(4)).build())
                    .build()
            }
        }
    }

    @Bean
    internal fun redisRateLimiter(applicationContext: ApplicationContext): RedisRateLimiter {
        val redisRateLimiter = RedisRateLimiter(1, 1, 1)
        redisRateLimiter.setApplicationContext(applicationContext)
        return redisRateLimiter
    }

    @Bean
    internal fun userKeyResolver(): KeyResolver {
        return KeyResolver { exchange ->
            Mono.justOrEmpty(exchange.request.headers.getFirst("user"))
                .defaultIfEmpty("anonymous")
        }
    }
}