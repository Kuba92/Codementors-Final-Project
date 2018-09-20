package pl.codementors.finalstore.view;


import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import java.util.Optional;

/**
 * View used to login form.
 */
@Named
@ViewScoped
public class LoginView implements Serializable {

    private String username;

    private String password;

    /**
     * Principal object.
     */
    @Inject
    private Principal principal;

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO dao;

    public void doPost() throws IOException, ServletException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        Optional<User> userByNickAndPass = dao.findUserByNickAndPass(username, password);

        if (userByNickAndPass.isPresent()) {
            request.login(username, password);
            request.getSession().setAttribute("user", userByNickAndPass.get());
            context.getExternalContext().redirect(request.getContextPath() + "/products.xhtml");
        } else {
            context.getExternalContext().redirect(request.getContextPath() + "/error.xhtml");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
            context.getExternalContext().redirect(request.getContextPath() + "/products.xhtml");

        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loggedInCheck () {
        return principal==null;
    }

}
