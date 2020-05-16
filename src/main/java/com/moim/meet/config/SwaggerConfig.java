package com.moim.meet.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import com.fasterxml.classmate.TypeResolver;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig.java
 * 
 * @author cdssw
 * @since Apr 10, 2020
 * @description Swagger 설정 
 * 
 * <pre>
 * since          author           description
 * ===========    =============    ===========================
 * Apr 10, 2020   cdssw            최초 생성
 * </pre>
 */
@Configuration
@EnableSwagger2
@AllArgsConstructor
public class SwaggerConfig {

	// 생성자로 인한 자동 Autowired
	private final TypeResolver typeResolver;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				// pageable custom 처리
				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class), typeResolver.resolve(Page.class)))
				.apiInfo(swaggerInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.moim.meet.controller"))
				.paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false);
	}
	
	private ApiInfo swaggerInfo() {
		return new ApiInfoBuilder().title("Moim API Documentation").description("Meet Service Document")
				.license("Andrew").licenseUrl("cdssw.duckdns.org").version("1").build();
	}
	
	// pageable custom 처리
	@Getter
	@Setter
	@ApiModel
	private static class Page {
		
		@ApiModelProperty(value = "페이지 번호(0..N)")
		private Integer page;
		
		@ApiModelProperty(value = "페이지 크기", allowableValues = "range[0, 100]")
		private Integer size;
		
		@ApiModelProperty(value = "정렬(사용법: 컬럼명,ASC|DESC)")
		private List<String> sort;
	}
}
