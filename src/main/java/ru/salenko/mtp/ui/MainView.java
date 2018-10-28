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
import ru.salenko.mtp.entity.Country;
import ru.salenko.mtp.repository.CountryRepository;

@Route
public class MainView extends VerticalLayout {

	private final CountryRepository repo;

	private final CountryEditor editor;

	private final Grid<Country> grid;

	private final TextField filter;

	private final Button addNewBtn;

	public MainView(CountryRepository repo, CountryEditor editor) {
		this.repo = repo;
		this.editor = editor;
		this.grid = new Grid<>(Country.class);
		this.filter = new TextField();
		this.addNewBtn = new Button("New country", VaadinIcon.PLUS.create());

		// build layout
		HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
		add(actions, grid, editor);

		grid.setHeight("300px");
		grid.setColumns("id", "code", "name");
		grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

		filter.setPlaceholder("Filter by name");

		// Hook logic to components

		// Replace listing with filtered content when user changes filter
		filter.setValueChangeMode(ValueChangeMode.EAGER);
		filter.addValueChangeListener(e -> listCountries(e.getValue()));

		// Connect selected Country to editor or hide if none is selected
		grid.asSingleSelect().addValueChangeListener(e -> editor.editCountry(e.getValue()));

		// Instantiate and edit new Country the new button is clicked
		addNewBtn.addClickListener(e -> editor.editCountry(new Country("", "","")));

		// Listen changes made by the editor, refresh data from backend
		editor.setChangeHandler(() -> {
			editor.setVisible(false);
			listCountries(filter.getValue());
		});

		// Initialize listing
		listCountries(null);
	}

	private void listCountries(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repo.findAll());
		}
		else {
			grid.setItems(repo.findByCodeStartsWithIgnoreCase(filterText));
		}
	}

}
