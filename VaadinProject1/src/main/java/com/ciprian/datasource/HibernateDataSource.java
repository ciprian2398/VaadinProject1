package com.ciprian.datasource;

import com.ciprian.product.Product;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateDataSource {

    private static final SessionFactory sessionFactory = null;

    private static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            return new Configuration().configure().buildSessionFactory();
        }else{
            return sessionFactory;
        }
    }

    private static Session getSesion(){
        return getSessionFactory().openSession();
    }

    @SuppressWarnings("unchecked")
    public static List<Product> getAllProducts() {
        Session session = getSesion();
        Transaction transaction = null;
        List<Product> specialDates = null;
        try {
            transaction = session.beginTransaction();
            specialDates = session.createCriteria(Product.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return specialDates;
    }


    public static void addProduct(Product product) {
        Session session = getSesion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public static void deleteProduct(Product product) {
        Session session = getSesion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(product);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    public static void updateProduct(Product product) {
        Session session = getSesion();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(product);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

}
