package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.Product;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ProductsList implements Serializable {


    @EJB
    private StoreDAO dao;

    @Inject
    private BasketView basketView;

    private List<Product> productList;

    public List<Product> getProductList(){
        if(productList == null){
            productList = dao.findAllProducts();
        }
        return productList;
    }

    public void addProductToBasket(Product product) {
        if (!basketView.getProductsInBasket().contains(product)) {
            basketView.addProductToBasket(product);
        }
    }
    public boolean addedToBasketOrNotAvailable (Product product) {
        return (basketView.getProductsInBasket().contains(product) || !product.isAvailable());
    }


}
