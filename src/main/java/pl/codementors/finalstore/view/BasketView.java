package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Order;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Application scoped basket view.
 */
@Named
@ApplicationScoped
public class BasketView implements Serializable {

    /**
     * EJB store to communicate with db.
     */
    @EJB
    private StoreDAO store;

    @EJB
    private UserService userService;

    /**
     * List of products added to basket.
     */
    private List<Product> productsInBasket = new ArrayList<>();

    /**
     * Placeholder for order.
     */
    private Order order;

    /**
     * Potential customer.
     */
    private User customer;


    public List<Product> getProductsInBasket() {
        return productsInBasket;
    }

    public void setProductsInBasket(List<Product> productsInBasket) {
        this.productsInBasket = productsInBasket;
    }

    /**
     * Method adding product to basket.
     *
     * @param product Product to be added.
     */
    public void addProductToBasket(Product product) {
        if (!productsInBasket.contains(product)) {
            productsInBasket.add(product);
        }
    }

    /**
     * Method removing product from basket.
     *
     * @param product Product to be removed.
     */
    public void removeProductFromBasket(Product product) {
        productsInBasket.remove(product);
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    /**
     * Method making new order.
     */
    public void makeOrder() {
        customer = userService.getCurrentlyLoggedUser().get();
        order = new Order();
        order.setCustomer(customer);
        order.setProducts(productsInBasket);
        for (Product product : productsInBasket) {
            product.setAvailable(false);
            store.updateProduct(product);
        }
        store.addOrder(order);
        productsInBasket = new ArrayList<>();
    }
}
