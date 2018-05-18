package com.app.ui.mainlayout.topbuttonslayout;

import com.app.ui.mainlayout.topbuttonslayout.listeners.RefreshButtonClickListener;
import com.app.ui.data.ProductDataManager;
import com.app.ui.mainlayout.addform.AddForm;
import com.app.ui.mainlayout.productgrid.ProductGrid;
import com.app.ui.mainlayout.topbuttonslayout.listeners.AddButtonClickListener;
import com.app.ui.mainlayout.topbuttonslayout.listeners.GridSelectionListener;
import com.app.ui.mainlayout.topbuttonslayout.listeners.RemoveButtonClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class TopButtonsLayout extends HorizontalLayout {

    private final AddForm addForm;
    private final Button removeButton;
    private final Button addButton;
    private final Button refreshButton;
    private final ProductGrid grid;
    private final ProductDataManager productDataManager;

    public TopButtonsLayout(ProductGrid grid) {
        this.grid = grid;
        this.productDataManager = grid.getProductDataManager();
        this.addForm = new AddForm(productDataManager);
        this.addButton = new Button("Add");
        this.removeButton = new Button("Remove");
        this.refreshButton = new Button("Refresh");
        init();
    }

    private void init() {
        grid.addSelectionListener(new GridSelectionListener(removeButton));
        addButton.addClickListener(new AddButtonClickListener(addForm));
        removeButton.addClickListener(new RemoveButtonClickListener(grid, productDataManager));
        refreshButton.addClickListener(new RefreshButtonClickListener(productDataManager));
        removeButton.setEnabled(false);
        addComponents(addButton, removeButton,refreshButton);
    }
}
