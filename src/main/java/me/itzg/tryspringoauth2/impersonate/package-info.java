/**
 * Provides the ability to impersonate an authenticated principal for dev/integration testing. For example,
 *
 * <pre>
 * {@code
 *   @Configuration
 *   @EnableConfigurationProperties(ImpersonateProperties.class)
 *   public class SecurityConfiguration {
 *
 *     private final ImpersonateProperties impersonateProperties;
 *     public SecurityConfiguration(ImpersonateProperties impersonateProperties) {
 *       this.impersonateProperties = impersonateProperties;
 *     }
 *
 *     @Bean
 *     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 *       ...
 *       http.with(new ImpersonateConfigurer<>(), c -> c.setName(impersonateProperties.name()));
 *     }
 *   }
 * }
 * </pre>
 *
 * <p>
 *   With that, the property {@code impersonate.name} can be set to force impersonated authentication where the
 *   authenticated principal's name (same as the OIDC subject) will be one configured.
 * </p>
 */
package me.itzg.tryspringoauth2.impersonate;