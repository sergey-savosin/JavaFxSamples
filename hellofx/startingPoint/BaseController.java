package startingPoint;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public abstract class BaseController implements Initializable {

    @FXML
    private AnchorPane pane;

    @FXML
    private TextArea log;

    protected void log(Object obj) {
        log.appendText(obj.toString() + System.lineSeparator());
    }

    protected void add(Node... nodes) {
        pane.getChildren().addAll(nodes);
    }
}
