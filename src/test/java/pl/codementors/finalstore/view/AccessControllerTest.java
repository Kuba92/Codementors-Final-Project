package pl.codementors.finalstore.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.codementors.finalstore.model.User;
import pl.codementors.finalstore.service.UserService;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AccessControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AccessController accessController;

    @Test
    public void should_handle_missing_user() {
        Mockito.when(userService.getCurrentlyLoggedUser()).thenReturn(Optional.empty());

        User currentUser = accessController.getCurrentUser();

        assertEquals(User.createAnnonymousUser(), currentUser);
    }

    @Test
    public void should_return_current_user() {
        User user = new User();
        Mockito.when(userService.getCurrentlyLoggedUser()).thenReturn(Optional.of(user));

        User currentUser = accessController.getCurrentUser();

        assertEquals(user, currentUser);
    }
}