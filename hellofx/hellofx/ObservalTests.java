package hellofx;

//import static org.junit.Assert.fail;
import org.junit.Test;

import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.scene.shape.Rectangle;


public class ObservalTests {
    @Test
    public void listTest() {
        ObservableList<Rectangle> list = FXCollections.observableArrayList();

        list.addListener((ListChangeListener<Rectangle>) System.out::println);
        list.addListener((InvalidationListener) System.out::println);
        
        list.add(new Rectangle());
        list.add(new Rectangle());

        Rectangle r = list.get(1);
        r.setWidth(20);

        //System.out.println(list);
    }

    @Test
    public void setTest() {
        ObservableSet<String> set = FXCollections.observableSet();

        set.addListener((SetChangeListener<String>) System.out::println);
        set.addListener((InvalidationListener) System.out::println);
        set.add("First - set");
        set.add("Second - set");
    }

    @Test
    public void mapTest() {
        ObservableMap<String, String> map = FXCollections.observableHashMap();

        map.addListener((MapChangeListener<String, String>) System.out::println);
        map.addListener((InvalidationListener) System.out::println);

        map.put("First - map", "A");
        map.put("Second - map", "B");

        //System.out.println(map);
    }
}
