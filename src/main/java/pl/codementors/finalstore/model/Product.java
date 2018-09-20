package pl.codementors.finalstore.model;


import javax.persistence.*;
import java.util.Objects;


/**
 * Entity class representing a product in our store.
 */
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private int price;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id", nullable = false)
    private User seller;

    @Column(name = "availability")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    public Product() {
    }

    public Product(String name, String description, int price, User seller, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.seller = seller;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                isAvailable == product.isAvailable &&
                Objects.equals(name, product.name) &&
                Objects.equals(description, product.description) &&
                Objects.equals(seller, product.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, seller, isAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", seller=" + seller +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
