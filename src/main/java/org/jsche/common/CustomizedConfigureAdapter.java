/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jsche.common;

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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validTokenInteceptor()).addPathPatterns("/user/*");
    }
    
}