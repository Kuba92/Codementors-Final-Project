package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;

@Named
@ViewScoped
public class NewProduct implements Serializable {

    @EJB
    private StoreDAO dao;

    @Inject
    private Principal principal;

    @EJB
    private UserService service;

    private User seller;

    private Product product;


    public Product getProduct() {
        if (product == null) {
            product = new Product();
        }
        return product;
    }

    public void saveProduct() {
        seller = service.getCurrentlyLoggedUser().get();
        product.setSeller(seller);
        dao.addProduct(product);
    }
}
