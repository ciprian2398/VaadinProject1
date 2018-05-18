package com.app.ui.mainlayout.productgrid.listeners;

import com.app.ui.data.ProductDataManager;
import com.app.backend.model.Product;
import com.vaadin.ui.components.grid.EditorSaveEvent;
import com.vaadin.ui.components.grid.EditorSaveListener;

public class GridEditorSaveListener implements EditorSaveListener<Product> {

    private final ProductDataManager productDataManager;

    public GridEditorSaveListener(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
    }

    @Override
    public void onEditorSave(EditorSaveEvent<Product> event) {
        productDataManager.updateProduct(event.getBean());
    }

}
