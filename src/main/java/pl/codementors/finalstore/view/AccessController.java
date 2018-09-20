package pl.codementors.finalstore.view;

import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Access controller used to get currently logged user.
 */
@Named
@ViewScoped
public class AccessController implements Serializable {

    /**
     * Bean EJB used get user data.
     */
    @EJB
    private UserService service;

    /**
     * Method getting current user. Used to check if logged user is accepted.
     *
     * @return Currently logged user object.
     */
    public User getCurrentUser() {
        if (service.getCurrentlyLoggedUser().isPresent()) {
            return service.getCurrentlyLoggedUser().get();
        }
        return User.createAnnonymousUser();
    }
}
