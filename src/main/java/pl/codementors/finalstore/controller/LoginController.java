package pl.codementors.finalstore.controller;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet used to redirect user after success or failed login.
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {

    /**
     * Bean EJB used to communicate with db.
     */
    @EJB
    private StoreDAO storeDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nickname = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        Optional<User> userByNickAndPass = storeDAO.findUserByNickAndPass(nickname, password);

        if (userByNickAndPass.isPresent()) {
            request.login(nickname, password);
            request.getSession().setAttribute("user", userByNickAndPass.get());
            response.sendRedirect(request.getContextPath() + "/template.xhtml");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.xhtml");
        }
    }


}
