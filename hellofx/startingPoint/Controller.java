package startingPoint;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class Controller extends BaseController {
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        Button b = new Button("Динамическая кнопка");
        TextField t = new TextField("Динамо текстовое поле");
        t.setLayoutY(50);

        add(b, t);
    }
}
