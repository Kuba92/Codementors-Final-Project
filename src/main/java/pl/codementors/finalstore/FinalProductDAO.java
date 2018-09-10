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

/**
 * DAO and REST apis for Products in our store
 */
@Stateless
@Path("products")
public class FinalProductDAO {


    /**
     * Declaring an Entity manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Rest api for getting an product from the database by its id
     * @param id
     * @return Product
     */
    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product findProduct (@PathParam("id") int id) {
        return em.find(Product.class, id);

    }

    /**
     * REST api returning all products in the database
     * @return List<Product>
     */
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

    /**
     * REST api to add new products to the database
     * @param product
     */
    @Path("")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void addProduct (Product product) {
        em. persist(product);
    }

    /**
     * REST api to update products in the databse
     * @param product
     */
    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProduct (Product product) {
        em.merge(product);
    }
    /**
     * REST api to remove products from the database
     * @param product
     */
    @Path("")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void removeProduct (Product product) {
        em.remove(em.merge(product));
    }


}
