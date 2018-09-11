package pl.codementors.finalstore.service;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Stateful
public class UserService {

    @Inject
    private Principal principal;

    @EJB
    private StoreDAO dao;

    public Optional<User> getCurrentlyLoggedUser() {
        if (Objects.isNull(principal)) {
            return Optional.empty();
        }

        return dao.findUserByNickname(principal.getName());
    }
}
