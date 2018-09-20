package pl.codementors.finalstore.rest_apis;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Order;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST api class with orders.
 */
@Stateless
@Path("orders")
public class OrdersAPI {

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO storeDAO;


    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() {
        return storeDAO.findAllOrders();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrderbyId(@PathParam("id") int id) {
        return storeDAO.findOrder(id);
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrder(Order order) {
        storeDAO.updateOrder(order);
    }

    @Path("")
    @POST
    @Consumes
    public void addOrder(Order order) {
        storeDAO.addOrder(order);
    }

    @Path("")
    @DELETE
    @Consumes
    public void removeOrder(Order order) {
        storeDAO.removeOrder(order);
    }
}
