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
 * Created by wangning on 2017/11/3.
 */
@Configuration
@MapperScan(basePackages = "com.example.mapper.winnerdb", sqlSessionFactoryRef = "winnerdbSqlSessionFactory")
public class WinnerDbConfig {

    @Bean("winnerdbDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.winnerdb")
    public DataSource winnerDbDatasource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("winnerdbSqlSessionFactory")
    public SqlSessionFactory winnerdbSqlSessionFactory(@Qualifier("winnerdbDatasource")DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.example.model.winnerdb");
//        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/winnerdb/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

}
