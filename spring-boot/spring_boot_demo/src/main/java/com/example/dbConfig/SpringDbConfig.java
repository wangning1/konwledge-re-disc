package com.example.dbConfig;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by wangning on 2017/10/31.
 */

@Configuration
@MapperScan(basePackages = "com.example.mapper.springdb", sqlSessionFactoryRef = "springdbSqlSessionFactory")
public class SpringDbConfig {

    @Bean("springdbDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.springdb")
    @Primary
    public DataSource springDbDatasource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("springdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory springSqlSessionFactory(@Qualifier("springdbDatasource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage("com.example.model.springdb");
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/springdb/*.xml"));
        return factoryBean.getObject();
    }


}
