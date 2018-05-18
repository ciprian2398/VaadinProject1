package com.app.ui.mainlayout.addform;

import com.app.ui.data.ProductDataManager;
import com.app.backend.model.Product;
import com.app.ui.data.binder.ProductBinder;
import com.app.ui.mainlayout.addform.listeners.SaveButtonClickListener;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;

public class AddForm extends VerticalLayout {

    private final ProductDataManager productDataManager;
    private final TextField nameField;
    private final TextField quantityField;
    private final TextField priceField;
    private final Button saveButton;
    private final Binder<Product> binder;
    private final Window window;

    public AddForm(ProductDataManager productDataManager) {
        super();
        this.productDataManager = productDataManager;
        this.nameField = new TextField("Name");
        this.quantityField = new TextField("Quantity");
        this.priceField = new TextField("Price");
        this.saveButton = new Button("Save");
        this.window = new Window("Add Window");
        this.binder = new ProductBinder(nameField, quantityField, priceField);
        init();
    }

    public Binder<Product> getBinder() {
        return binder;
    }

    public ProductDataManager getProductDataManager() {
        return productDataManager;
    }

    private void init() {
        window.setContent(this);
        window.setSizeUndefined();
        saveButton.addClickListener(new SaveButtonClickListener(this));
        addComponents(nameField, quantityField, priceField);
        addComponent(saveButton);
    }

    public void openWindow() {
        window.center();
        UI.getCurrent().addWindow(window);
    }

    public void setBean() {
        binder.setBean(new Product());
    }

    public void closeWindow() {
        window.close();
    }

    public Window getWindow() {
        return window;
    }
}
