package me.itzg.tryspringoauth2.impersonate;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("impersonate")
public record ImpersonateProperties(String name) {
    public boolean enabled() {
        return name != null;
    }
}
