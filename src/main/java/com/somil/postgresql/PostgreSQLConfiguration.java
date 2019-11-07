package com.somil.postgresql;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource(value = "classpath:dbm_main.properties")
public class PostgreSQLConfiguration {

    @Value("${spring.datasource.driver-class-name}")
    private String DB_DRIVER;

    @Value("${spring.datasource.password}")
    private String DB_PASSWORD;

    @Value("${spring.datasource.url}")
    private String DB_URL;

    @Value("${spring.datasource.username}")
    private String DB_USERNAME;
    
    @Value("${db.schema}")
    private String DB_SCHEMA;

    @Value("${hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${hibernate.show_sql}")
    private String HIBERNATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITYMANAGER_PACKAGES_TO_SCAN;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUrl(DB_URL);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
    	JpaTransactionManager txManager = new JpaTransactionManager();
    	txManager.setEntityManagerFactory(entityManagerFactory(dataSource()).getObject());
        return txManager;
    }    

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource ) {
    	
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan("com.somil");
 
        Properties jpaProperties = new Properties();
     
        //Configures the used database dialect. This allows Hibernate to create SQL that is optimized for the used database.
        jpaProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
 
        //Specifies the action that is invoked to the database when the Hibernate SessionFactory is created or closed.
        jpaProperties.put("hibernate.hbm2ddl.auto", 
                HIBERNATE_HBM2DDL_AUTO
        );
 
        // If the value of this property is true, Hibernate writes all SQL statements to the console. 
        jpaProperties.put("hibernate.show_sql", 
                HIBERNATE_SHOW_SQL
        );
 
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
 
        return entityManagerFactoryBean;
    }
}