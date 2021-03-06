package pl.codementors.finalstore.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class LoginViewTest {

    @Mock
    private FacesContext facesContext;

    @Mock
    private HttpServletRequest request;

    @Mock
    private ExternalContext externalContext;

    @InjectMocks
    private LoginView loginView;

    @Mock
    private StoreDAO dao;

    @Mock
    private HttpSession httpSession;

    @Mock
    private User user;

    @Before
    public void setup() {
        PowerMockito.mockStatic(FacesContext.class);
        PowerMockito.when(FacesContext.getCurrentInstance()).thenReturn(facesContext);

        Mockito.when(facesContext.getExternalContext()).thenReturn(externalContext);
        Mockito.when(externalContext.getRequest()).thenReturn(request);
    }

    @Test
    public void should_return_to_error_page() throws IOException, ServletException {
        Mockito.when(dao.findUserByNickAndPass(null, null)).thenReturn(Optional.empty());

        loginView.doPost();

        Mockito.verify(externalContext).redirect(Matchers.anyString());
    }

    @Test
    public void should_log_user() throws IOException, ServletException {
        Mockito.when(request.getSession()).thenReturn(httpSession);
        Mockito.when(dao.findUserByNickAndPass(user.getNickname(), user.getPassword())).thenReturn(Optional.of(user));

        loginView.doPost();

        Mockito.verify(externalContext).redirect(Matchers.anyString());
    }
}