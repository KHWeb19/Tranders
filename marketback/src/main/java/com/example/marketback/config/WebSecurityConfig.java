package com.example.marketback.config;

//import com.example.marketback.config.jwt.JwtAuthenticationFilter;
//import com.example.marketback.config.jwt.JwtAuthorizationFilter;
//import com.example.marketback.config.auth.PrincipalOauth2UserService;
///import com.example.marketback.config.auth.PrincipalOauth2UserService;
import com.example.marketback.filter.CustomAuthenticationFilter;
import com.example.marketback.filter.CustomAuthorizationFilter;
//import com.example.marketback.oauth2.OAuth2AuthenticationSuccessHandler;
import com.example.marketback.oauth2.CustomOAuth2UserService;
import com.example.marketback.oauth2.OAuth2SuccessHandler;
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


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CorsFilter corsFilter;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2SuccessHandler oAuth2AuthenticationSuccessHandler;

    /*@Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }*/

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
                .antMatchers("/oauth2/**").permitAll()
                .antMatchers("/member/test1/**").access("hasRole('ROLE_MANAGER') or hasRole('ROLE_BOSS')")
                .antMatchers("/member/test2").access("hasRole('ROLE_MANAGER')")
                .anyRequest().authenticated();
                //.anyRequest().permitAll();

        http
                .oauth2Login()
                .authorizationEndpoint()
                //.authorizationRequestRepository(cookieAuthorizationRequestRepository())
                .baseUri("/oauth2/authorization")
                .and()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/**")
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)  // 사용자 정보를 처리할 때 사용한다.
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler);
                // oauth2 로그인에 성공하면, 유저 데이터를 가지고 우리가 생성한 principalOauth2UserService 에서 처리하겠다.
                // 그 후 login-success 로 이동해라

    }

}
