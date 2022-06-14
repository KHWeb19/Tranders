package com.example.marketback.config;

//import com.example.marketback.config.jwt.JwtAuthenticationFilter;
//import com.example.marketback.config.jwt.JwtAuthorizationFilter;
import com.example.marketback.filter.CustomAuthenticationFilter;
import com.example.marketback.filter.CustomAuthorizationFilter;
import com.example.marketback.repository.member.MemberRepository;
//import com.example.marketback.service.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;

    private final MemberRepository memberRepository;

    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
        customAuthenticationFilter.setFilterProcessesUrl("/member/login");

        http.cors()
                .and().csrf().disable();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(customAuthenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(memberRepository), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/member/login").permitAll()
                .antMatchers("/member/register/**").permitAll()
                .antMatchers("/member/refreshToken").permitAll()
                .antMatchers("/login/oauth2/code/**").permitAll()
                .antMatchers("/member/auth/kakao/callback").permitAll()
                .antMatchers("/member/kakao/login").permitAll()
                .antMatchers("/member/test1/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_BOSS')")
                .antMatchers("/member/test2").access("hasRole('ROLE_MANAGER')")
//                .anyRequest().authenticated();
                .anyRequest().permitAll();

        /*http
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);*/

    }

}
