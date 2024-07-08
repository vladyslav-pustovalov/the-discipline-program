package com.thedisciplineprogram.utils;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.models.db_entities.Program;
import com.thedisciplineprogram.models.db_entities.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;
    private static final Configuration configuration;

    private HibernateSessionFactoryUtil() {
    }

     static {
        var url = String.format(
                "jdbc:postgresql://%s:%s/%s",
                System.getenv("TDP_DB_HOST"),
                System.getenv("TDP_DB_PORT"),
                System.getenv("TDP_DB_SCHEMA")
        );

        configuration = new Configuration();
        configuration.setProperty("hibernate.connection.characterEncoding", "UTF-8");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");

        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", System.getenv("TDP_DB_USER"));
        configuration.setProperty("hibernate.connection.password", System.getenv("TDP_DB_PASS"));

        configuration.setProperty("hibernate.format_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Team.class);
        configuration.addAnnotatedClass(Program.class);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        try {
            sessionFactory = configuration.buildSessionFactory();
            log.info("SessionFactory created");
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
    }
}
