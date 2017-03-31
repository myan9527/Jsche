package org.jsche.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@MapperScan("org.jsche.dao")
public class DataSourceConfig {
	
	@ConfigurationProperties(prefix = "datasource.druid")
	@Bean(name="datasource",initMethod = "init",destroyMethod = "close")
    public DruidDataSource getDataSource(){
         return new DruidDataSource();
    }
	
	@Bean
    public PlatformTransactionManager transactionManager(){
        return new DataSourceTransactionManager(getDataSource());
    }
	
	@Bean
    @Profile("dev")
    public FilterRegistrationBean druidFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new WebStatFilter());
        Map<String, String> intParams = new HashMap<>();
        intParams.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        registration.setName("DruidWebStatFilter");
        registration.setUrlPatterns(Lists.newArrayList("/*"));
        registration.setInitParameters(intParams);
        return registration;
    }	
	
	@Bean
    @Profile("dev")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean();
        registration.setServlet(new StatViewServlet());
        registration.setName("druid");
        registration.setUrlMappings(Lists.newArrayList("/druid/*"));
        //自定义添加初始化参数
        Map<String, String> intParams = new HashMap<>();
        intParams.put("loginUsername","druid");
        intParams.put("loginPassword","druid");
        registration.setName("DruidWebStatFilter");
        registration.setInitParameters(intParams);
        return registration;
    }
}
