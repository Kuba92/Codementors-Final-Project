package pl.codementors.finalstore.service;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

/**
 * User service class used to get currently logged user.
 */
@Stateful
public class UserService {

    /**
     * Logged principal.
     */
    @Inject
    private Principal principal;

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO dao;

    /**
     * Method getting currently logged user.
     *
     * @return Optional of user or null.
     */
    public Optional<User> getCurrentlyLoggedUser() {
        if (Objects.isNull(principal)) {
            return Optional.empty();
        }

        return dao.findUserByNickname(principal.getName());
    }
}
