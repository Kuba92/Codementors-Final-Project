package pl.codementors.finalstore;

import pl.codementors.finalstore.model.Order;
import pl.codementors.finalstore.model.Product;
import pl.codementors.finalstore.model.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Stateless
@Named
public class StoreDAO {


    /**
     * Declaring an Entity manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Getting an order from the database by its id
     * @param id
     * @return Order
     */
    public Order findOrder (int id) {

        return em.find(Order.class, id);
    }

    /**
     * Get all orders in the database
     * @return List<Order>
     */
    public List<Order> findAllOrders () {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> query = cb.createQuery(Order.class);
        query.from(Order.class);
        List<Order> orderList = em.createQuery(query).getResultList();
        return orderList;
    }
    /**
     * Add new order to the database
     * @param order
     */
    public void addOrder (Order order) {
        em.persist(order);
    }

    /**
     * Update orders in the databse
     * @param order
     */
    public void updateOrder (Order order) {
        em.merge(order);
    }

    /**
     * Remove order from the database
     * @param order
     */
    public void removeOrder (Order order) {
        em.remove(em.merge(order));
    }

    /**
     * Method for getting an product from the database by its id
     * @param id
     * @return Product
     */

    public Product findProduct ( int id) {
        return em.find(Product.class, id);

    }

    /**
     * Method returning all products in the database
     * @return List<Product>
     */

    public List<Product> findAllProducts () {
        CriteriaBuilder cb =  em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        query.from(Product.class);
        List<Product> productsList = em.createQuery(query).getResultList();
        return productsList;
    }

    /**
     * Method to add new products to the database
     * @param product
     */

    public void addProduct (Product product) {
        em.persist(product);
    }

    /**
     * Method to update products in the databse
     * @param product
     */

    public void updateProduct (Product product) {
        em.merge(product);
    }
    /**
     * Method to remove products from the database
     * @param product
     */

    public void removeProduct (Product product) {
        em.remove(em.merge(product));
    }

    /**
     * Method for getting an user from the database by its id
     * @param id
     * @return User
     */
    public User findUser (int id) {
        Optional<User> user = Optional.ofNullable(em.find(User.class, id));
        if(user.isPresent()) {
            em.detach(user.get());
            return user.get();
        } else {
            return new User(false, User.Role.USER);
        }
    }

    /**
     * Method for getting a user from the database by nickname and password
     * @param nickname
     * @param password
     * @return User
     */
    public Optional<User> findUserByNickAndPass(String nickname, String password) {
        TypedQuery<User> query = em.createQuery("select u from User u where u.nickname = :nickname and u.password = :password", User.class);
        query.setParameter("nickname", nickname);
        query.setParameter("password", password);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    /**
     * Method for getting a user from the database by nickname only
     * @param nickname
     * @return User
     */
    public Optional<User> findUserByNickname(final String nickname) {
        try {
            return Optional.of((User) em.createQuery("SELECT u From User u where u.nickname=:nickname")
                    .setParameter("nickname", nickname).getSingleResult());
        } catch (final NoResultException e) {
            return Optional.empty();
        }
    }

    /**
     * Method returning all users in the database
     * @return List<User>
     */
    public List<User> findAllUsers () {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        query.from(User.class);
        List<User> userList = em.createQuery(query).getResultList();
        return userList;
    }

    /**
     * Method to update users in the database
     * @param user
     */
    public void updateUser (User user) {
        em.merge(user);
    }
    /**
     * Method to add new users to the database
     * @param user
     */
    public void addUser (User user) {
        em.persist(user);
    }

    /**
     * Method to remove orders from the database
     * @param user
     */
    public void removeUser (User user) {
        em.remove(em.merge(user));
    }
}
