package a.gleb.reactiveauthors.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
        return serverHttpSecurity
                .csrf().disable()
                .formLogin().and()
                .httpBasic().disable()
                .authorizeExchange()
                .pathMatchers("/", "/login", "/favicon.ico", "/**", "/user").permitAll()
                .pathMatchers("/panel/administration", "/panel/administration/**").hasRole("ADMINISTRATOR")
                .anyExchange().authenticated()
                .and()
                .build();
    }
}
