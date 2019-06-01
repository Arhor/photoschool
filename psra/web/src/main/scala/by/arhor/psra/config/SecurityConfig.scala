package by.arhor.psra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired() (@Qualifier("userServiceImpl")
                                   private var service: UserDetailsService,
                                   private var encoder: PasswordEncoder) extends WebSecurityConfigurerAdapter {

  @Bean
  @throws(classOf[Exception])
  override protected def authenticationManager(): AuthenticationManager = super.authenticationManager()

  @throws(classOf[Exception])
  override def configure(http: HttpSecurity): Unit = http
    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    .and()
    .csrf().disable()
    .authorizeRequests()
    .antMatchers("/api/oauth/token").permitAll()
    .anyRequest().permitAll() // TODO: in dev purpose only!


  @throws(classOf[Exception])
  override protected def configure(auth: AuthenticationManagerBuilder): Unit = {
    super.configure(auth)
    auth.userDetailsService(service).passwordEncoder(encoder)
  }

}
