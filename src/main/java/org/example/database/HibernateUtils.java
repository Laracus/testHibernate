package org.example.database;

import org.example.user.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        Properties properties = new Properties();
        properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/minecraft");
        properties.put("hibernate.connection.username", "root");
        properties.put("hibernate.connection.password", "admin123-Vv");
        properties.put("hibernate.current_session_context_class", "thread");

        configuration.addAnnotatedClass(User.class);

        properties.put("show-sql", "true");
        properties.put("format_sql", "true");

        configuration.setProperties(properties);

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        System.out.println("Hibernate Java Config serviceRegistry created");

        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Не получилось получить Session Factory");
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
