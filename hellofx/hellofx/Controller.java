package hellofx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Callback;


public class Controller extends BaseController {
    
    @FXML
    private ChoiceBox<Country> choiceBox;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private ComboBox<Country> comboBox;

    @FXML
    private ComboBox<Country> imageComboBox;

    @FXML
    private ComboBox<String> priorities;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Button b = new Button("Динамическая кнопка");
        TextField t = new TextField("Динамо текстовое поле");
        
        t.setLayoutY(30);

        choiceBox.setItems(FXCollections.observableArrayList(
            new Country("India", "Asia"),
            new Country("Pakistan", "Asia"),
            new Country("UK", "Europe"),
            new Country("USA", "North Amerika"),
            new Country("Brazil", "South Amerika")
            ));
       
        //choiceBox.setConverter(Country.getStringConverter());
        choiceBox.setOnAction(event -> {
            log("Selected " + choiceBox.getValue());
        });
        choiceBox.visibleProperty().bind(toggleButton.selectedProperty().not());

        Circle circle = new Circle(18, 92, 15);
        add(b, t, circle);
        circle.fillProperty().bind(getColorBinding());

        comboBox.setOnAction(this::onAction);
        comboBox.setEditable(true);
        comboBox.setConverter(Country.getStringConverter());

        ObservableList<Country> countries = FXCollections.observableArrayList(
            new Country("India", "Asia"),
            new Country("Pakistan", "Asia"),
            new Country("UK", "Europe"),
            new Country("Africa", "Africa")
        );

        comboBox.setItems(countries);

        imageComboBox.setItems(countries);
        imageComboBox.setCellFactory(param -> new CountryCell());

        priorities.setCellFactory(param -> {
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                    if ("high".equalsIgnoreCase(item)) {
                        setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), Insets.EMPTY)));
                    } else if ("medium".equalsIgnoreCase(item)) {
                        setBackground(new Background(new BackgroundFill(Color.YELLOW, new CornerRadii(0), Insets.EMPTY)));
                    } else {
                        setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), Insets.EMPTY)));
                    }
                }
            };

            return cell;
        });
    }

    private ObservableValue<? extends Paint> getColorBinding() {
        return new ObjectBinding<Color>() {

            {
                super.bind(choiceBox.showingProperty());
            }

            @Override
            protected Color computeValue() {
                if (choiceBox.isShowing()) { return Color.LIMEGREEN; }
                else { return Color.ORANGERED; }
            }
        };
    }

    @FXML
    public void onAdd(ActionEvent event) {
        choiceBox.getItems().add(new Country("test", "Элемент " + System.currentTimeMillis() % 1000));
        choiceBox.show();
    }

    private void onAction(ActionEvent actionevent1) {
        log("Выбрано: " + comboBox.getValue());
    }
}
