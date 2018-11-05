package ru.salenko.mtp.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;
import ru.salenko.mtp.controller.BankController;
import ru.salenko.mtp.dto.BankItem;
import ru.salenko.mtp.entity.Bank;

import java.util.stream.Collectors;

@Route
public class MainView extends VerticalLayout {

	private final BankController bankController;
	private final Grid<BankItem> grid;

	private final TextField filter;

    public MainView(BankController bankController, BankEditor editor, BankViewer viewer) {
		this.bankController = bankController;
    	this.grid = new Grid<>(BankItem.class);
		this.filter = new TextField();
        Button addNewBankButton = new Button("New bank", VaadinIcon.PLUS.create());

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBankButton);
		add(actions, grid, editor, viewer);

		grid.setHeight("300px");
		grid.setColumns("code", "name");
		grid.getColumnByKey("code").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filter by name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listBank(e.getValue()));

		// Connect selected Bank to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> viewer.viewBank(e.getValue()));

		// Instantiate and edit new Bank the new button is clicked
		addNewBankButton.addClickListener(e -> editor.editBank());

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listBank(filter.getValue());
		});

		// Initialize listing
		listBank(null);
	}

	private void listBank(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(bankController.getBankItems());
		}
		else {
			grid.setItems(bankController.getBankItems().stream()
                    .filter(bankItem -> bankItem.getCode().startsWith(filterText.toUpperCase())).collect(Collectors.toList()));
		}
	}

}
