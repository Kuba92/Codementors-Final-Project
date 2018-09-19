package pl.codementors.finalstore.rest_apis;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST api class with products.
 */
@Stateless
@Path("products")
public class ProductAPI {

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO storeDAO;

    @Path("")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts() {
        return storeDAO.findAllProducts();
    }

    @Path("{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductbyId(@PathParam("id") int id) {
        return storeDAO.findProduct(id);
    }

    @Path("")
    @POST
    @Consumes
    public void addProduct(Product product) {
        storeDAO.addProduct(product);
    }

    @Path("")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProduct(Product product) {
        storeDAO.updateProduct(product);
    }

    @Path("")
    @DELETE
    @Consumes
    public void removeProduct(Product product) {
        storeDAO.removeProduct(product);
    }
}