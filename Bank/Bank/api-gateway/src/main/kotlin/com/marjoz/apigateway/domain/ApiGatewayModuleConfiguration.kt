package org.example.com.marjoz.apigateway.domain

import org.joda.time.LocalDateTime
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.time.Duration


@Configuration(proxyBeanMethods = false)
internal class ApiGatewayModuleConfiguration {

    @Bean
    internal fun routeLocator(routeLocatorBuilder: RouteLocatorBuilder) : RouteLocator {
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
                    }.uri("lb://CARD")
            }
            .build()
    }

    @Bean
    internal fun filterFacade() : FilterFacade {
        return FilterFacade()
    }

    @Order(1)
    @Bean
    internal fun requestTraceFilter(filterFacade: FilterFacade) : RequestTrackFilter {
        return RequestTrackFilter(filterFacade)
    }

    @Bean
    fun postGlobalFilter(filterFacade: FilterFacade): GlobalFilter {
        return GlobalFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    val requestHeaders: HttpHeaders = exchange.request.headers
                    val correlationId: String = filterFacade.getCorrelationId(requestHeaders).toString()
                    exchange.response.headers.add(FilterFacade.CORRELATION_ID, correlationId)
                })
        }
    }
}