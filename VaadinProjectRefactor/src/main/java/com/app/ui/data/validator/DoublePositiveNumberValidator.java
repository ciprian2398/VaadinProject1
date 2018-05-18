package com.app.ui.data.validator;


import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class DoublePositiveNumberValidator implements Validator<Double> {
    @Override
    public ValidationResult apply(Double value, ValueContext context) {
        if (value > 0) {
            return ValidationResult.ok();
        } else {
            return ValidationResult.error(
                    "Must be more than 0 .");
        }
    }
}
