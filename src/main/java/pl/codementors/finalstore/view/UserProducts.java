package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserProducts implements Serializable {

    @EJB
    StoreDAO dao;

    @EJB
    UserService service;

    private List<Product> userProducts;

    public List<Product> getUserProducts() {
        if (userProducts == null) {
            userProducts = dao.findUserProducts(service.getCurrentlyLoggedUser().get().getId());
        }
        return userProducts;
    }
}
