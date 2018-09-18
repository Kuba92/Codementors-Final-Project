package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class ProductEdit implements Serializable {

    @EJB
    private StoreDAO dao;

    private Product product;

    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Product getProduct() {
        if (product == null) {
            product = dao.findProduct(productId);
        }
        return product;
    }

    public void updateProduct() {
        dao.updateProduct(product);
    }

    public void deleteProduct(Product product) {
        dao.removeProduct(product);
    }
}
