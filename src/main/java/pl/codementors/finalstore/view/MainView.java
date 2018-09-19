package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;
import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Named
@SessionScoped
public class MainView {

    private String username;

    private String password;

    @Inject
    private Principal principal;

    @EJB
    private StoreDAO dao;

    @Inject
    private UserService userService;




    public void logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        if (principal.getName()=="anonymous") {
            try {
                context.getExternalContext().redirect(request.getContextPath() + "/login.xhtml");

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } else {
            try {
                request.logout();
                context.getExternalContext().redirect(request.getContextPath() + "/main.xhtml");

            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean getLoggedInCheck () {
        if (principal.getName()=="anonymous") {
            return true;
        } else {
            return false;
        }

    }


    public String getLoginButtonLabel() {
        return (principal.getName() == "anonymous")?"Login" : "Logout";
    }



    public User getCurrentUser() {
        return userService.getCurrentlyLoggedUser().get();
    }

}

