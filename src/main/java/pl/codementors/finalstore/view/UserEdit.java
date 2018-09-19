package pl.codementors.finalstore.view;


import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class UserEdit implements Serializable {

    @EJB
    private StoreDAO dao;

    private User user;

    private int userId;

    private List<SelectItem> rolesList;

    public User getUser() {
        if (user == null) {
            user = dao.findUser(userId);
        }
        return user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void saveUser() {

        if (user.getId() == 0) {
            dao.addUser(user);
        } else {
            dao.updateUser(user);

        }
    }

    public List<SelectItem> getRoles() {
        if (rolesList == null) {
            rolesList = new ArrayList<>();
            for (User.Role roles : User.Role.values()) {
                rolesList.add(new SelectItem(roles, roles.name()));
            }
        }
        return rolesList;
    }

}
