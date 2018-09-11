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
    @EJB //wstrzykniety storeDAO zamiast em
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
            admin.setAccepted(true);
            admin.setRole(User.Role.ADMIN);
            storeDAO.addUser(admin);
        }
    }


    /**
     * Method checking size of users list.
     * @return Size of users list.
     */
    private int sizeOfDatabase() {
//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<User> query = cb.createQuery(User.class);
//        query.from(User.class);
//        List<User> users = em.createQuery(query).getResultList();
//        return users.size();
        return storeDAO.findAllUsers().size(); //zamiast strzykiwac em, wstrzyknalem storeDAO, i nie musimy tutaj duplikowac kodu
    }
}
