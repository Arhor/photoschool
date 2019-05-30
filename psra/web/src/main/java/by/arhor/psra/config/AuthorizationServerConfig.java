package by.arhor.psra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String CLIENT = "giftCertificates";
    private static final String SECRET = "absolutelySecret";
    private static final String[] GRANT_TYPES = {"password", "refresh_token"};
    private static final String[] SCOPES = {"TRUSTED"};
    private static final int ACCESS_TOKEN_VALIDITY = 30; //900;
    private static final int REFRESH_TOKEN_VALIDITY = 3600;

    @Qualifier("userServiceImpl")
    private UserDetailsService service;
    private AuthenticationManager manager;
    private PasswordEncoder encoder;
    private TokenEnhancer tokenEnhancer;
    private TokenStore tokenStore;
    private JwtAccessTokenConverter tokenConverter;
    
    @Autowired
    public AuthorizationServerConfig(UserDetailsService service,
    								 AuthenticationManager manager,
                                     PasswordEncoder encoder,
                                     TokenEnhancer tokenEnhancer,
                                     TokenStore tokenStore,
                                     JwtAccessTokenConverter tokenConverter) {
        this.service = service;
        this.manager = manager;
        this.encoder = encoder;
        this.tokenEnhancer = tokenEnhancer;
        this.tokenStore = tokenStore;
        this.tokenConverter = tokenConverter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
               .withClient(CLIENT)
               .secret(encoder.encode(SECRET))
               .scopes(SCOPES)
               .authorizedGrantTypes(GRANT_TYPES)
               .autoApprove(true)
               .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY)
               .refreshTokenValiditySeconds(REFRESH_TOKEN_VALIDITY);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer, tokenConverter));
        endpoints.tokenStore(tokenStore)
                 .tokenEnhancer(tokenEnhancerChain)
                 .authenticationManager(manager)
                 .userDetailsService(service);
    }

}
