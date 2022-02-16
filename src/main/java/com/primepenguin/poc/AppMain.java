package com.primepenguin.poc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
 

public class AppMain {
 
    public static void main(String[] args) {        
        // Creating the config instance & passing the hibernate config file.
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");
 
       // Employee employee = null;
        
        // Session object to start the db transaction.
        SessionFactory sessionFactory= config.buildSessionFactory();
        Session session = sessionFactory.openSession();
   
       // employee = (Employee)session.get(Employee.class, "418790FC-815B-40F9-02B0-08D7265C17EE");
         
//        System.out.println(employee.getFirstName());
//        System.out.println(employee.getBirthDate());
//        System.out.println(employee.getAddress().getPostCode());
// 
 
        // Closing the session object.
        session.close();
        sessionFactory.close();
    }
}
