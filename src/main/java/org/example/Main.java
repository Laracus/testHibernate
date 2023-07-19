package org.example;

import org.example.database.HibernateUtils;
import org.example.user.User;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        User user = new User("Ivan");
        HibernateUtils hibernateUtils  = new HibernateUtils();
        try (Session session = hibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        }

    }
}