package com.ciprian.components.mainlayout;

import com.ciprian.datasource.HibernateDataSource;
import com.ciprian.product.Product;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TextField;
import com.vaadin.ui.components.grid.EditorSaveListener;
import com.vaadin.ui.renderers.NumberRenderer;

public class CustomGrid extends Grid<Product> {
    private static CustomGrid instance = null;

    private CustomGrid() {
        init();
    }

    public static CustomGrid getInstance() {
        if (instance == null) {
            instance = new CustomGrid();
        }
        return instance;
    }


    public void refreshGrid() {
        setItems(HibernateDataSource.getAllProducts());
    }

    private void init() {

        final Binder<Product> binder = getEditor().getBinder();

        addColumn(Product::getProductName)
                .setCaption("Name")
                .setEditorBinding(binder
                        .forField(new TextField())
                        .withValidator(name -> (name.length() >= 3),
                                "Full name must contain at least three characters")
                        .bind(Product::getProductName, Product::setProductName)
                );

        addColumn(Product::getQuantity, new NumberRenderer())
                .setCaption("Quantity")
                .setEditorBinding(binder
                        .forField(new TextField())
                        .withConverter(new StringToIntegerConverter("Not n Integer"))
                        .withValidator(quantity -> (quantity > 0),
                                "Not a valid quantity")
                        .bind(Product::getQuantity, Product::setQuantity)
                );

        addColumn(Product::getTotalPriece, new NumberRenderer())
                .setCaption("Price")
                .setEditorBinding(binder
                        .forField(new TextField())
                        .withConverter(new StringToDoubleConverter("Not a double"))
                        .withValidator(price -> (price > 0),
                                "Not a valid price")
                        .bind(Product::getTotalPriece, Product::setTotalPriece)
                );

        getEditor().addSaveListener((EditorSaveListener<Product>) event -> HibernateDataSource.updateProduct(event.getBean()));

        setWidth("100%");
        setHeight("100%");
        setSelectionMode(SelectionMode.MULTI);
        getEditor().setEnabled(true);
        setResponsive(true);
        refreshGrid();
    }
}
