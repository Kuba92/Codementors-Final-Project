package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UserAddView implements Serializable {


    @EJB
    private StoreDAO userDAO;

    private User user;

    private int userId;

    private List<SelectItem> roles;

    /**
     * User to be provided in the registration form
     * @return user from database or a new user (not approved with role user)
     */
    public User getUser () {
        if (user == null) {
            user = userDAO.findUser(userId);
        }
        return user;
    }

    /**
     * Method appended to the Register new user button, if path param = 0, creaetes a new user
     * and saves him to the database or if pathparam != 0 updates the selected user
     * @return
     */
    public User addNewUser () {
        if (userId == 0) {
            userDAO.addUser(user);
        } else {
            userDAO.updateUser(user);
        }
        return user;
    }

    /**
     * returns the id of the user passed to the edit/registration form
     * @return userId
     */
    public int getUserId () {
        return userId;
    }

    /**
     * Method appended to the approve button in the registration/edit form
     * @return boolean
     */
    public boolean approveUser () {
        user.setAccepted(true);
        return true;
    }

    /**
     * setter for user id
     * @param userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Method used to convert role to a select item list to be displayed in the
     * registration/edit form
     * @return
     */
    public List<SelectItem> getRoles () {
        if (roles == null) {
            roles = new ArrayList<>();
            for(User.Role role: User.Role.values()) {
                roles.add(new SelectItem(role, role.name()));
            }
        }
        return roles;
    }

    /**
     * Method used to check if the user is an admin or not
     * @param user
     * @return bool true/false
     */
    public boolean adminCheck (User user) {
        if (user.getRole() == User.Role.ADMIN) {
            return true;
        } else {
            return false;
        }
    }
}
