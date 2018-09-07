package pl.codementors.finalstore.view;


import pl.codementors.finalstore.FinalStoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class UserEdit implements Serializable {

    @EJB
    private FinalStoreDAO dao;

    private User user;

    private int userId;

    public User getUser(){
        if (user == null) {
            user = dao.findUserById(userId);
        }
        return user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void saveUser(){
        if(user.getId() == 0){
            dao.createUser(user);
        } else {
            dao.updateUser(user);
        }
    }
}
