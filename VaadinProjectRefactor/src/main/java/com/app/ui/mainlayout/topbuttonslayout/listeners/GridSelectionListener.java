package com.app.ui.mainlayout.topbuttonslayout.listeners;

import com.app.backend.model.Product;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;

public class GridSelectionListener implements SelectionListener<Product> {

    private final Button removeButton;

    public GridSelectionListener(Button removeButton) {
        this.removeButton = removeButton;
    }

    @Override
    public void selectionChange(SelectionEvent<Product> event) {
        int selectedSize = event.getAllSelectedItems().size();

        Notification.show(selectedSize + " items selected");

        if (selectedSize > 0) {
            removeButton.setEnabled(true);
        } else {
            removeButton.setEnabled(false);
        }
    }
}
