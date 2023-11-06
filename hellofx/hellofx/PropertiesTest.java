package hellofx;

import org.junit.Test;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class PropertiesTest {
    
    @Test
    public void simpleTest() {
        IntegerProperty v = new SimpleIntegerProperty(10);
        v.set(50);
        System.out.println(v);

        v.addListener((observable, oldValue, newValue) -> {
            System.out.println("Value changed from " + oldValue + " to " + newValue);
        });

        v.addListener(observable -> {
            System.out.println("New value is " + observable);
        });

        v.set(55);
    }

    @Test
    public void binding() {
        IntegerProperty source = new SimpleIntegerProperty(10);
        IntegerProperty dependent = new SimpleIntegerProperty(11);

        dependent.bindBidirectional(source);

        System.out.println(dependent.get());
        source.set(101);
        System.out.println(dependent.get());
        dependent.set(19);

        System.out.println(source);
    }
}
