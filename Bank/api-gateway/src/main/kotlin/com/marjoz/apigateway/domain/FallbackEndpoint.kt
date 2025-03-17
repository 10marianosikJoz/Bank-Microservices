package org.example.com.marjoz.apigateway.domain

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/fallbacks")
internal class FallbackEndpoint {

    @GetMapping("/support")
    internal fun contactSupport(): Mono<String> {
        return Mono.just("An error occurred. Please try after some time or contact support team.")
    }
}