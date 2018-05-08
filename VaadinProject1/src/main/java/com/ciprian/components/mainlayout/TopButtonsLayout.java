package com.ciprian.components.mainlayout;

import com.ciprian.components.windows.AddWindow;
import com.ciprian.datasource.HibernateDataSource;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

public class TopButtonsLayout extends HorizontalLayout {
    private final Button removeButton = new Button("Remove");
    private final Button addButton = new Button("Add");
    private final AddWindow addWindow = new AddWindow("Add Item");
    private final CustomGrid grid = CustomGrid.getInstance();

    public TopButtonsLayout() {
        init();
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    private void init() {
        addWindow.addCloseListener(e1 -> {
            grid.refreshGrid();
        });


        addButton.addClickListener(e -> {
            if (!UI.getCurrent().getWindows().contains(addWindow)) {
                UI.getCurrent().addWindow(addWindow);
            }
        });

        removeButton.addClickListener(e -> {
            grid.getSelectedItems().forEach(HibernateDataSource::deleteProduct);
            grid.refreshGrid();
        });

        removeButton.setEnabled(false);
        addComponent(addButton);
        addComponent(removeButton);
    }
}
