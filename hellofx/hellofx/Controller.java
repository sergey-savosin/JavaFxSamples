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
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Callback;
import javafx.util.StringConverter;


public class Controller extends BaseController {
    
    @FXML
    private ListView<Book> booksListView;

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
        
        booksListView.setEditable(true);
        booksListView.setCellFactory(TextFieldListCell.forListView(new StringConverter<Book>() {

            @Override
            public Book fromString(String string) {
                if (string == null)
                    return null;
                
                if (string.contains(",")) {
                    String[] splits = string.split(",");
                    return new Book(splits[0], splits[1]);
                } else {
                    return new Book(string, null);
                }
            }

            @Override
            public String toString(Book object) {
                return object.getName() + (object.getAuthor() == null ? "" : ", " + object.getAuthor());
            }
            
        }));

        booksListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue)->{
            log("Selected: " + newValue);
        });
    }

    @FXML
    public void onValidate(ActionEvent event) {
    }
}
