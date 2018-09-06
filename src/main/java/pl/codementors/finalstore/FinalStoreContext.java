package pl.codementors.finalstore;

import pl.codementors.finalstore.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Application store context.
 */
@Singleton
@Startup
public class FinalStoreContext {

    /**
     * Entity manager.
     */
    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        if (sizeOfDatabase() == 0) {
            User admin = new User();
        }
    }




    private int sizeOfDatabase() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        query.from(User.class);
        List<User> users = em.createQuery(query).getResultList();
        return users.size();
    }
}
