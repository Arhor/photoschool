package by.arhor.psra.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.{EnableGlobalMethodSecurity, GlobalMethodSecurityConfiguration}
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class OAuthConfig extends GlobalMethodSecurityConfiguration {

    override protected def createExpressionHandler(): MethodSecurityExpressionHandler =
        new OAuth2MethodSecurityExpressionHandler()

}