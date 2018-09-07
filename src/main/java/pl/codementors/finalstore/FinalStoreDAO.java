package pl.codementors.finalstore;


import pl.codementors.finalstore.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
public class FinalStoreDAO {

    @PersistenceContext
    private EntityManager em;

    public Optional<User> findUser (String nickname, String password) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.nickame = :nickname and u.password = :password", User.class);
        query.setParameter("nickname", nickname);
        query.setParameter("password", password);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public List<User> findAllUsers(){
        return em.createQuery("select u from User u").getResultList();
    }
}
