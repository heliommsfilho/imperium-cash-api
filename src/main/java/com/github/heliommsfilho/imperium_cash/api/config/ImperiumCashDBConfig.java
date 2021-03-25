package com.github.heliommsfilho.imperium_cash.api.config;

import com.github.heliommsfilho.imperium_cash.api.domain.model.BaseEntity;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.heliommsfilho.imperium_cash.api.domain.repository",
                       entityManagerFactoryRef = "imperiumCashEntityManagerFactory",
                       transactionManagerRef   = "imperiumCashTransactionManager")
public class ImperiumCashDBConfig {

    private final ImperiumCashCustomProperties properties;

    @Autowired
    public ImperiumCashDBConfig(ImperiumCashCustomProperties properties) {
        this.properties = properties;
    }
    
    @Bean
    @Primary
    @ConfigurationProperties("imperium-cash.api.database.datasource.connection")
    public DataSourceProperties imperiumCashDatasourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        dataSourceProperties.setInitializationMode(DataSourceInitializationMode.valueOf(properties.getDatabase().getDatasource().getInitializationMode()));

        return dataSourceProperties;
    }

    @Primary
    @Bean("imperiumCashDatasource")
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
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", properties.getDatabase().getHibernate().getDdlAuto());
        hibernateProperties.setProperty("hibernate.show_sql", properties.getDatabase().getHibernate().getShowSql());
        hibernateProperties.setProperty("hibernate.format_sql", properties.getDatabase().getHibernate().getFormatSql());

        return  hibernateProperties;
    }
}
