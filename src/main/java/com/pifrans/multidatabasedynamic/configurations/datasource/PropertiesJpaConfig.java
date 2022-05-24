package com.pifrans.multidatabasedynamic.configurations.datasource;

import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PropertiesJpaConfig {


    public Map<String, Object> properties() {
        var properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.put("hibernate.default_schema", "public");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.format_sql", "true");
        properties.put("javax.persistence.create-database-schemas", "true");
        properties.put("hibernate.id.new_generator_mappings", "false");
        properties.put("hibernate.enable_lazy_load_no_trans", "true");
        properties.put("hibernate.event.merge.entity_copy_observer", "allow");
        return properties;
    }
}
