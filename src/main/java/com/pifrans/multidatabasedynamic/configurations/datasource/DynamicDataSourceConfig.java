package com.pifrans.multidatabasedynamic.configurations.datasource;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@DependsOn("dataSourceRouting")
@EnableJpaRepositories(basePackages = "com.pifrans.multidatabasedynamic.repositories.users", transactionManagerRef = "othersTransactionManager", entityManagerFactoryRef = "othersEntityManagerFactory")
public class DynamicDataSourceConfig {
    private final DataSourceRouting dataSourceRouting;
    private final PropertiesJpaConfig propertiesJpaConfig;


    @Lazy
    @Bean(name = "othersDataSource")
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DataSource othersDataSource() {
        return this.dataSourceRouting;
    }

    @Lazy
    @Bean(name = "othersEntityManagerFactory")
    public EntityManagerFactory othersEntityManagerFactory() {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factory.setPackagesToScan("com.pifrans.multidatabasedynamic.entities.users");
        factory.setDataSource(othersDataSource());
        factory.setJpaPropertyMap(this.propertiesJpaConfig.properties());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Lazy
    @Bean(name = "othersTransactionManager")
    public PlatformTransactionManager othersTransactionManager() {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(othersEntityManagerFactory());
        return transactionManager;
    }
}
