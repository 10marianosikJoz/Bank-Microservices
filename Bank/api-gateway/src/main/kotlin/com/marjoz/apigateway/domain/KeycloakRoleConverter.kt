package org.example.com.marjoz.apigateway.domain

import org.springframework.core.convert.converter.Converter
import org.springframework.security. core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt

internal class KeycloakRoleConverter : Converter<Jwt, Collection<GrantedAuthority>>{

    override fun convert(source: Jwt): Collection<GrantedAuthority> {
        val roles = (source.claims["realm_access"] as? Map<*, *>)?.get("roles") as? List<*> ?: return emptyList()
        return roles.map { SimpleGrantedAuthority("ROLE_$it") }
    }
}