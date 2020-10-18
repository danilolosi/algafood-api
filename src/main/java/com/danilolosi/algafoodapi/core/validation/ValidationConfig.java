package com.danilolosi.algafoodapi.core.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ValidationConfig {

	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean(); 
		localValidatorFactoryBean.setValidationMessageSource(messageSource);
		
		return localValidatorFactoryBean;
	}
	
}
