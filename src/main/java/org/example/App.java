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
//            session.persist(ivan);

            /**
             * По человеку искать пасспорт
             */
            Person p = session.get(Person.class, 1);
            System.out.println(p.getPassport().getPassportNumber());

            /**
             * По паспорту найти человека
             */
            Passport passport = session.get(Passport.class, 1);
            System.out.println(passport.getPerson().getPassport());
            /**
             * Изменение данных паспорта по номеру
             */
            Person person = session.get(Person.class, 1);
            person.getPassport().setPassportNumber(7777);
            /**
            * Удаление человека
            */
            Person person1 = session.get(Person.class,1);
            session.remove(person1);

            transaction.commit();
            session.close();
        } finally {
            sessionFactory.close();
        }

    }
}
