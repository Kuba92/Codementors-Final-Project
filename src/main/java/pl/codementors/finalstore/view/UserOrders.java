package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Order;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class UserOrders implements Serializable {

    @EJB
    private StoreDAO dao;

    @EJB
    private UserService service;

    private List<Order> userOrders;

    public List<Order> getUserOrders() {
        if (userOrders == null) {
            userOrders = dao.findUserOrders(service.getCurrentlyLoggedUser().get().getId());
        }
        return userOrders;
    }
}
