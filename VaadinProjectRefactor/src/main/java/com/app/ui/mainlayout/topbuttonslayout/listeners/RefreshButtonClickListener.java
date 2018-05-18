package com.app.ui.mainlayout.topbuttonslayout.listeners;

import com.app.ui.data.ProductDataManager;
import com.vaadin.ui.Button;


public class RefreshButtonClickListener implements Button.ClickListener {

    private final ProductDataManager productDataManager;

    public RefreshButtonClickListener(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        productDataManager.refresh();
    }
}