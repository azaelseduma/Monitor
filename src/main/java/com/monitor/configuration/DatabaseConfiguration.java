package com.monitor.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

/**
 * Created by Azael on 2017/08/10.
 */
@Configuration
public class DatabaseConfiguration {
    @Autowired
    private Environment environment;

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        org.springframework.orm.hibernate5.LocalSessionFactoryBuilder localSessionFactoryBuilder =
                new org.springframework.orm.hibernate5.LocalSessionFactoryBuilder(dataSource());
        localSessionFactoryBuilder.scanPackages("com.monitor.model");
        return localSessionFactoryBuilder.buildSessionFactory();
    }

    @Bean(name = "dataSource")
    @Primary
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/monitor");
        basicDataSource.setUsername("demo");
        basicDataSource.setPassword("xbox360");
        basicDataSource.setTestWhileIdle(true);
        basicDataSource.setValidationQuery("SELECT 1");
        return basicDataSource;
    }

    @Autowired
    @Bean(name = "transactionManager")
    @Primary
    public org.springframework.orm.hibernate5.HibernateTransactionManager hibernateTransactionManager(
            @Qualifier(value = "sessionFactory") SessionFactory sessionFactory) {
        org.springframework.orm.hibernate5.HibernateTransactionManager hibernateTransactionManager =
                new org.springframework.orm.hibernate5.HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(sessionFactory);
        return hibernateTransactionManager;
    }
}
