package pl.codementors.finalstore.view;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class RoleView implements Serializable {

    public boolean isAdmin() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMIN");
    }

    public boolean isUser() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USER");
    }

}
