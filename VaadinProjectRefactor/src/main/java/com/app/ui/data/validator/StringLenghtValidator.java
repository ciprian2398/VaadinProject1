package com.app.ui.data.validator;

import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

public class StringLenghtValidator implements Validator<String> {
    private final int minValue;

    public StringLenghtValidator(int minValue) {
        this.minValue = minValue;
    }

    @Override
    public ValidationResult apply(String value, ValueContext context) {
        if (value.length() >= minValue) {
            return ValidationResult.ok();
        } else {
            return ValidationResult.error(
                    "Must be more than 3 characters long.");
        }
    }
}
