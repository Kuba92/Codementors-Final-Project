package pl.codementors.finalstore.controller;

import pl.codementors.finalstore.StoreDAO;
import pl.codementors.finalstore.model.User;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @EJB
    private StoreDAO storeDAO;

    @Inject
    private User principal;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        String nickname = request.getParameter("j_username");
        String password = request.getParameter("j_password");
        Optional<User> userByNickAndPass = storeDAO.findUserByNickAndPass(nickname, password);

        if (userByNickAndPass.isPresent()) {
            request.login(nickname, password);
            request.getSession().setAttribute("user", userByNickAndPass.get());
            response.sendRedirect(request.getContextPath() + "/main.xhtml");
        } else {
            response.sendRedirect(request.getContextPath() + "/error.xhtml");
        }
    }


}
