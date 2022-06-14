package com.example.marketback.config;

//import com.example.marketback.config.jwt.JwtAuthenticationFilter;
//import com.example.marketback.config.jwt.JwtAuthorizationFilter;
//import com.example.marketback.config.auth.PrincipalOauth2UserService;
import com.example.marketback.config.auth.PrincipalOauth2UserService;
import com.example.marketback.filter.CustomAuthenticationFilter;
import com.example.marketback.filter.CustomAuthorizationFilter;
import com.example.marketback.oauth2.OAuth2AuthenticationSuccessHandler;
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
    @Autowired
    private PrincipalOauth2UserService principalOauth2UserService;

    //private final CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
//
//    @Autowired
//    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
//
//    @Bean
//    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
//        return new HttpCookieOAuth2AuthorizationRequestRepository();
//    }

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
                .antMatchers("/member/test1/test").access("hasRole('ROLE_MANAGER')")
                .antMatchers("/member/test2").access("hasRole('ROLE_USER')")
                .anyRequest().authenticated();

        http
                .oauth2Login()
//                .authorizationEndpoint()
//                .baseUri("/oauth2/authorize")
//                .authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .userInfoEndpoint() // 로그인 성공 후 사용자정보를 가져온다.
                .userService(principalOauth2UserService) // 사용자 정보를 처리할 때 사용한다.
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler);
                // oauth2 로그인에 성공하면, 유저 데이터를 가지고 우리가 생성한 principalOauth2UserService 에서 처리하겠다.
                // 그 후 login-success 로 이동해라

    }

}
