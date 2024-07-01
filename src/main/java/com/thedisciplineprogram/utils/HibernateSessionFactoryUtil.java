package com.thedisciplineprogram.utils;

import com.thedisciplineprogram.models.db_entities.Team;
import com.thedisciplineprogram.models.db_entities.Program;
import com.thedisciplineprogram.models.db_entities.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Slf4j
public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            createSessionFactory();
        }
        return sessionFactory;
    }

    private static void createSessionFactory() {
        try {
            Configuration configuration = new Configuration().configure();
            configuration
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Team.class)
                    .addAnnotatedClass(Program.class);
            StandardServiceRegistry builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties())
                    .build();
            sessionFactory = configuration.buildSessionFactory(builder);
            log.info("SessionFactory created");
        } catch (HibernateException e) {
            log.error(e.getMessage());
        }
    }
}
