package club.isource.platform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import club.isource.platform.filter.TokenAuthenticationFilter;

@Configurable
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启配置拦截提前进行
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private TokenAuthenticationFilter tokenAuthenticationFilter;
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Bean
	public AuthenticationManager getAuthenticationManager() {
		return new AuthenticationManager() {

			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				
				return authentication;
			}
			
		};
	}
	
	@Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
    }
	
	// 方法拦截，配置安全认证
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 禁用session管理、允许example模块无限制使用
		http.csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().authorizeRequests()
		.antMatchers("/uac/**").permitAll()
		.antMatchers("/*.html").permitAll()
		.antMatchers("/webjars/**").permitAll()
		.antMatchers("/v2/api-docs").permitAll()
		.antMatchers("/swagger-resources/configuration/ui").permitAll()
		.antMatchers("/swagger-resources").permitAll()
		.antMatchers("/swagger-resources/configuration/security").permitAll()
		.anyRequest().authenticated();
		
		// 运行header缓存
		http.headers().cacheControl();
		// 使用过滤器验证权限
		http.addFilterAfter(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
