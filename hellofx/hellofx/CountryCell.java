package hellofx;

import java.io.InputStream;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CountryCell extends ListCell<Country> {

    @Override
    protected void updateItem(Country item, boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setGraphic(null);
        } else {
            String filename = item.getContinent().toLowerCase().trim() + ".png";
            InputStream resourceAsStream = getClass().getResourceAsStream(filename);
            Image image = new Image(resourceAsStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(25);
            imageView.setFitHeight(25);

            setGraphic(new HBox(imageView, new Label(item.getName())));
        }
        setText("1212");
    }
}
