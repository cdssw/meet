package com.moim.meet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * ResourceServerConfig.java
 * 
 * @author cdssw
 * @since 2020. 5. 15.
 * @description  
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * 2020. 5. 15.   cdssw            최초 생성
 * </pre>
 */
@EnableResourceServer
@Configuration
@Profile({"dev", "prod"})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String[] WHITE_LIST = {
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/swagger-ui.html",
			"/webjars/**",
			"/h2-console/**",
			"/api/*/v2/api-docs"			
	};
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable(); // X-Frame-Options 차단 해제
		http.authorizeRequests()
			.antMatchers(WHITE_LIST).permitAll()
			.antMatchers("/").access("#oauth2.hasScope('read')") // read scope만 허용
			.anyRequest().authenticated(); // 모든 요청 호출시 인증되어야 함
	}
}
