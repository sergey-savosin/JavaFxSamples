package hellofx;

import java.io.InputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.StringConverter;

public class Country {
    private String name;
    private String continent;
    private long population;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
        this.population = (System.currentTimeMillis() + name.hashCode()) % 100000;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + ", " + continent + ", " + population;
    }

    public static StringConverter<Country> getStringConverter(){
        return new StringConverter<Country>() {
            @Override
            public String toString(Country object) {
                if (object == null) {
                    return "null";
                }
                return object.name + ", " + object.continent + ", [" + object.population + "]";
            }

            @Override
            public Country fromString(String string) {
                if (string == null) {
                    return null;
                }
                String[] splits = string.split(",");
                String name = splits[0].trim();
                String continent = splits[1].trim();
                long population = Long.parseLong(splits[2].trim().replace("[","").replace("]",""));

                Country country = new Country(name, continent);
                country.population = population;
                return country;
            }
        };
    }

    public ImageView getImageView() {
        String filename = continent.toLowerCase().trim() + ".png";
        InputStream resourceAsStream = getClass().getResourceAsStream(filename);
        Image image = new Image(resourceAsStream);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);

        return imageView;
    }

    public String getContinent() {
        return continent;
    }
}
