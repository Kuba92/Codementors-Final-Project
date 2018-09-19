package pl.codementors.finalstore;

import pl.codementors.finalstore.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 * Application store context.
 */
@Singleton
@Startup
public class FinalStoreContext {

    /**
     * UserDAO
     */
    @EJB
    private StoreDAO storeDAO;

    /**
     * Method adding default admin after deployment if users list is empty.
     */
    @PostConstruct
    public void init() {
        if (sizeOfDatabase() == 0) {
            User admin = new User();
            admin.setName("admin");
            admin.setSurname("admin");
            admin.setNickname("admin");
            admin.setPassword("admin");
            admin.setEmail("");
            admin.setEmail("admin@admin.com");
            admin.setAccepted(true);
            admin.setRole(User.Role.ADMIN);
            storeDAO.addUser(admin);
        }
    }


    /**
     * Method checking size of users list.
     *
     * @return Size of users list.
     */
    private int sizeOfDatabase() {
        return storeDAO.findAllUsers().size();
    }
}
