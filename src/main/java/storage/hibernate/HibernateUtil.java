package storage.hibernate;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import passenger.Passenger;
import storage.DatabaseInitService;
import tests.Person;
import ticket.Ticket;

import java.util.Arrays;
import java.util.List;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Passenger.class)
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Person.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        //Init DB using flyway
        new DatabaseInitService().initDb();

        HibernateUtil util = HibernateUtil.getInstance();

        //Get single
//        Session session = util.getSessionFactory().openSession();
//        Passenger passenger = session.get(Passenger.class, 1L);
//        System.out.println("passenger = " + passenger);
//        session.close();

        //List all passengers
//        Session session = util.getSessionFactory().openSession();
//        List<Passenger> passengers = session.createQuery("from Passenger", Passenger.class).list();
//        System.out.println("passengers = " + passengers);
//        session.close();

        //Save new passenger
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Passenger newPassenger = new Passenger();
//                newPassenger.setName("New Passenger Name");
//                newPassenger.setPassport("yy99");
//                session.persist(newPassenger);
//
//                System.out.println("newPassenger = " + newPassenger);
//            transaction.commit();
//        session.close();

        //Modify existing passenger
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Passenger existing = session.get(Passenger.class, 2L);
//                existing.setName("Modified passenger name");
//                session.persist(existing);
//            transaction.commit();
//        session.close();

        //List all tickets
//        Session session = util.getSessionFactory().openSession();
//        List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
//        System.out.println("tickets = " + tickets);
//        session.close();

//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Person p = new Person();
//                p.setAddressList(Arrays.asList("address1", "address2"));
//                session.persist(p);
//            transaction.commit();
//        session.close();

        Session session = util.getSessionFactory().openSession();
        List<Person> persons = session.createQuery("from Person", Person.class).list();
        System.out.println("persons = " + persons);
        session.close();
    }
}
