package com.app.ui.mainlayout.productgrid;

import com.app.ui.data.ProductDataManager;
import com.app.backend.model.Product;
import com.app.ui.mainlayout.productgrid.listeners.GridEditorSaveListener;
import com.vaadin.data.Binder;
import com.app.ui.data.validator.DoublePositiveNumberValidator;
import com.app.ui.data.validator.IntegerPositiveNumberValidator;
import com.app.ui.data.validator.StringLenghtValidator;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.EditorSaveListener;
import com.vaadin.ui.renderers.NumberRenderer;

public class ProductGrid extends Grid<Product> {

    private final Binder<Product> binder;
    private final ProductDataManager productDataManager;
    private final EditorSaveListener editorSaveListener;

    public ProductGrid(ProductDataManager productDataManager) {
        this.productDataManager = productDataManager;
        this.binder = getEditor().getBinder();
        this.editorSaveListener = new GridEditorSaveListener(productDataManager);
        init();
    }

    public ProductDataManager getProductDataManager() {
        return productDataManager;
    }

    private void init() {

        //TODO fix unchecked assignment
        setDataProvider(productDataManager.getListDataProvider());
        getEditor().addSaveListener(editorSaveListener);

        addColumn(Product::getProductName)
                .setCaption("Name")
                .setEditorBinding(binder
                        .forField(new TextField())
                        .withValidator(new StringLenghtValidator(3))
                        .bind(Product::getProductName, Product::setProductName)
                );

        addColumn(Product::getQuantity, new NumberRenderer())
                .setCaption("Quantity")
                .setEditorBinding(binder
                        .forField(new TextField())
                        .withConverter(new StringToIntegerConverter("Not n Integer"))
                        .withValidator(new IntegerPositiveNumberValidator())
                        .bind(Product::getQuantity, Product::setQuantity)
                );

        addColumn(Product::getTotalPriece, new NumberRenderer())
                .setCaption("Price")
                .setEditorBinding(binder
                        .forField(new TextField())
                        .withConverter(new StringToDoubleConverter("Not a double"))
                        .withValidator(new DoublePositiveNumberValidator())
                        .bind(Product::getTotalPriece, Product::setTotalPriece)
                );

        setWidth("100%");
        setHeight("100%");
        setSelectionMode(SelectionMode.MULTI);
        getEditor().setEnabled(true);
        setResponsive(true);
    }
}
