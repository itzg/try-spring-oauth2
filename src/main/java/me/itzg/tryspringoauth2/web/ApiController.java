package me.itzg.tryspringoauth2.web;

import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
    /**
     *
     * @param principal for OAuth2 will be instance of {@link OidcUser}
     */
    @GetMapping("user")
    public String user(@AuthenticationPrincipal AuthenticatedPrincipal principal) {
        return principal.toString();
    }
}
