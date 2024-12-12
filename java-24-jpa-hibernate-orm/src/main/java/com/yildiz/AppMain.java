package com.yildiz;

import com.yildiz.entity_models.Customer;
import com.yildiz.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppMain {
    public static void main(String[] args) {
        Customer customer2 = new Customer(2,"Ahmet","Yıldız");

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("sena");
        customer.setLastName("yıldız");

        System.out.println(customer);
        System.out.println(customer2);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
                session.save(customer2);
                session.save(customer);
            tx.commit();
        }catch (HibernateError e) {
            System.out.println("HibernateError" + e.getMessage());
        }finally {
            session.close();
        }


    }
}
