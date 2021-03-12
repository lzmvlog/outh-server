package top.lzmvlog.oauthserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年11月07日 22:21
 * @Description:
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 配置授权服务器的安全，意味着 /oauth/token 端点和 /oauth/authorize 端点都应该是安全的
     * 默认的设置覆盖了绝大多数需求，所以一般情况下不需要做任何事情
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 该处通过 ClientDetailsService 来配置注册到该授权服务器的客户端 clients信息
     * 注意：除非在下面的 configure(AuthorizationServerEndpointsConfigurer endpoints) 种指定
     * 了一个 AuthorizationManager,否则密码授权模式不可用
     * <p>
     * 至少要配置一个 client ，否则无法启动服务器
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        super.configure(clients);
        clients.inMemory()
                // client_id
                .withClient("client")
                // client_secret
                .secret(passwordEncoder.encode("client"))
                // 该 client 支持的授权模式 OAuth 的 client 在请求 code 时 值又该传递授权模式的参数 该处包含的授权模式才可以访问
                .authorizedGrantTypes("authorization_code", "implicit")
                // 该 client 分配的 access_token 的有效时间要少于刷新时间
                .accessTokenValiditySeconds(7200)
                // 该 client 分配的 access_token 的可刷新时间要多于有效时间
                .refreshTokenValiditySeconds(7200)
                // 重定向的 url
                .redirectUris("http://127.0.0.1:8090/login/oauth2/code/authorizationserver")
                .additionalInformation()
                // 该 client 可以访问资源服务器ID，每个资源服务器都有一个ID
                .resourceIds(ResourceServerConfig.RESOURCE_ID)
                // 对 client 进行鉴权
                .authorities("ROLE_CLIENT")
                // 该 client 可以访问的资源的范围，资源服务器可以依据该处定义的范围对 client 进行鉴权
                .scopes("profile", "email", "phone", "aaa")
                // 自动批准的范围（scope），自动批准的 scope 在批准页不需要显示，寄不需要用户确认批准。如果所有 scope 都自动批准 则不显示批准页
                .autoApprove("profile");
    }

    /***
     * 该方法时用来配置授权服务器特性的 （Authorization Server endpoints），主要是一些非安全的特性 比如 token 存储、token 自定义，授权模式等
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
    }
}
