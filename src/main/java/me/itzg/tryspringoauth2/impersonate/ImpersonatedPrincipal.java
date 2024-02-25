package me.itzg.tryspringoauth2.impersonate;


import org.springframework.security.core.AuthenticatedPrincipal;

public class ImpersonatedPrincipal implements AuthenticatedPrincipal {
    private final String name;

    public ImpersonatedPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Impersonated[name=%s]", name);
    }

    @Override
    public String getName() {
        return name;
    }
}
