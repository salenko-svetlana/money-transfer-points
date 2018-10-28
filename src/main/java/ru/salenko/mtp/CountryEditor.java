package ru.salenko.mtp;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class CountryEditor extends VerticalLayout implements KeyNotifier {

	private final CountryRepository repository;

	/**
	 * The currently edited customer
	 */
	private Country country;

	/* Fields to edit properties in Customer entity */
	private TextField code = new TextField("Code");
	private TextField name = new TextField("Name");

	/* Action buttons */
	private Button save = new Button("Save", VaadinIcon.CHECK.create());
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	private Binder<Country> binder = new Binder<>(Country.class);
	private ChangeHandler changeHandler;

	@Autowired
	public CountryEditor(CountryRepository repository) {
		this.repository = repository;

		add(code, name, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");
		delete.getElement().getThemeList().add("error");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		delete.addClickListener(e -> delete());
		cancel.addClickListener(e -> editCountry(country));
		setVisible(false);
	}

	private void delete() {
		repository.delete(country);
		changeHandler.onChange();
	}

	private void save() {
		repository.save(country);
		changeHandler.onChange();
	}

	public interface ChangeHandler {
		void onChange();
	}

	final void editCountry(Country c) {
		if (c == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = c.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			country = repository.findById(c.getId()).orElse(null);
		}
		else {
			country = c;
		}
		cancel.setVisible(persisted);

		// Bind customer properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(country);

		setVisible(true);

		// Focus first name initially
		code.focus();
	}

	void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		changeHandler = h;
	}

}
