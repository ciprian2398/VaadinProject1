package com.app.ui.data.binder;

import com.app.backend.model.Product;
import com.vaadin.data.Binder;
import com.app.ui.data.validator.DoublePositiveNumberValidator;
import com.app.ui.data.validator.IntegerPositiveNumberValidator;
import com.app.ui.data.validator.StringLenghtValidator;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.ui.TextField;

public class ProductBinder extends Binder<Product> {
    private final TextField nameField;
    private final TextField quantityField;
    private final TextField priceField;

    public ProductBinder(TextField nameField, TextField quantityField, TextField priceField) {
        this.nameField = nameField;
        this.quantityField = quantityField;
        this.priceField = priceField;
        init();
    }

    private void init() {

        forField(nameField)
                .withNullRepresentation("")
                .withValidator(new StringLenghtValidator(3))
                .bind(Product::getProductName, Product::setProductName);

        forField(quantityField)
                .withNullRepresentation("")
                .withConverter(new StringToIntegerConverter("Must enter a number"))
                .withValidator(new IntegerPositiveNumberValidator())
                .bind(Product::getQuantity, Product::setQuantity);

        forField(priceField)
                .withNullRepresentation("")
                .withConverter(new StringToDoubleConverter("Must enter a number"))
                .withValidator(new DoublePositiveNumberValidator())
                .bind(Product::getTotalPriece, Product::setTotalPriece);

    }
}
