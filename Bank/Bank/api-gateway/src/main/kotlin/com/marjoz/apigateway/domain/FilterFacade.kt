package org.example.com.marjoz.apigateway.domain

import org.springframework.http.HttpHeaders
import org.springframework.web.server.ServerWebExchange

internal class FilterFacade {

    companion object {
        internal const val CORRELATION_ID = "marjoz-correlation-id";
    }

    internal fun getCorrelationId(requestHeaders: HttpHeaders): String? {
        val correlationIdHeaders = requestHeaders[CORRELATION_ID]
        return correlationIdHeaders?.stream()?.findFirst()?.orElse(null)
    }

    internal fun setCorrelationId(exchange: ServerWebExchange, correlationId: String) : ServerWebExchange {
        return setRequestHeader(exchange, CORRELATION_ID, correlationId)
    }

    private fun setRequestHeader(exchange: ServerWebExchange, name: String, value: String) : ServerWebExchange {
        return exchange.mutate()
            .request(exchange.request.mutate().header(name, value).build())
            .build()
    }
}