package com.app.backend.service;

import com.app.backend.dao.ProductDAO;
import com.app.backend.dao.ProductDaoImpl;
import com.app.backend.model.Product;

import java.io.Serializable;
import java.util.List;

public class ProductService implements ProductDAO<Product, String> {

    private ProductDaoImpl productDaoImpl;

    public ProductService() {
        productDaoImpl = new ProductDaoImpl();
    }

    public void persist(Product entity) {
        productDaoImpl.openCurrentSessionwithTransaction();
        productDaoImpl.persist(entity);
        productDaoImpl.closeCurrentSessionwithTransaction();
    }

    public void update(Product entity) {
        productDaoImpl.openCurrentSessionwithTransaction();
        productDaoImpl.update(entity);
        productDaoImpl.closeCurrentSessionwithTransaction();
    }

    public Product findById(String id) {
        productDaoImpl.openCurrentSession();
        Product product = productDaoImpl.findById(id);
        productDaoImpl.closeCurrentSession();
        return product;
    }

    public void delete(Product product) {
        productDaoImpl.openCurrentSessionwithTransaction();
        productDaoImpl.delete(product);
        productDaoImpl.closeCurrentSessionwithTransaction();
    }

    public List<Product> findAll() {
        productDaoImpl.openCurrentSession();
        List<Product> products = productDaoImpl.findAll();
        productDaoImpl.closeCurrentSession();
        return products;
    }

    public void deleteAll() {
        productDaoImpl.openCurrentSessionwithTransaction();
        productDaoImpl.deleteAll();
        productDaoImpl.closeCurrentSessionwithTransaction();
    }

    public ProductDaoImpl ProductDao() {
        return productDaoImpl;
    }
}
