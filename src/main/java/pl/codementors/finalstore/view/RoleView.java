package pl.codementors.finalstore.view;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named
public class RoleView implements Serializable {

    @Inject
    private StoreDAO storeDAO;

    public boolean isAdmin() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ADMIN");
    }

    public boolean isUser() {
        return FacesContext.getCurrentInstance().getExternalContext().isUserInRole("USER");
    }

    public boolean isAccepted() {
        User current = storeDAO.findUserByNickname(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()).orElse(null);
        return current.isAccepted();

    }

}
