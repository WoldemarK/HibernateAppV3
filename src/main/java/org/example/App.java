package org.example;

import org.example.model.Passport;
import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {
            Transaction transaction = session.beginTransaction();
//            Person ivan = new Person("Ivan", 22);
//            Passport passportIvan = new Passport(ivan, 1234);
//
//            ivan.setPassport(passportIvan);
            /**
             * По человеку искать пасспорт
             */
            Person p = session.get(Person.class,1);
            System.out.println(p.getPassport().getPassportNumber());
           // session.persist(ivan);

            /**
             * По паспорту найти человека
             */
            Passport passport = session.get(Passport.class,1);
            System.out.println(passport.getPerson().getPassport());
            transaction.commit();
            session.close();
        } finally {
            sessionFactory.close();
        }

    }
}
