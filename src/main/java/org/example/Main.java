package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    static void main(String[] args) {
        Student s1 = new Student();
        s1.setId(3);
        s1.setFullName("Troutis");
        s1.setAge(24);

        Student updatedStudent = new Student();
        updatedStudent.setId(1);
        updatedStudent.setFullName("Micro");
        updatedStudent.setAge(0);

        var lt1 = new Laptop();
        lt1.setId(1000);
        lt1.setBrand("Asus");
        lt1.setModel("Rog");
        lt1.setRam(32);

        var adr1 = new Address("Thessaloniki", "Egnatia", 12);

        Alien a1 = new Alien();
        a1.setId(101);
        a1.setName("John");
        a1.setTech("Java");
        a1.setLaptop(lt1);
        a1.setAddress(adr1);

        //First step on creating a session is creating a config obj
        Configuration cfg = new Configuration();
        //add classes that will become tables
        cfg.addAnnotatedClass(org.example.Student.class);
        cfg.addAnnotatedClass(org.example.Alien.class);
        cfg.addAnnotatedClass(org.example.Laptop.class);
        // load hibernate.cfg.xml from resources
        cfg.configure();
        //Create session factory, once
        SessionFactory sf = cfg.buildSessionFactory();

        //alternative syntax for session factory
        SessionFactory sf2 = new Configuration()
                .addAnnotatedClass(org.example.Student.class)
                .configure()
                .buildSessionFactory();

        //Hibernate session, opens for each operation
        Session session = sf.openSession();
        //Then create a transaction
        Transaction transaction = session.beginTransaction();

        //to fetch data, type(class) and PK
        var gotStudent = session.find(Student.class, 1);

        //to save the obj
//        session.persist(s1);
//        session.persist(lt1);
//        session.persist(a1);

        //to update a row, need transaction
        //session.merge(updatedStudent);

        //to delete, use remove, it takes a whole obj,
        //if obj not known first find()
//        session.remove(s1);

        //hql
        //select * from Laptop where brand='Asus' -> SQL
        //from Laptop where brand='Asus'
        Query<Laptop> query = session.createQuery("from Laptop", Laptop.class);
        Query<Laptop> query1 = session.createQuery("from Laptop where brand='Asus11'", Laptop.class);

        String brand = "Asus";
        Query<Laptop> query2 = session.createQuery("select model from Laptop where brand like ?1", Laptop.class);
        query2.setParameter(1, brand);
        List<Laptop> ltps1 = query2.getResultList();
        System.out.println(ltps1);

        List<Laptop> lpts = query1.getResultList();
        System.out.println(lpts);

        //commit the transaction(not required for select operations)
        transaction.commit();
        //close
        session.close();
        sf.close();

        System.out.println(s1);
        System.out.println(gotStudent.toString());
    }

}
