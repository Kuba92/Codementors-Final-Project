package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Order;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * View class used for user orders.
 */
@Named
@ViewScoped
public class UserOrders implements Serializable {

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
     * List of user orders.
     */
    private List<Order> userOrders;

    public List<Order> getUserOrders() {
        if (userOrders == null) {
            userOrders = dao.findUserOrders(service.getCurrentlyLoggedUser().get().getId());
        }
        return userOrders;
    }
}
