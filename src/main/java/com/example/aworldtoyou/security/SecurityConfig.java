package com.example.aworldtoyou.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/blog/**").hasRole("ADMIN")
                .antMatchers("/resources/**", "/static/**", "/css/**", "/*.css", "/templates/**", "/blocks/**").permitAll()
                .antMatchers("/", "/signUp", "/pagePrice", "/blog/user/{id}", "/botMessage", "/news").permitAll()
                .antMatchers("/images/**", "/*.jpg", "/*.png").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/success")
                .and()
                // logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
////        web.ignoring().antMatchers("#carouselExampleIndicators");
////        web.ignoring().antMatchers("/#carouselExampleIndicators");
////        web.ignoring().antMatchers("/*.css");
//        web.ignoring().antMatchers("/heder-mine.html");
//        web.ignoring().antMatchers("/photoSlider.html");
//        web.ignoring().antMatchers("/*.css");
//        web.ignoring().antMatchers("/*.jpg");
//        web.ignoring().antMatchers("/*.png");
//    }
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/users/**").hasAuthority("ADMIN")
//                .antMatchers("/signUp/**").permitAll()
//                .antMatchers("/").authenticated()
//                .antMatchers("/css/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .usernameParameter("login")
//                .defaultSuccessUrl("/")
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .rememberMe()
//                .rememberMeParameter("remember-me")
//                .tokenRepository(tokenRepository());
//        http.csrf().disable();
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
