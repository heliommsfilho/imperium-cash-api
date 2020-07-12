package com.github.heliommsfilho.imperium_cash.api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Imperium Cash custom properties.
 *
 * @author Hélio Márcio Filho <heliommsfilho@tutanota.com>
 */
@Configuration
@ConfigurationProperties(prefix = "imperium-cash.app")
public class ImperiumCashCustomProperties {

    /**
     * The Database.
     */
    @Getter @Setter
    private Database database;

    /**
     * The Database.
     */
    @Getter @Setter
    public static class Database {

        /**
         * The Datasource.
         */
        private Datasource datasource;
        /**
         * The Hibernate.
         */
        private Hibernate hibernate;

        /**
         * The Datasource.
         */
        @Getter @Setter
        public static class Datasource {

            /**
             * The Datasource initialization mode. <br>
             * See possible modes: {@link org.springframework.boot.jdbc.DataSourceInitializationMode}
             */
            private String initializationMode;

            /**
             * The Connection.
             */
            @Getter @Setter
            private Connection connection;

            /**
             * The Connection.
             */
            @Getter @Setter
            public static class Connection {

                /**
                 * Datasource name.
                 */
                private String name;
                /**
                 * Database JDBC URL.
                 */
                private String url;
                /**
                 * Driver class name.
                 */
                private String driverClassName;
                /**
                 * Database username.
                 */
                private String username;
                /**
                 * Database password.
                 */
                private String password;
            }
        }

        /**
         * The Hibernate.
         */
        @Getter @Setter
        public static class Hibernate {

            /**
             * Value for {@code hibernate.hbm2ddl.auto}.
             */
            private String ddlAuto;

            /**
             * Value for {@code hibernate.show_sql}.
             */
            private String showSql;

            /**
             * Value for {@code hibernate.format_sql}.
             */
            private String formatSql;
        }
    }
}
