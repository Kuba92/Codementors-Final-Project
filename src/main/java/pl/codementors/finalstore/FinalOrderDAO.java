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

@Stateless
@Path("orders")
public class FinalOrderDAO {

    @PersistenceContext
    private EntityManager em;

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order findOrder (@PathParam("id") int id) {
       return em.find(Order.class, id);
    }

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

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addOrder (Order order) {
        em.persist(order);
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrder (Order order) {
        em.merge(order);
    }

    @Path("")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeOrder (Order order) {
        em.remove(em.merge(order));
    }

}
