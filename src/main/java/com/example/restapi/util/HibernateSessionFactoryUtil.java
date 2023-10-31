package com.example.restapi.util;

import com.example.restapi.model.Event;
import com.example.restapi.model.File;
import com.example.restapi.model.User;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@NoArgsConstructor
public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;
    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
                        .configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(File.class);
                configuration.addAnnotatedClass(Event.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.
                                getProperties());
                sessionFactory = configuration
                        .buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
    public static Session session() {
        return getSessionFactory().openSession();
    }
}
