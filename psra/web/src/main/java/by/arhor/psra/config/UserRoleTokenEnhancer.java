package by.arhor.psra.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import static java.util.stream.Collectors.toSet;

public class UserRoleTokenEnhancer implements TokenEnhancer {
  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication auth) {
    final var scopes = auth
      .getUserAuthentication()
      .getAuthorities()
      .stream()
      .map(GrantedAuthority::getAuthority)
      .collect(toSet());

    ((DefaultOAuth2AccessToken) token).setScope(scopes);
    return token;
  }
}
