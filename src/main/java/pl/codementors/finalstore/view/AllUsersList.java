package pl.codementors.finalstore.view;


import pl.codementors.finalstore.FinalStoreDAO;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AllUsersList implements Serializable {


    @EJB
    private FinalStoreDAO dao;

    @EJB
    private UserService userService;

    private List<User> users;

    /**
     * Method filling placeholder with all existing users.
     * @return List of users.
     */
    public List<User> getUsers(){
        if(users == null){
            users = dao.findAllUsers();
        }
        return users;
    }

    public User getCurrentUser() {
        return userService.getCurrentlyLoggedUser().get();
    }

    public void removeUser(User user){
        users.remove(user);
        dao.deleteUser(user);
    }

}
