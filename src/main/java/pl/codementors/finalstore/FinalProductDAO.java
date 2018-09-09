package pl.codementors.finalstore;


import pl.codementors.finalstore.model.Product;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Stateless
@Path("products")
public class FinalProductDAO {

    @PersistenceContext
    private EntityManager em;

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product findProduct (@PathParam("id") int id) {
        return em.find(Product.class, id);

    }

    @Path("products")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> findAllProducts () {
        CriteriaBuilder cb =  em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        query.from(Product.class);
        List<Product> productsList = em.createQuery(query).getResultList();
        return productsList;
    }

    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct (Product product) {
        em. persist(product);
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProduct (Product product) {
        em.merge(product);
    }

    @Path("")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeProduct (Product product) {
        em.remove(em.merge(product));
    }


}
