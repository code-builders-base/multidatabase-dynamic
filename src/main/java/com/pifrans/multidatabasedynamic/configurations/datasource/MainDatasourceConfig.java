package com.pifrans.multidatabasedynamic.configurations.datasource;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@EnableJpaRepositories(basePackages = "com.pifrans.multidatabasedynamic.repositories.databases", entityManagerFactoryRef = "mainEntityManagerFactory", transactionManagerRef = "mainTransactionManager")
public class MainDatasourceConfig {
    private final PropertiesJpaConfig propertiesJpaConfig;

    @Value("${datasource.main.url}")
    private String urlDatasource;

    @Value("${datasource.main.user-name}")
    private String userName;

    @Value("${datasource.main.password}")
    private String password;


    @Primary
    @Bean(name = "mainDataSource")
    public DataSource mainDataSource() {
        var dataSource = new DataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(urlDatasource);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        dataSource.setTestOnBorrow(true);
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setValidationInterval(0);
        return dataSource;
    }

    @Primary
    @Bean(name = "mainEntityManagerFactory")
    public EntityManagerFactory mainEntityManagerFactory() {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("com.pifrans.multidatabasedynamic.entities.databases");
        factory.setDataSource(mainDataSource());
        factory.setJpaPropertyMap(this.propertiesJpaConfig.properties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(mainEntityManagerFactory());
        return transactionManager;
    }
}
