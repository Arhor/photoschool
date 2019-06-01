package by.arhor.psra

import by.arhor.psra.config.UserRoleTokenEnhancer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.MessageSource
import org.springframework.context.annotation.{Bean, Primary}
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.provider.token.store.{JwtAccessTokenConverter, JwtTokenStore}
import org.springframework.security.oauth2.provider.token.{DefaultTokenServices, TokenEnhancer, TokenStore}

@SpringBootApplication
class WebApplication

object WebApplication extends App {

  SpringApplication.run(classOf[WebApplication])

  @Bean
  def messageSource(): MessageSource = {
    val messageSource = new ResourceBundleMessageSource()
    messageSource.setBasename("messages")
    messageSource
  }

  @Bean
  def tokenStore(converter: JwtAccessTokenConverter): TokenStore = new JwtTokenStore(converter)

  @Bean
  def accessTokenConverter(): JwtAccessTokenConverter = {
    val converter = new JwtAccessTokenConverter()
    converter.setSigningKey("key")
    converter
  }

  @Bean
  def passwordEncoder(): PasswordEncoder = new BCryptPasswordEncoder(5)

  @Bean
  @Primary
  def tokenServices(store: TokenStore): DefaultTokenServices = {
    val defaultTokenServices = new DefaultTokenServices()
    defaultTokenServices.setTokenStore(store)
    defaultTokenServices
  }

  @Bean
  def tokenEnhancer(): TokenEnhancer = new UserRoleTokenEnhancer()

}
