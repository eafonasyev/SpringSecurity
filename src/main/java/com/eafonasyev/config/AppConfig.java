package com.eafonasyev.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.eafonasyev")
@PropertySource("classpath:presistence-oracle.properties")
public class AppConfig {


    @Autowired
    private Environment env;
//ENABLE A Bean ViewResolver
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new  InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Bean
    DataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setUser(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        dataSource.setInitialPoolSize(
                getIntProperty("connection.pool.initialPoolSize"));

        dataSource.setMinPoolSize(
                getIntProperty("connection.pool.minPoolSize"));

        dataSource.setMaxPoolSize(
                getIntProperty("connection.pool.maxPoolSize"));

        dataSource.setMaxIdleTime(
                getIntProperty("connection.pool.maxIdleTime"));
        return dataSource;
    }

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }
}
