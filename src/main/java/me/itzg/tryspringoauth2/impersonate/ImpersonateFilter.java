package me.itzg.tryspringoauth2.impersonate;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class ImpersonateFilter extends OncePerRequestFilter {
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder
        .getContextHolderStrategy();

    private final SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();
    private final String name;


    public ImpersonateFilter(String name) {
        this.name = name;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
        FilterChain chain) throws ServletException, IOException {

        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(new PreAuthenticatedAuthenticationToken(new ImpersonatedPrincipal(name), "", List.of(
            new SimpleGrantedAuthority("USER"))));
        securityContextHolderStrategy.setContext(context);
        this.securityContextRepository.saveContext(context, request, response);

        chain.doFilter(request, response);
    }
}
