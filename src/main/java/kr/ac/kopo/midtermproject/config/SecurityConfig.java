package kr.ac.kopo.midtermproject.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Log4j2
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 패스워드 암호화하는 클래스
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        String userName = "user2";
//        String password = "1234";
//        UserDetails user = User.builder()
//                .username(userName)
//                .password(passwordEncoder().encode(password))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/css/**", "/js/**", "/About_Us_imgs/**", "/Logo&Menu_imgs/**", "/Shop_Product_imgs/**",
                    "/totoscoop/main_page", "/totoscoop/about_us", "/totoscoop/shop", "/totoscoop/contact", "/totoscoop/login", "/totoscoop/registration").permitAll();
            auth.requestMatchers("/board/list","/board/read","/community/list","/community/read","/community/register" ,"/community/modify","/community/delete", "/replies/**").hasRole("USER");
            auth.requestMatchers("/board/modify", "/board/delete", "board/register").hasRole("MANAGER");
        });

        httpSecurity.formLogin((formLogin) -> formLogin
                .loginPage("/totoscoop/login")
                .loginProcessingUrl("/loginProcess")
                .defaultSuccessUrl("/totoscoop/main_page"));
        httpSecurity.csrf().disable();
        httpSecurity.logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/totoscoop/main_page")
                .invalidateHttpSession(true));

        return httpSecurity.build();
    }
}
