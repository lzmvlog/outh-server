package top.lzmvlog.oauthserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ShaoJie zhang1591313226@163.com
 * @Date 2020年11月07日 23:13
 * @Description:
 */
@Slf4j
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("user")).roles("USER")
                .and().withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("SecurityConfiguration 中配置 HttpSecurity 对象执行");
        http.authorizeRequests().antMatchers("/").permitAll()
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and().formLogin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/favicon.ico");
    }
}
