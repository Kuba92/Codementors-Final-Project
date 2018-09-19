package pl.codementors.finalstore.view;


import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * View class used for list of all users.
 */
@Named
@ViewScoped
public class AllUsersList implements Serializable {

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
     * Injected request. Needed for logout option.
     */
    @Inject
    private HttpServletRequest request;

    /**
     * List of users.
     */
    private List<User> users;


    /**
     * Method filling placeholder with all existing users.
     *
     * @return List of users.
     */
    public List<User> getUsers() {
        if (users == null) {
            users = dao.findAllUsers();
        }
        return users;
    }

    /**
     * Method getting current user. Used to check if logged user is accepted.
     *
     * @return Currently logged user object.
     */
    public User getCurrentUser() {
        return userService.getCurrentlyLoggedUser().get();
    }

    /**
     * Method removing user.
     *
     * @param user User to be removed.
     */
    public void removeUser(User user) {
        users.remove(user);
        dao.removeUser(user);
    }

    /**
     * Logout method.
     */
    public void logout() {
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to accept user.
     *
     * @param user User to be accepted.
     * @param b    True by default.
     */
    public void acceptUser(User user, boolean b) {
        user.setAccepted(b);
        dao.updateUser(user);
    }

    /**
     * Method checking if user is already accepted.
     *
     * @param user User to check.
     * @return Returns true if accepted. Else returns false.
     */
    public boolean isUserAlreadyAccepted(User user) {
        if (user.isAccepted()) {
            return true;
        } else {
            return false;
        }
    }
}
