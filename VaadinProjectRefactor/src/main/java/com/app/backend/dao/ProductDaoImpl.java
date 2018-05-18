package com.app.backend.dao;

import com.app.backend.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class ProductDaoImpl implements ProductDAO<Product, String> {

    private Session currentSession;
    private Transaction currentTransaction;

    public ProductDaoImpl() {
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        return configuration.buildSessionFactory();
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }


    @Override
    public void persist(Product entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Product entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Product findById(String id) {
        return getCurrentSession().get(Product.class, id);
    }

    @Override
    public void delete(Product entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Product> findAll() {
        return getCurrentSession().createCriteria(Product.class).list();
    }

    @Override
    public void deleteAll() {
        List<Product> entityList = findAll();
        for (Product entity : entityList) {
            delete(entity);
        }
    }
}
