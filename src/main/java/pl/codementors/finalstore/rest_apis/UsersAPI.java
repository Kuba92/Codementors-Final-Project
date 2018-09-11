package pl.codementors.finalstore.rest_apis;
import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("users")
public class UsersAPI {

    @EJB
    private StoreDAO storeDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers () {
        return  storeDAO.findAllUsers();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserbyId (@PathParam("id") int id) {
        return storeDAO.findUser(id);
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateUser (User user) {
        storeDAO.updateUser(user);
    }

    @Path("")
    @DELETE
    @Consumes
    public void removeUser (User user) {
        storeDAO.removeUser(user);
    }
}