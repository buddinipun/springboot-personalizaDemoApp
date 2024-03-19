package com.smtaps.demoApi.config;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import io.micrometer.common.lang.NonNull;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken>{
	
	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = 
			new  JwtGrantedAuthoritiesConverter();
	
	@Value("${jwt.auth.converter.resource-id}")
	private String principleAttribute;
	@Value("${jwt.auth.converter.principle-attribute}")
	private final String resourceId = "swweb-rest-api";

	@Override
	public AbstractAuthenticationToken convert(@NonNull Jwt jwt) {
		// TODO Auto-generated method stub
		
		var authorities = Stream
				.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream()

				).collect(Collectors.toList());
		
		return new JwtAuthenticationToken(jwt, authorities, getPrincipleClaimName(jwt));
	}
	
	private String getPrincipleClaimName(Jwt jwt) {
		String claimName = JwtClaimNames.SUB;
		
		if (principleAttribute != null) {
			claimName = principleAttribute;
		}
		
		return jwt.getClaim(claimName);
	}

	private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {
		Map<String, Object> resourceAccess;
		Map<String, Object> resource;
		Collection<String> resourceRoles;

		if (jwt.getClaim("resource_access") == null) {
			return Set.of();
		}

		resourceAccess = jwt.getClaim("resource_access");

		if (resourceAccess.get("swweb-rest-api") == null) {
			return Set.of();
		}
		
		resource = (Map<String, Object>) resourceAccess.get(resourceId);
		resourceRoles = (Collection<String>) resource.get("roles");

		return resourceRoles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
				.collect(Collectors.toList());
	}

	

}
