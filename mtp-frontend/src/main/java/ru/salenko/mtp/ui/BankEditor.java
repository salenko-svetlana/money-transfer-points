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

	private final transient ApiCaller apiCaller;
	private transient BankItem bank;

	private TextField code = new TextField("Code");
	private TextField name = new TextField("Name");

	private Binder<BankItem> binder = new Binder<>(BankItem.class);
	private transient ChangeHandler changeHandler;

	@Autowired
	public BankEditor(ApiCaller apiCaller) {
		this.apiCaller = apiCaller;

		Button save = new Button("Save", VaadinIcon.CHECK.create());
		Button cancel = new Button("Cancel");
		HorizontalLayout actions = new HorizontalLayout(save, cancel);
		add(code, name, actions);

		binder.bindInstanceFields(this);

		setSpacing(true);

		save.getElement().getThemeList().add("primary");

		addKeyPressListener(Key.ENTER, e -> save());

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
		binder.setBean(bank);
		setVisible(true);
		code.focus();
	}

	void setChangeHandler(ChangeHandler h) {
		changeHandler = h;
	}

}
