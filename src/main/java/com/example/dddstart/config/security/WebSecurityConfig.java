package com.example.dddstart.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	public static final String AUTHCOOKIENAME = "AUTH";

	private final DataSource dataSource;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> authz
				.anyRequest().authenticated()
			)
			.httpBasic(withDefaults());
		return http.build();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select member_id, password, 'true' from member where member_id = ?")
			.authoritiesByUsernameQuery("select member_id, authority from member_authorities where member_id = ?")
			.passwordEncoder(NoOpPasswordEncoder.getInstance())
		;
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
			"/vendor/**",
			"/api/**",
			"/images/**",
			"/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.securityContext()
			.securityContextRepository(new CookieSecurityContextRepository(userDetailsService()));
		http.requestCache().requestCache(new NullRequestCache());

		http.authorizeRequests()
			.antMatchers("/", "/home", "/categories/**", "/products/**").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated()
			.and()
			.formLogin() // login
			.loginPage("/login")
			.permitAll()
			.successHandler(new CustomAuthSuccessHandler())
			.and()
			.logout() // /login?logout
			.logoutUrl("/logout")
			.logoutSuccessUrl("/loggedOut")
			.deleteCookies(AUTHCOOKIENAME)
			.permitAll()
			.and()
			.csrf().disable()
		;
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() {
		return super.userDetailsService();
	}

}
