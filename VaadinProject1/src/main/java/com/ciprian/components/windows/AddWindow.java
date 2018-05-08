package com.ciprian.components.windows;

import com.ciprian.datasource.HibernateDataSource;
import com.ciprian.product.Product;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.*;

public class AddWindow extends Window {
    private final VerticalLayout form = new VerticalLayout();
    private final Binder<Product> binder = new Binder<>();
    private final TextField nameField = new TextField("Name");
    private final TextField quantityField = new TextField("Quantity");
    private final TextField priceField = new TextField("Price");
    private final Button saveButton = new Button("Save");

    public AddWindow(String n) {
        super(n);
        init();
    }

    private void init() {
        addCloseListener(e1 -> {
            binder.readBean(new Product());
        });

        binder.forField(nameField)
                .withValidator(name -> (name.length() >= 3),
                        "Full name must contain at least three characters")
                .bind(Product::getProductName, Product::setProductName);

        binder.forField(quantityField)
                .withConverter(
                        new StringToIntegerConverter("Must enter a number"))
                .withValidator(quantity -> (quantity > 0),
                        "Not a valid quantity")
                .bind(Product::getQuantity, Product::setQuantity);

        binder.forField(priceField)
                .withConverter(
                        new StringToDoubleConverter("Must enter a number"))
                .withValidator(price -> (price > 0),
                        "Not a valid price")
                .bind(Product::getTotalPriece, Product::setTotalPriece);

        saveButton.addClickListener(event -> {
            try {
                Product product = new Product();
                binder.writeBean(product);
                HibernateDataSource.addProduct(binder.getBean());
                close();
            } catch (ValidationException e) {
                Notification.show("Person could not be saved, " +
                        "please check error messages for each field.");
            }
        });

        form.addComponent(nameField);
        form.addComponent(quantityField);
        form.addComponent(priceField);
        form.addComponent(saveButton);
        setContent(form);

        setSizeUndefined();
        center();
    }

}
