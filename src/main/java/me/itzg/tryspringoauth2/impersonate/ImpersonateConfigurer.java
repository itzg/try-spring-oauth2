package me.itzg.tryspringoauth2.impersonate;

import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class ImpersonateConfigurer<B extends HttpSecurityBuilder<B>>
    extends AbstractHttpConfigurer<HttpBasicConfigurer<B>, B> {

    private String name;

    public void withName(String name) {
        this.name = name;
    }

    @Override
    public void configure(B http) {
        http.addFilterBefore(new ImpersonateFilter(name), BasicAuthenticationFilter.class);
    }
}
