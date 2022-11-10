package br.com.desafio.deveficiente.mercadolivre.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/api/v1/usuarios/**").hasAuthority("GRAVACAO")
//                .antMatchers(HttpMethod.PUT, "/api/v1/usuarios/**").hasAuthority("GRAVACAO")
//                .antMatchers(HttpMethod.DELETE, "/api/v1/usuarios/**").hasAuthority("GRAVACAO")
//                .antMatchers(HttpMethod.PATCH, "/api/v1/usuarios/**").hasAuthority("GRAVACAO")
//                .antMatchers(HttpMethod.GET, "/api/v1/usuarios/**").hasAuthority("LEITURA")
//                .anyRequest().authenticated()
//                .anyRequest().denyAll()
//                .and()
                .csrf().disable()
                .cors()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authenticationConverter = new JwtAuthenticationConverter();

        authenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            List<String> authorities = jwt.getClaimAsStringList("authorities");

            if (authorities == null) {
                authorities = Collections.emptyList();
            }

            return authorities
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        });

        return authenticationConverter;
    }


}
