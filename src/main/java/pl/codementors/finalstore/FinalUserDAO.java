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

/**
 * DAO and REST apis for users in our store
 */
@Stateless
@Path("users")
public class FinalUserDAO {

    /**
     * Declaring an Entity manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Rest api for getting an user from the database by its id
     * @param id
     * @return User
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User findUser (@PathParam("id") int id) {
        Optional<User> user = Optional.ofNullable(em.find(User.class, id));
        if(user.isPresent()) {
            em.detach(user.get());
            return user.get();
        } else {
            return new User(false, User.Role.USER);
        }
    }


    public Optional<User> findUserByNickAndPass(String nickname, String password) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.nickname = :nickname and u.password = :password", User.class);
        query.setParameter("nickname", nickname);
        query.setParameter("password", password);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    /**
     * REST api returning all users in the database
     * @return List<User>
     */
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

    /**
     * REST api to update users in the database
     * @param user
     */
    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser (User user) {
        em.merge(user);
    }
    /**
     * REST api to add new users to the database
     * @param user
     */
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addUser (User user) {
        em.persist(user);
    }

    /**
     * REST api to remove orders from the database
     * @param user
     */
    @Path("")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeUser (User user) {
        em.remove(em.merge(user));
    }
}
