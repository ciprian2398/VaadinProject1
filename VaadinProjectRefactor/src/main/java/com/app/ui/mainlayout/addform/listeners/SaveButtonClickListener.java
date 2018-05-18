package com.app.ui.mainlayout.addform.listeners;

import com.app.ui.data.ProductDataManager;
import com.app.backend.model.Product;
import com.app.ui.mainlayout.addform.AddForm;
import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

public class SaveButtonClickListener implements Button.ClickListener {

    private final AddForm addForm;
    private final Binder binder;
    private final ProductDataManager productDataManager;

    public SaveButtonClickListener(AddForm addForm) {
        this.addForm = addForm;
        this.binder = addForm.getBinder();
        this.productDataManager = addForm.getProductDataManager();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        binder.validate();
        if (binder.isValid()) {
            Product product = (Product) binder.getBean();
            productDataManager.addProduct(product);
            addForm.closeWindow();
        } else
            Notification.show("Not a valid input.");
    }
}
