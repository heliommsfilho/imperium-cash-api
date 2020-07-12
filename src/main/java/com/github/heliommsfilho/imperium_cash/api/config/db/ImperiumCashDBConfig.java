package com.github.heliommsfilho.imperium_cash.api.config.db;

import java.util.Objects;
import java.util.Properties;

import javax.sql.DataSource;

import com.github.heliommsfilho.imperium_cash.api.model.BaseEntity;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceInitializationMode;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.heliommsfilho.imperium-cash.api.repository",
                       entityManagerFactoryRef = "imperiumCashEntityManagerFactory",
                       transactionManagerRef   = "imperiumCashTransactionManager")
public class ImperiumCashDBConfig {
    
    /**
     * Value for {@code hibernate.hbm2ddl.auto} property.
     */
    @Value( "${imperium-cash.app.database.hibernate.ddl-auto}" )
    private String hibernateDdlAuto;

    /**
     * Value for {@code hibernate.show_sql} property.
     */
    @Value( "${imperium-cash.app.database.hibernate.show-sql}" )
    private String hibernateShowSql;

    /**
     * Value for {@code hibernate.format_sql} property.
     */
    @Value( "${imperium-cash.app.database.hibernate.format-sql}" )
    private String hibernateFormatSql;

    /**
     * Enable or disable Spring's datasource initialization.
     */
    @Value( "${imperium-cash.app.database.datasource.initialization-mode}" )
    private String initializationMode;
    
    @Bean
    @Primary
    @ConfigurationProperties("imperium-cash.app.database.datasource.connection")
    public DataSourceProperties imperiumCashDatasourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setInitializationMode(DataSourceInitializationMode.valueOf(initializationMode));

        return dataSourceProperties;
    }

    @Primary
    @Bean("imperiumCashDatasource")
    @ConfigurationProperties("imperium-cash.app.database.datasource.configuration")
    public DataSource imperiumCashDataSource() {
        return imperiumCashDatasourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "imperiumCashEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean imperiumCashEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        LocalContainerEntityManagerFactoryBean emf = builder.dataSource(imperiumCashDataSource()).packages(BaseEntity.class).build();

        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(getHibernateConfigProperties());

        return emf;
    }

    @Primary
    @Bean(name = "imperiumCashTransactionManager")
    public PlatformTransactionManager imperiumCashTransactionManager(final @Qualifier("imperiumCashEntityManagerFactory") LocalContainerEntityManagerFactoryBean emf) {
        return new JpaTransactionManager(Objects.requireNonNull(emf.getObject()));
    }

        /**
     * Defines specific Hibernate properties.
     *
     * @return {@code Properties} containing configured parameters.
     */
    private Properties getHibernateConfigProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", hibernateDdlAuto);
        hibernateProperties.setProperty("hibernate.show_sql", hibernateShowSql);
        hibernateProperties.setProperty("hibernate.format_sql", hibernateFormatSql);

        return  hibernateProperties;
    }
}