package CiscoSDE123YOE;
import java.util.*; 


// ---------------------- PRODUCT -----------------------
class Product {
    private int id; 
    private String name; 
    private double price; 

    public Product(int id, String name, double price) {
        this.id = id; 
        this.name = name; 
        this.price = price; 
    }

    public int getId() {
        return id; 
    }

    public String getName() {
        return name; 
    }

    public double getPrice() {
        return price; 
    }

    public void setId(int id) {
        this.id = id; 
    }

    public void setName(String name) {
        this.name = name; 
    }

    public void setPrice(double price) {
        this.price = price; 
    }
}

// --------------------- INVENTORY ----------------------
class Inventory {
    // Product id : int -> stock quanity : int 
    private Map<Integer, Integer> stock = new HashMap<>(); 

    public void addStock(Product p, int quanity) {
        stock.put(p.getId(), stock.getOrDefault(p.getId(), 0) + quanity); 
    }

    public boolean isAvailable(Product p, int quanity) {
        return stock.get(p.getId()) >= quanity; 
    }

    public void reduce(Product p, int quanity) {
        stock.put(p.getId(), stock.get(p.getId()) - quanity); 
    }
}

// ------------------- CART ITEM ---------------------------- 
class CartItem {
    Product product; 
    int quanity; 

    CartItem(Product product, int quanity) {
        this.product = product; 
        this.quanity = quanity; 
    }
}

class Cart {
    private List<CartItem> items = new ArrayList<>(); 

    public void addItem(Product p, int quanity) {
        // If product exists inside our cart them simply for that product->id we increase the qty 
        for (CartItem item : items) {
            if (item.product.getId() == p.getId()) {
                item.quanity += quanity; 
                return; 
            }
        }
        // If not then return do not work we add at the end 
        items.add(new CartItem(p, quanity)); 
    }

    public List<CartItem> getItems() {
        return items; 
    }

    public double getTotal() {
        double totalPrice = 0; 
        for (CartItem item : items) {
            totalPrice += item.product.getPrice() * item.quanity; 
        }
        return totalPrice; 
    }
}

// PAYMENT STRATEGY 
// OPEN CLOSED PRINCIPLE 
interface PaymentStrategy {
    void pay(double amount); 
}

class CreditCartPayment implements PaymentStrategy {
    @Override 
    public void pay(double amount) {
        System.out.println("Paid amount : " + amount + " vai Credit Card."); 
    }
}

class UPIPayment implements PaymentStrategy {
    @Override 
    public void pay(double amount) {
        System.out.println("Paid amount: " + amount + " via UPI Payment"); 
    }
}

// --------------- USER ------------------------
class User {
    private int id; 
    private String name; 
    private double balance; 
    private Cart cart = new Cart(); 

    public User(int id, String name, double balance) {
        this.id = id; 
        this.name = name; 
        this.balance = balance; 
    }

    public Cart getCart() {
        return cart; 
    }
    public void setCart(Cart cart) {
        this.cart = cart; 
    }

    public double getBalance() {
        return balance; 
    }
    public void setBalance(double balance) {
        this.balance = balance; 
    }

    public void deductBalance(double amount) {
        balance -= amount; 
    }
}

// ---------------------- ORDER -------------------------
class Order {
    private User user; 
    private Inventory inventory; 
    private PaymentStrategy paymentStrategy; 

    public Order(User user, Inventory inventory, PaymentStrategy paymentStrategy) {
        this.user = user; 
        this.inventory = inventory; 
        this.paymentStrategy = paymentStrategy; 
    }

    public void placeOrder() {
        double total = user.getCart().getTotal(); 

        // Checking stock 
        for (CartItem item : user.getCart().getItems()) {
            if (!inventory.isAvailable(item.product, item.quanity)) {
                throw new RuntimeException("Stock not available");
            }
        }

        // Check balance 
        if (user.getBalance() < total) {
            throw new RuntimeException("Insufficient balance"); 
        }

        // Reduction of Stock 
        for (CartItem item : user.getCart().getItems()) {
            inventory.reduce(item.product, item.quanity);
        }

        // Deducting balance 
        user.deductBalance(total);

        // Payment 
        paymentStrategy.pay(total);
        System.out.println("Order placed successfully.");
    }
}

public class EcommerceSystem {
    
    
}
