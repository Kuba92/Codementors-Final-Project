package pl.codementors.finalstore.rest_apis;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Order;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST api class with users.
 */
@Stateless
@Path("users")
public class UsersAPI {

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO storeDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers() {
        return storeDAO.findAllUsers();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserbyId(@PathParam("id") int id) {
        return storeDAO.findUser(id);
    }

    @Path("")
    @POST
    @Consumes
    public void addUser(User user) {
        storeDAO.addUser(user);
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser(User user) {
        storeDAO.updateUser(user);
    }

    @Path("")
    @DELETE
    @Consumes
    public void removeUser(User user) {
        storeDAO.removeUser(user);
    }
}