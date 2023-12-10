package hellofx;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Callback;


public class Controller extends BaseController {
    
    @FXML
    private ListView<Book> booksListView;

    @FXML
    private ListView<String> authorsListView;

    @FXML
    private TextField textField;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        ObservableList<Book> list = FXCollections.observableArrayList(
            new Book("Harry Potter", "J.K. Rowling"),
            new Book("Cronicles of Narnia", "C.S. Lewis")
            );
        booksListView.setItems(list);
        booksListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String[] authors = list.stream()
            .map(Book::getAuthor)
            .sorted()
            .toArray(String[]::new);
        
        authorsListView.setCellFactory(ComboBoxListCell.forListView(authors));
        authorsListView.setEditable(true);
        authorsListView.setItems(FXCollections.observableArrayList(authors));
        
        booksListView.setEditable(true);
        booksListView.setCellFactory(param -> new ListCell<Book>() {
            @Override
            protected void updateItem(Book item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName() + " - " + item.getAuthor());
                }
            }
        }

        );
        booksListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            log("Selected: " + newValue);
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

    @FXML
    public void onValidate(ActionEvent event) {

    }

    private void onAction(ActionEvent actionevent1) {
        log("Выбрано: " + comboBox.getValue());
    }
}
