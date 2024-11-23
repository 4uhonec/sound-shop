
package sound.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sound.database.ClientDB;
import sound.entities.Client;
import sound.utils.PasswordUtils;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        if(PasswordUtils.validatePassword(pass, email)){

            Client client = ClientDB.getClientByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("user", client);
            response.sendRedirect("index.jsp");
        }else{
            request.setAttribute("message", "invalid email or password");
            request.getRequestDispatcher("includes/login.jsp").forward(request, response);
        }
        
        
    }

    
    
}
