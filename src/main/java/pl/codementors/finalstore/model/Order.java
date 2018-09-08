package pl.codementors.finalstore.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private User customer;

    @Column
    private String adress;

    @OneToMany
    @JoinColumn(name = "product", referencedColumnName = "id")
    private List<Product> products;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
