package be.dylan.arbitrage_v1.il.config.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.*;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private static final String ROLE_PREFIX = "ROLE_";

    private static final String CLIENT_ID = "spring-app-arbitrage";

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.addAll(extractRealmRoles(jwt));
        authorities.addAll(extractClientRoles(jwt));

        return authorities;
    }

    private Collection<GrantedAuthority> extractRealmRoles(Jwt jwt) {

        Object realmAccessObj = jwt.getClaim("realm_access");

        if (!(realmAccessObj instanceof Map<?, ?> realmAccess)) {
            return Collections.emptyList();
        }

        Object rolesObj = realmAccess.get("roles");

        if (!(rolesObj instanceof Collection<?> roles)) {
            return Collections.emptyList();
        }

        return roles.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toSet());
    }

    private Collection<GrantedAuthority> extractClientRoles(Jwt jwt) {

        Object resourceAccessObj = jwt.getClaim("resource_access");

        if (!(resourceAccessObj instanceof Map<?, ?> resourceAccess)) {
            return Collections.emptyList();
        }

        Object clientObj = resourceAccess.get(CLIENT_ID);

        if (!(clientObj instanceof Map<?, ?> clientAccess)) {
            return Collections.emptyList();
        }

        Object rolesObj = clientAccess.get("roles");

        if (!(rolesObj instanceof Collection<?> roles)) {
            return Collections.emptyList();
        }

        return roles.stream()
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                .collect(Collectors.toSet());
    }
}
 