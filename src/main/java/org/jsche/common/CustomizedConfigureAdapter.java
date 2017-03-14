/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.common;

import org.jsche.common.inteceptor.LoginInteceptor;
import org.jsche.common.inteceptor.SystemUsageLogInteceptor;
import org.jsche.common.inteceptor.TokenStageInteceptor;
import org.jsche.common.inteceptor.ValidTokenInteceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author myan
 */
@Configuration
public class CustomizedConfigureAdapter extends WebMvcConfigurerAdapter{
    @Bean
    public ValidTokenInteceptor validTokenInteceptor(){
        return new ValidTokenInteceptor();
    }
    
    @Bean
    public TokenStageInteceptor tokenStageInteceptor(){
    	return new TokenStageInteceptor();
    }
    
    @Bean
    public SystemUsageLogInteceptor systemLogInteceptor(){
    	return new SystemUsageLogInteceptor();
    }
    
    @Bean
    public LoginInteceptor loginInteceptor(){
    	return new LoginInteceptor();
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validTokenInteceptor()).addPathPatterns("/user/*")
            .excludePathPatterns("/user/dashboard").excludePathPatterns("/user/logout");
        registry.addInterceptor(tokenStageInteceptor()).addPathPatterns("/**");
        registry.addInterceptor(systemLogInteceptor()).addPathPatterns("/**");
        registry.addInterceptor(loginInteceptor()).addPathPatterns("/**");
    }
    
}
