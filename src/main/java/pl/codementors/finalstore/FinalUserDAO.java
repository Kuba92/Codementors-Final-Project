package pl.codementors.finalstore;


import pl.codementors.finalstore.model.User;

import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Stateless
@Path("users")
public class FinalUserDAO {

    @PersistenceContext
    private EntityManager em;


    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser (@PathParam("id") int id) {
        return em.find(User.class, id);
    }


    public Optional<User> findUserByNickAndPass(String nickname, String password) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.nickame = :nickname and u.password = :password", User.class);
        query.setParameter("nickname", nickname);
        query.setParameter("password", password);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    @Path("users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findAllUsers () {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        query.from(User.class);
        List<User> userList = em.createQuery(query).getResultList();
        return userList;
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser (User user) {
        em.merge(user);
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser (User user) {
        em.persist(user);
    }

    @Path("")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeUser (User user) {
        em.remove(em.merge(user));
    }
}
