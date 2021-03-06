/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lkmproject.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Jayuk
 */
public class HibernateUtil 
{
    private static final SessionFactory SessionFactory = createSessionFactory();
    private static ThreadLocal<Session> session = new ThreadLocal<>();
    
     public static SessionFactory createSessionFactory()
     {
         Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
         return configuration.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build());
     }
     public static Session getSession()
     {
         session.set(SessionFactory.openSession());
         return session.get();
     }
     public static void closeSession()
     {
         Session s = session.get();
         if(s !=  null)
         {
            s.flush();
            s.close();
            session.remove();
         }
     }
}
