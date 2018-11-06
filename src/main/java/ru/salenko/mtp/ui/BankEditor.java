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
import ru.salenko.mtp.dto.BankItem;

/**
 * Форма для создания нового банка.
 */
@SpringComponent
@UIScope
public class BankEditor extends VerticalLayout implements KeyNotifier {

	private final ApiCaller apiCaller;
	/**
	 * The currently edited bank
	 */
	private BankItem bank;

	/* Fields to edit properties in Bank entity */
	private TextField code = new TextField("Code");
	private TextField name = new TextField("Name");

	private Binder<BankItem> binder = new Binder<>(BankItem.class);
	private ChangeHandler changeHandler;

	@Autowired
	public BankEditor(ApiCaller apiCaller) {
		this.apiCaller = apiCaller;

		/* Action buttons */
		Button save = new Button("Save", VaadinIcon.CHECK.create());
		Button cancel = new Button("Cancel");
		HorizontalLayout actions = new HorizontalLayout(save, cancel);
		add(code, name, actions);

		// bind using naming convention
		binder.bindInstanceFields(this);

		// Configure and style components
		setSpacing(true);

		save.getElement().getThemeList().add("primary");

		addKeyPressListener(Key.ENTER, e -> save());

		// wire action buttons to save, delete and reset
		save.addClickListener(e -> save());
		cancel.addClickListener(e -> cancel());
		setVisible(false);
	}

	private void cancel() {
		changeHandler.onChange();
	}

	private void save() {
		apiCaller.save(bank);
		changeHandler.onChange();
	}

	public interface ChangeHandler {
		void onChange();
	}

	final void editBank() {
		bank = new BankItem(null, null);

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
