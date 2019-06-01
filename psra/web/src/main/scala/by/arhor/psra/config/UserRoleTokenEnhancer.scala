package by.arhor.psra.config

import java.util.stream.Collectors.toSet

import org.springframework.security.oauth2.common.{DefaultOAuth2AccessToken, OAuth2AccessToken}
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer

class UserRoleTokenEnhancer extends TokenEnhancer {

    override
    def enhance(token: OAuth2AccessToken, auth: OAuth2Authentication): OAuth2AccessToken = {
        val scopes = auth.getUserAuthentication
          .getAuthorities
          .stream
          .map[String] { _.getAuthority }
          .collect(toSet())

        token.asInstanceOf[DefaultOAuth2AccessToken].setScope(scopes)
        token
    }

}
