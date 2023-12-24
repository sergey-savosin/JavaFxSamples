package hellofx;

import javafx.scene.control.ListCell;

public class Book {
    private String name;
    private String author;
    private ListCell<Book> cell;

    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String toString() {
        return "Book " + name + "\\" + author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public void setCell(ListCell<Book> listCell) {
        this.cell = listCell;
    }

    public ListCell<Book> getCell() {
        return cell;
    }
}