package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * View class used to user products.
 */
@Named
@ViewScoped
public class UserProducts implements Serializable {

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
     * List of user products.
     */
    private List<Product> userProducts;

    public List<Product> getUserProducts() {
        if (userProducts == null) {
            userProducts = dao.findUserProducts(service.getCurrentlyLoggedUser().get().getId());
        }
        return userProducts;
    }

    /**
     * Method removing product.
     *
     * @param product Product to be removed.
     */
    public void deleteProduct(Product product) {
        userProducts.remove(product);
        dao.removeProduct(product);
    }
}
