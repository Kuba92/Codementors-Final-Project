package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * View class used to all products list.
 */
@Named
@ViewScoped
public class ProductsList implements Serializable {


    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO dao;

    /**
     * Bean EJB used to get currently logged user.
     */
    @EJB
    private UserService userService;

    /**
     * Injected basket.
     */
    @Inject
    private BasketView basketView;

    /**
     * List of all products.
     */
    private List<Product> productList;

    public List<Product> getProductList() {
        if (productList == null) {
            productList = dao.findAllProducts();
        }
        return productList;
    }

    /**
     * Method adding product to basket.
     *
     * @param product Product to be added.
     */
    public void addProductToBasket(Product product) {
        if (!basketView.getProductsInBasket().contains(product)) {
            basketView.addProductToBasket(product);
        }
    }

    /**
     * Method checking if product is already added to basket or is not available.
     *
     * @param product Product to be checked.
     * @return True if product is added or not available. Else returns false.
     */
    public boolean addedToBasketOrNotAvailable(Product product) {
        return (basketView.getProductsInBasket().contains(product) || !product.isAvailable());
    }

    /**
     * Method checking if logged user is owner of product on list.
     *
     * @param product Product to be checked.
     * @return True if logged user is owner. Else returns false.
     */
    public boolean isItMyOwnProduct(Product product) {
        return (userService.getCurrentlyLoggedUser().get().getNickname().equals(product.getSeller().getNickname()));
    }


}
