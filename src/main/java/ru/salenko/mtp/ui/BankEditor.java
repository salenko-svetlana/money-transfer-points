package ru.salenko.mtp.ui;


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
import ru.salenko.mtp.entity.Bank;
import ru.salenko.mtp.repository.BankRepository;

@SpringComponent
@UIScope
public class BankEditor extends VerticalLayout implements KeyNotifier {

	private final BankRepository repository;

	/**
	 * The currently edited bank
	 */
	private Bank bank;

	/* Fields to edit properties in Bank entity */
	private TextField code = new TextField("Code");
	private TextField name = new TextField("Name");

	/* Action buttons */
	private Button save = new Button("Save", VaadinIcon.CHECK.create());
	private Button cancel = new Button("Cancel");
	private Button delete = new Button("Delete", VaadinIcon.TRASH.create());
	private HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

	private Binder<Bank> binder = new Binder<>(Bank.class);
	private ChangeHandler changeHandler;

	@Autowired
	public BankEditor(BankRepository repository) {
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
		cancel.addClickListener(e -> editBank(bank));
		setVisible(false);
	}

	private void delete() {
		repository.delete(bank);
		changeHandler.onChange();
	}

	private void save() {
		repository.save(bank);
		changeHandler.onChange();
	}

	public interface ChangeHandler {
		void onChange();
	}

	final void editBank(Bank editedBank) {
		if (editedBank == null) {
			setVisible(false);
			return;
		}
		final boolean persisted = editedBank.getId() != null;
		if (persisted) {
			// Find fresh entity for editing
			bank = repository.findById(editedBank.getId()).orElse(null);
		}
		else {
			bank = editedBank;
		}
		cancel.setVisible(persisted);

		// Bind bank properties to similarly named fields
		// Could also use annotation or "manual binding" or programmatically
		// moving values from fields to entities before saving
		binder.setBean(bank);

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
