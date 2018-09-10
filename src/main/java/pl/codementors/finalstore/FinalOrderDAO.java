package pl.codementors.finalstore;


import pl.codementors.finalstore.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


/**
 * DAO and REST apis for orders in our store
 */
@Stateless
@Path("orders")
public class FinalOrderDAO {

    /**
     * Declaring an Entity manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Rest api for getting an order from the database by its id
     * @param id
     * @return Order
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order findOrder (@PathParam("id") int id) {
       return em.find(Order.class, id);
    }

    /**
     * REST api returning all orders in the database
     * @return List<Order>
     */
    @Path("")
    @GET
    @Produces
    public List<Order> findAllOrders () {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        query.from(Order.class);
        List<Order> orderList = em.createQuery(query).getResultList();
        return orderList;
    }

    /**
     * REST api to add new orders to the database
     * @param order
     */
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addOrder (Order order) {
        em.persist(order);
    }

    /**
     * REST api to update orders in the databse
     * @param order
     */
    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrder (Order order) {
        em.merge(order);
    }

    /**
     * REST api to remove orders from the database
     * @param order
     */
    @Path("")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeOrder (Order order) {
        em.remove(em.merge(order));
    }

}
