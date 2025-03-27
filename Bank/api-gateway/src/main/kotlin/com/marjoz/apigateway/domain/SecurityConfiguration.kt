package org.example.com.marjoz.apigateway.domain

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono


@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
internal class SecurityConfiguration {

    @Bean
    internal fun securityFilterChain(serverHttpSecurity: ServerHttpSecurity): SecurityWebFilterChain {
        serverHttpSecurity
            .authorizeExchange { exchanges ->
                exchanges.pathMatchers(HttpMethod.GET).permitAll()
                    .pathMatchers("/marjoz/account/**").hasRole("ACCOUNT")
                    .pathMatchers("/marjoz/card/**").hasRole("CARD")
                    .pathMatchers("/marjoz/loan/**").hasRole("LOAN")
            }
            .oauth2ResourceServer { oAuth2ResourceServerSpec ->
                oAuth2ResourceServerSpec.jwt { jwtSpec ->
                    jwtSpec.jwtAuthenticationConverter(grantedAuthoritiesExtractor())
                }
            }
            .csrf { it.disable() }

        return serverHttpSecurity.build()
    }

    @Bean
    internal fun reactiveJwtDecoder(): ReactiveJwtDecoder {
        return NimbusReactiveJwtDecoder.withJwkSetUri("http://localhost:7080/realms/master/protocol/openid-connect/certs").build()
    }

    private fun grantedAuthoritiesExtractor(): Converter<Jwt, Mono<AbstractAuthenticationToken>> {
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(KeycloackRoleConverter())
        return ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter)
    }
}