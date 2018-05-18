package com.app.ui.data;

import com.app.backend.model.Product;
import com.app.backend.service.ProductService;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;

public class ProductDataManager {
    private ProductService productService;
    private ListDataProvider<Product> listDataProvider;

    public ProductDataManager() {
        this.productService = new ProductService();
        listDataProvider = DataProvider.ofCollection(productService.findAll());
    }

    public ListDataProvider getListDataProvider() {
        return listDataProvider;
    }

    public void addProduct(Product product) {
        listDataProvider.getItems().add(product);
        listDataProvider.refreshAll();
        productService.persist(product);
    }

    public void updateProduct(Product product) {
        listDataProvider.refreshItem(product);
        productService.update(product);
    }

    public void deleteProduct(Product product) {
        listDataProvider.getItems().remove(product);
        listDataProvider.refreshAll();
        productService.delete(product);
    }

    public void refresh() {
        listDataProvider.getItems().clear();
        listDataProvider.getItems().addAll(productService.findAll());
        listDataProvider.refreshAll();
    }
}
