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
import ru.salenko.mtp.entity.Bank;
import ru.salenko.mtp.repository.BankRepository;

@Route
public class MainView extends VerticalLayout {

	private final BankRepository bankRepository;
	private final Grid<Bank> grid;

	private final TextField filter;

    public MainView(BankRepository bankRepository, BankEditor editor, BankViewer viewer) {
		this.bankRepository = bankRepository;
    	this.grid = new Grid<>(Bank.class);
		this.filter = new TextField();
        Button addNewBankButton = new Button("New bank", VaadinIcon.PLUS.create());

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBankButton);
		add(actions, grid, editor, viewer);

		grid.setHeight("300px");
		grid.setColumns("id", "code", "name");
		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filter by name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listBank(e.getValue()));

		// Connect selected Bank to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> viewer.viewBank(e.getValue()));

		// Instantiate and edit new Bank the new button is clicked
		addNewBankButton.addClickListener(e -> editor.editBank(new Bank("", "")));

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
			grid.setItems(bankRepository.findAll());
		}
		else {
			grid.setItems(bankRepository.findByCodeStartsWithIgnoreCase(filterText));
		}
	}

}
