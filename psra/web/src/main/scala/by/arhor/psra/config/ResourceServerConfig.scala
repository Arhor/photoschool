package by.arhor.psra.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.{EnableResourceServer, ResourceServerConfigurerAdapter}
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.DefaultTokenServices

@Configuration
@EnableResourceServer
class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private var tokenServices: DefaultTokenServices = _

    @throws(classOf[Exception])
    override def configure(http: HttpSecurity): Unit = http.authorizeRequests().anyRequest().permitAll()

    override def configure(config: ResourceServerSecurityConfigurer): Unit = config.tokenServices(tokenServices)

}
