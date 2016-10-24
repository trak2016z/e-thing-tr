package pl.ething.config;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Bean
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://ec2-54-228-214-46.eu-west-1.compute.amazonaws.com:5432/d3gdcmhjmsbvoq?sslmode=require");
        dataSource.setUsername("vwekkkccgwsgns");
        dataSource.setPassword("Api11NmH0FjZrs4GWee2WWINsK");
        //dataSource.setSchema("public");
        Properties connectionProperties = new Properties();
        //connectionProperties.put("serverName", "ec2-54-228-214-46.eu-west-1.compute.amazonaws.com");
        //connectionProperties.put("databaseName", "d3gdcmhjmsbvoq");
        //connectionProperties.put("portNumber", "5432");
        //connectionProperties.put("user", "vwekkkccgwsgns");
        //connectionProperties.put("password", " Api11NmH0FjZrs4GWee2WWINsK");
        //connectionProperties.put("ssl", "true");
        //connectionProperties.put("sslfactory", "NonValidatingFactory.NonValidatingFactory");
        dataSource.setConnectionProperties(connectionProperties);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan("pl.ething.model");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        additionalProperties.put("hibernate.show_sql", "true");
        entityManagerFactory.setJpaProperties(additionalProperties);
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
