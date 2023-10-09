// package dev.odaat.Config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.factory.PasswordEncoderFactories;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.DefaultSecurityFilterChain;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
 
// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     // @Bean
//     // public UserDetailsService userDetailsService() {
//     //     PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

//     //     // Define a sample user with username "user" and password "password"
//     //     UserDetails user = User.builder()
//     //             .username("user")
//     //             .password(encoder.encode("password"))
//     //             .roles("USER")
//     //             .build();

//     //     return new InMemoryUserDetailsManager(user);
//     // }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//     }

//     @Bean
//     public AuthenticationSuccessHandler successHandler() {
//         return new SimpleUrlAuthenticationSuccessHandler("/overview"); // Redirect to an authenticated route
//     }

//     @Configuration
//     public static class WebSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

//         @Autowired
//         private AuthenticationSuccessHandler successHandler;

//         @Override
//         public void configure(HttpSecurity http) throws Exception {
//             http
//                 .csrf().disable() // Disable CSRF for simplicity. You may want to enable it in a production application.
//                 .authorizeRequests()
//                     .requestMatchers("/login").permitAll() // Allow public access to "/index"
//                     .anyRequest().authenticated() // Require authentication for all other routes
//                     .and()
//                 .httpBasic() // Use basic authentication
//                     .and()
//                 .formLogin()
//                     .successHandler(successHandler) // Set the custom success handler
//                     .loginPage("/login") // Specify the custom login page URL
//                     .permitAll(); // Allow access to the login page
//         }
//     }
// }
