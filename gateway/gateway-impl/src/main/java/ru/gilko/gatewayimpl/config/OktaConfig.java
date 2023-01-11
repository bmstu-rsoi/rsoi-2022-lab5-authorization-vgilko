package ru.gilko.gatewayimpl.config;

import com.okta.spring.boot.oauth.Okta;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class OktaConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/authorize").permitAll()
                .antMatchers("/api/v1/callback").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer().jwt();

        http.cors();

        Okta.configureResourceServer401ResponseBody(http);
    }
}
