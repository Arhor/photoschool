package by.arhor.psra.config;

import java.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import scala.collection.JavaConverters._

object AuthorizationServerConfig {
  private val CLIENT: String              = "giftCertificates"
  private val SECRET: String              = "absolutelySecret"
  private val GRANT_TYPES: Array[String]  = Array("password", "refresh_token")
  private val SCOPES: Array[String]       = Array("TRUSTED")
  private val ACCESS_TOKEN_VALIDITY: Int  = 900
  private val REFRESH_TOKEN_VALIDITY: Int = 3600
}

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig @Autowired() (

    @Qualifier("userServiceImpl")
    private val service: UserDetailsService,
    private val manager: AuthenticationManager,
    private val encoder: PasswordEncoder,
    private val tokenEnhancer: TokenEnhancer,
    private val tokenStore: TokenStore,
    private val tokenConverter: JwtAccessTokenConverter

  ) extends AuthorizationServerConfigurerAdapter {

  import AuthorizationServerConfig._

  override def configure(security: AuthorizationServerSecurityConfigurer): Unit = security
    .tokenKeyAccess("permitAll()")
    .checkTokenAccess("isAuthenticated()")

  @throws(classOf[Exception])
  override def configure(clients: ClientDetailsServiceConfigurer): Unit = clients
    .inMemory()
    .withClient(CLIENT)
    .secret(encoder.encode(SECRET))
    .scopes(SCOPES: _*)
    .authorizedGrantTypes(GRANT_TYPES: _*)
    .autoApprove(true)
    .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY)
    .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY)


  override def configure(endpoints: AuthorizationServerEndpointsConfigurer): Unit = {
    val tokenEnhancerChain = new TokenEnhancerChain()

    tokenEnhancerChain.setTokenEnhancers(List(tokenEnhancer, tokenConverter).asJava)

    endpoints
      .tokenStore(tokenStore)
      .tokenEnhancer(tokenEnhancerChain)
      .authenticationManager(manager)
      .userDetailsService(service)
  }

}
