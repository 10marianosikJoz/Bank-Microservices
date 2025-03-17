package org.example.com.marjoz.apigateway.domain

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.http.HttpHeaders
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import java.util.*

internal class RequestTrackFilter internal constructor(private val filterFacade: FilterFacade): GlobalFilter {

    companion object {
        private val LOGGER = LoggerFactory.getLogger(RequestTrackFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
        val requestHeaders = exchange!!.request.headers
        if (isCorrelationIdPresent(requestHeaders)) {
            LOGGER.debug("marjoz-correlation-id found in RequestTraceFilter : {}", filterFacade.getCorrelationId(requestHeaders))
        } else {
            val correlationID = generateCorrelationId()
            val newExchange = filterFacade.setCorrelationId(exchange, correlationID)
            LOGGER.debug("marjoz-correlation-id generated in RequestTraceFilter : {}", correlationID)
            return chain!!.filter(newExchange)
        }
        return chain!!.filter(exchange)
    }

    private fun isCorrelationIdPresent(requestHeaders: HttpHeaders) : Boolean {
        return filterFacade.getCorrelationId(requestHeaders) != null
    }

    private fun generateCorrelationId() : String {
        return UUID.randomUUID().toString();
    }
}