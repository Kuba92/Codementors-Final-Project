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
    UserService service;

    public User getCurrentUser() {
        return service.getCurrentlyLoggedUser().get();
    }
}
