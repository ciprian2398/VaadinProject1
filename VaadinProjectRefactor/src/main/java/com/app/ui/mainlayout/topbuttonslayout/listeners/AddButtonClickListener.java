package com.app.ui.mainlayout.topbuttonslayout.listeners;

import com.app.ui.mainlayout.addform.AddForm;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;

public class AddButtonClickListener implements Button.ClickListener {

    private final AddForm addForm;

    public AddButtonClickListener(AddForm addForm) {
        this.addForm = addForm;
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if (!UI.getCurrent().getWindows().contains(addForm.getWindow())) {
            addForm.setBean();
            addForm.openWindow();
        }
    }
}
