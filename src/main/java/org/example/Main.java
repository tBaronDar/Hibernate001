package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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

        Alien a1 = new Alien();
        a1.setId(101);
        a1.setName("John");
        a1.setTech("Java");

        //First step on creating a session is creating a config obj
        Configuration cfg = new Configuration();
        //add classes that will become tables
        cfg.addAnnotatedClass(org.example.Student.class);
        cfg.addAnnotatedClass(org.example.Alien.class);
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
//         session.persist(s1);
        session.persist(a1);

        //to update a row, need transaction
        session.merge(updatedStudent);

        //to delete, use remove, it takes a whole obj,
        //if obj not known first find()
        session.remove(s1);

        //commit the transaction(not required for select operations)
        transaction.commit();
        //close
        session.close();
        sf.close();

        System.out.println(s1.toString());
        System.out.println(gotStudent.toString());
    }

}
