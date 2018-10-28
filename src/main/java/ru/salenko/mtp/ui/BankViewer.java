package ru.salenko.mtp.ui;


import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import ru.salenko.mtp.entity.Bank;
import ru.salenko.mtp.entity.City;
import ru.salenko.mtp.entity.Country;
import ru.salenko.mtp.entity.Point;
import ru.salenko.mtp.repository.BankRepository;
import ru.salenko.mtp.repository.CityRepository;
import ru.salenko.mtp.repository.CountryRepository;
import ru.salenko.mtp.repository.PointRepository;

import java.util.*;
import java.util.stream.Collectors;

@SpringComponent
@UIScope
public class BankViewer extends VerticalLayout implements KeyNotifier {

	private final BankRepository bankRepository;
	private final PointRepository pointRepository;

    private List<Point> points = new ArrayList<>();
    private Set<City> cities = new TreeSet<>();

	/* Fields to edit properties in Bank entity */
	private TextField bankInfo = new TextField("Выбранный банк");
	private ComboBox<Country> countriesCombo = new ComboBox<>("Выберите страну присутствия");
    private ComboBox<City> citiesCombo = new ComboBox<>("Выберите город присутствия");
    private Grid<Point> pointsGrid;

	/* Action buttons */
	private Button cancel = new Button("Cancel");
	private HorizontalLayout actions = new HorizontalLayout(cancel);

	@Autowired
    public BankViewer(BankRepository bankRepository, PointRepository pointRepository,
                      CityRepository cityRepository, CountryRepository countryRepository) {
		this.bankRepository = bankRepository;
        this.pointRepository = pointRepository;

        bankInfo.setReadOnly(true);
        bankInfo.setWidth("50%");

		countriesCombo.setPlaceholder("Страна не выбрана");
		countriesCombo.setWidth("50%");
        // Sets the combobox to show a certain property as the item caption
        countriesCombo.setItemLabelGenerator(Country::getName);


        countriesCombo.addValueChangeListener(event -> {
            Country country = countriesCombo.getValue();
            if (country != null) {
                Set<City> filteredCities = cities.stream().filter(city -> city.getCountry().equals(country)).collect(Collectors.toSet());
                citiesCombo.setItems(filteredCities);
                List<Point> filteredPoints =
                        points.stream().filter( point -> filteredCities.contains(point.getCity())).collect(Collectors.toList());
                pointsGrid.setItems(filteredPoints);
            }
            else {
                citiesCombo.setItems(cities);
                pointsGrid.setItems(points);
            }
        });

        citiesCombo.setPlaceholder("Город не выбран");
        citiesCombo.setWidth("50%");
        citiesCombo.setItemLabelGenerator(City::getName);
        citiesCombo.addValueChangeListener(event -> {
            City city = citiesCombo.getValue();
            if (city != null) {
                List<Point> filteredPoints =
                        points.stream().filter( point -> point.getCity().equals(city)).collect(Collectors.toList());
                pointsGrid.setItems(filteredPoints);
            }
        });

        pointsGrid = new Grid<>(Point.class);
        pointsGrid.setHeight("300px");
        pointsGrid.setColumns("id", "code", "name");
        pointsGrid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        add(bankInfo, countriesCombo, citiesCombo, pointsGrid, actions);

		// Configure and style components
		setSpacing(true);

		// wire action buttons to save, delete and reset
		cancel.addClickListener(e -> viewBank(null));
		setVisible(false);
	}

	final void viewBank(Bank editedBank) {
        if (editedBank == null) {
            setVisible(false);
            return;
        }
    	// Find fresh entity for editing
		Bank bank = bankRepository.findById(editedBank.getId())
                .orElseThrow(() -> new RuntimeException("Неизвестная ошибка приложения"));
        bankInfo.setValue("КОД: " + bank.getCode() + " ;Название: " + bank.getName());

        points = pointRepository.findAllByBank(bank);
        if (points.isEmpty()) {
            pointsGrid.setItems(Collections.emptyList());
            cities = Collections.emptySet();
            citiesCombo.setItems(Collections.emptyList());
            countriesCombo.setItems(Collections.emptyList());
        } else {
            pointsGrid.setItems(points);
            cities = points.stream().map(Point::getCity).collect(Collectors.toSet());
            citiesCombo.setItems(cities);

            Set<Country> countries = cities.stream().map(City::getCountry).collect(Collectors.toSet());
            countriesCombo.setItems(countries);
        }

		// Focus first name initially
        countriesCombo.focus();

        setVisible(true);
	}
}
