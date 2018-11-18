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
import ru.salenko.mtp.dto.BankItem;
import ru.salenko.mtp.dto.CityItem;
import ru.salenko.mtp.dto.CountryItem;
import ru.salenko.mtp.dto.PointItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Просмотровщик подробной информации по банку с возможностью
 * просмотра перечня точек в разрезе страны/города
 */
@SpringComponent
@UIScope
public
class BankViewer extends VerticalLayout implements KeyNotifier {
    private final transient ApiCaller apiCaller;

    /**
     * Перечень всех точек выбранного банка
     */
    private transient List<PointItem> points = new ArrayList<>();
    /**
     * Перечень всех городов, в которых находятся точки выбранного банка
     */
    private transient List<CityItem> cities = new ArrayList<>();

    /* Fields to edit properties in Bank entity */
    private TextField bankInfo = new TextField("Выбранный банк");
    private ComboBox<CountryItem> countriesCombo = new ComboBox<>("Выберите страну присутствия");
    private ComboBox<CityItem> citiesCombo = new ComboBox<>("Выберите город присутствия");
    private Grid<PointItem> pointsGrid;

    @Autowired
    public BankViewer(ApiCaller apiCaller) {
        this.apiCaller = apiCaller;

        bankInfo.setReadOnly(true);
        bankInfo.setWidth("50%");

        countriesCombo.setPlaceholder("Страна не выбрана");
        countriesCombo.setWidth("50%");
        countriesCombo.setItemLabelGenerator(CountryItem::getName);

        countriesCombo.addValueChangeListener(event -> filterByCountry());

        citiesCombo.setPlaceholder("Город не выбран");
        citiesCombo.setWidth("50%");
        citiesCombo.setItemLabelGenerator(CityItem::getName);
        citiesCombo.addValueChangeListener(event -> {
            CityItem city = citiesCombo.getValue();
            if (city != null) {
                List<PointItem> filteredPoints =
                        points.stream().filter(point -> point.getCityCode().equals(city.getCode()))
                                .collect(Collectors.toList());
                pointsGrid.setItems(filteredPoints);
            } else {
                filterByCountry();

            }
        });

        pointsGrid = new Grid<>(PointItem.class);
        pointsGrid.setHeight("300px");
        pointsGrid.setColumns("code", "name");
        pointsGrid.getColumnByKey("code").setWidth("50px").setFlexGrow(0);

        Button cancel = new Button("Cancel");
        HorizontalLayout actions = new HorizontalLayout(cancel);
        add(bankInfo, countriesCombo, citiesCombo, pointsGrid, actions);

        setSpacing(true);

        cancel.addClickListener(e -> viewBank(null));
        setVisible(false);
    }

    private
    void filterByCountry() {
        CountryItem country = countriesCombo.getValue();
        if (country != null) {
            Set<CityItem> filteredCities =
                    cities.stream().filter(city -> city.getCountryCode().equals(country.getCode()))
                            .collect(Collectors.toSet());
            citiesCombo.setItems(filteredCities);
            List<PointItem> filteredPoints =
                    points.stream().filter(point -> filteredCities.stream()
                            .anyMatch(fc -> fc.getCode().equals(point.getCityCode()))).collect(Collectors.toList());
            pointsGrid.setItems(filteredPoints);
        } else {
            citiesCombo.setItems(cities);
            pointsGrid.setItems(points);
        }
    }

    final
    void viewBank(BankItem editedBank) {
        if (editedBank == null) {
            setVisible(false);
            return;
        }
        // Find fresh entity for editing
        BankItem bank = apiCaller.findBankByCode(editedBank.getCode())
                .orElseThrow(() -> new RuntimeException("Неизвестная ошибка приложения"));
        bankInfo.setValue("КОД: " + bank.getCode() + " ;Название: " + bank.getName());

        points = apiCaller.findAllPointsByBank(bank.getCode());
        if (points.isEmpty()) {
            pointsGrid.setItems(Collections.emptyList());
            cities = Collections.emptyList();
            citiesCombo.setItems(Collections.emptyList());
            countriesCombo.setItems(Collections.emptyList());
        } else {
            pointsGrid.setItems(points);
            cities = apiCaller.getCitiesByPoints(points);
            citiesCombo.setItems(cities);

            List<CountryItem> countries = apiCaller.getCountriesByCities(cities);
            countriesCombo.setItems(countries);
        }

        // Focus first name initially
        countriesCombo.focus();

        setVisible(true);
    }
}
