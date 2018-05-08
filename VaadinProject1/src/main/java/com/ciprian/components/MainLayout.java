package com.ciprian.components;

import com.ciprian.components.mainlayout.CustomGrid;
import com.ciprian.components.mainlayout.TopButtonsLayout;
import com.ciprian.product.Product;
import com.vaadin.event.Action;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.util.Set;

public class MainLayout extends VerticalLayout {
    private final TopButtonsLayout topButtonsLayout = new TopButtonsLayout();
    private final CustomGrid grid = CustomGrid.getInstance();

    public MainLayout() {
        init();
    }

    private void init() {
        grid.addSelectionListener(event -> {
            Set<Product> selected = event.getAllSelectedItems();
            Notification.show(selected.size() + " items selected");
            if (selected.size() > 0) {
                topButtonsLayout.getRemoveButton().setEnabled(true);
            } else {
                topButtonsLayout.getRemoveButton().setEnabled(false);
            }
        });

        addComponent(topButtonsLayout);
        addComponentsAndExpand(grid);
    }

    public CustomGrid getGrid() {
        return grid;
    }
}
