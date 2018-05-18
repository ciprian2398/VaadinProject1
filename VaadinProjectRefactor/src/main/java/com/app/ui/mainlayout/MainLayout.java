package com.app.ui.mainlayout;

import com.app.ui.data.ProductDataManager;
import com.app.ui.mainlayout.productgrid.ProductGrid;
import com.app.ui.mainlayout.topbuttonslayout.TopButtonsLayout;
import com.vaadin.ui.VerticalLayout;

public class MainLayout extends VerticalLayout {

    private final TopButtonsLayout topButtonsLayout;
    private final ProductGrid grid;

    public MainLayout() {
        grid = new ProductGrid(new ProductDataManager());
        topButtonsLayout = new TopButtonsLayout(grid);
        init();
    }

    private void init() {
        addComponent(topButtonsLayout);
        addComponentsAndExpand(grid);
    }
}
