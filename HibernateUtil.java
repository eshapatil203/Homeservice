package com.homeservice1.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.homeservice1.entity.*;


public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration config = new Configuration();

            // Database Connection
            config.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
            config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/carenest");
            config.setProperty("hibernate.connection.username", "root");
            config.setProperty("hibernate.connection.password", "Esha#2020"); // Change if needed

            // Hibernate Settings
            config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
            config.setProperty("hibernate.hbm2ddl.auto", "update"); // use "update" for safety
            config.setProperty("hibernate.show_sql", "true");
            config.setProperty("hibernate.format_sql", "true");

            // Add Entity Classes
            config.addAnnotatedClass(User.class);
            config.addAnnotatedClass(Service.class);
            config.addAnnotatedClass(Provider.class);
            config.addAnnotatedClass(Booking.class);
            config.addAnnotatedClass(Payment.class);
            config.addAnnotatedClass(Feedback.class);
        
            sessionFactory = config.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("There was a problem building the SessionFactory.");
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
