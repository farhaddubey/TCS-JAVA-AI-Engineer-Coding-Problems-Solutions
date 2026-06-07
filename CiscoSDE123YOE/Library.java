package CiscoSDE123YOE;
import java.util.*; 


// BASE CLASS 
abstract class Item {
    String title; 
    String author; 
    int year; 

    public Item(String title, String author, int year) {
        this.title = title; 
        this.author = author; 
        this.year = year; 
    }

    abstract void display(); 
}

// ----------------- BOOK --------------------
class Book extends Item {
    String genre; 
    String isbn; 

    public Book(String genre, String isbn, String title, String author, int year) {
        super(title, author, year); 
        this.genre = genre; 
        this.isbn = isbn; 
    }

    void display() {
        System.out.println(title + " by " + author + "(" + year + ")");
        System.out.println("Genre is : " + genre + " & ISBN is : " + isbn); 
    }
}

// ----------------- DVD -----------------------
class DVD extends Item { 
    int duration; 

    public DVD(String title, String author, int year, int duration) {
        super(title, author, year); 
        this.duration = duration; 
    }

    void display() {
        System.out.println(title + " by " + author + " (" + year + ")"); 
        System.out.println("Duration is : " + duration); 
    }
}

// ----------------- LIBRARY --------------------
public class Library {
    List<Item> items = new ArrayList<>(); 

    public void addItem(Item item) {
        items.add(item); 
    }
    public void showAll() {
        for (Item item: items) {
            item.display();  // Polymorphism here 
        }
    }
}