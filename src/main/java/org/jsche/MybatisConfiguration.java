package org.jsche;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by myan on 2017/3/29.
 */
@Configuration
@MapperScan(basePackages = {"org.jsche.dao"})
public class MybatisConfiguration implements EnvironmentAware {
    @Autowired
    private DataSource dataSource;
    private RelaxedPropertyResolver propertyResolver;

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, "mybatis.");
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactory(){
        try {
            SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
            sessionFactory.setDataSource(dataSource);
            sessionFactory.setTypeAliasesPackage(propertyResolver.getProperty("typeAliasesPackage"));
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                            .getResources(propertyResolver.getProperty("mapperLocations")));
            return sessionFactory.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Bean
    @ConditionalOnMissingBean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
