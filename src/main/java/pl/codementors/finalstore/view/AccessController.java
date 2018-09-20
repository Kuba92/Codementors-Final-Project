package pl.codementors.finalstore.view;

import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class AccessController implements Serializable {

    @EJB
    private UserService service;

    public User getCurrentUser() {
        if (service.getCurrentlyLoggedUser().isPresent()) {
            return service.getCurrentlyLoggedUser().get();
        }
        return User.createAnnonymousUser();
    }
}
