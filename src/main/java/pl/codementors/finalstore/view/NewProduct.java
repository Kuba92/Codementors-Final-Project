package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * View class used for adding new product.
 */
@Named
@ViewScoped
public class NewProduct implements Serializable {

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO dao;

    /**
     * Bean EJB used to get currently logged user.
     */
    @EJB
    private UserService service;

    /**
     * Users that sells a product.
     */
    private User seller;

    /**
     * Product to be added.
     */
    private Product product;


    public Product getProduct() {
        if (product == null) {
            product = new Product();
        }
        return product;
    }

    /**
     * Method saving product.
     */
    public void saveProduct() {
        seller = service.getCurrentlyLoggedUser().get();
        product.setSeller(seller);
        product.setAvailable(true);
        dao.addProduct(product);
    }
}
