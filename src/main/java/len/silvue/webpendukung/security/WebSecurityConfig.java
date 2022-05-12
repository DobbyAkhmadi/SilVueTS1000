package len.silvue.webpendukung.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests((authorize) -> {
            authorize.antMatchers("/h2-console/**").permitAll();
            authorize.antMatchers("/login", "/resources/**").permitAll();
            authorize.antMatchers("/webjars/**").permitAll();
            authorize.antMatchers("/api/v1/ts1000/auto/updateConflictArs/**").permitAll();
            authorize.antMatchers("/api/v1/ts1000/auto/enableArs").permitAll();
        })
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .formLogin(formLoginConfigurer -> {
            formLoginConfigurer
                    .loginProcessingUrl("/login")
                    .loginPage("/login").permitAll()
                    .successForwardUrl("/")
                    .defaultSuccessUrl("/")
                    .failureUrl("/login?error");
        })
        .logout(logOutConfigurer -> {
            logOutConfigurer.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                .logoutSuccessUrl("/login/?logout")
                .permitAll();
        })
        .httpBasic()
                .and().csrf().ignoringAntMatchers("/h2-console/**",
                    "/api/v1/ts1000/actual/export-Actual",
                    "/api/v1/ts1000/auto/insertRouteManual",
                    "/api/v1/ts1000/auto/export-Route",
                    "/api/v1/ts1000/auto/export-Line",
                    "/api/v1/ts1000/auto/insertRouteAuto",
                    "/api/v1/ts1000/auto/insertRouteById",
                    "/api/v1/ts1000/auto/insertLineManual",
                    "/api/v1/ts1000/auto/insertLineAuto",
                    "/api/v1/ts1000/auto/updateBase",
                    "/api/v1/ts1000/auto/autoUpdate",
                    "/api/v1/ts1000/auto/updateLine",
                    "/api/v1/ts1000/auto/updateRute",
                    "/api/v1/ts1000/auto/updateEnable",
                    "/api/v1/ts1000/auto/updateArsEnable",
                    "/api/v1/ts1000/auto/updateArsBtn",
                    "/ars/send-ars-enable",
                    "/ars/send-ars-disable",
                    "/ars/checkArsConflict",
                    "/api/v1/ts1000/auto/updateRangeA",
                    "/api/v1/ts1000/auto/updateRangeB",
                    "/api/v1/ts1000/auto/updateIntervalPrintHours",
                    "/api/v1/ts1000/getAllConflict",
                    "/api/v1/ts1000/checkConflict",
                    "/api/v2/ts1000/getAllConflict",
                    "/ars/insert-schedule-command",
                    "/api/v1/ts1000/ars/enable",
                    "/api/v1/ts1000/ars/disable");
        http.headers().frameOptions().sameOrigin();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return TsPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
