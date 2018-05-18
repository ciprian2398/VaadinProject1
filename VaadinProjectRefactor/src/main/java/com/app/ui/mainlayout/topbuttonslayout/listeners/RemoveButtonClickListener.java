package com.app.ui.mainlayout.topbuttonslayout.listeners;

import com.app.ui.data.ProductDataManager;
import com.app.ui.mainlayout.productgrid.ProductGrid;
import com.vaadin.ui.Button;

public class RemoveButtonClickListener implements Button.ClickListener {

    private final ProductGrid grid;
    private final ProductDataManager productDataManager;

    public RemoveButtonClickListener(ProductGrid grid, ProductDataManager productDataManager) {
        this.grid = grid;
        this.productDataManager = productDataManager;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        grid.getSelectedItems().forEach(item -> {
            grid.deselect(item);
            productDataManager.deleteProduct(item);
        });
    }
}
