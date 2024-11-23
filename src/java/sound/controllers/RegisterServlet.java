
package sound.controllers;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sound.database.AuthDB;
import sound.database.ClientDB;
import sound.entities.Auth;
import sound.entities.Client;
import sound.utils.PasswordUtils;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        if(AuthDB.isEmailExists(email)){
            
            request.setAttribute("message", "user with this email already exists");
            request.getRequestDispatcher("/includes/register.jsp").forward(request, response);
            
        }else{
            
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            String password = request.getParameter("password");
            String role = "client";
            Client client = new Client();
            client.setUuid(UUID.randomUUID());
            client.setRole(role);
            client.setFirstName(firstName);
            client.setLastName(lastName);
            client.setAddress(address);
            client.setEmail(email);
            
            Auth auth = new Auth();
            String salt = PasswordUtils.getSalt();
            String hashedPassword = PasswordUtils.hashPassword(password, salt);
            auth.setEmail(email);
            auth.setPassword(hashedPassword);
            auth.setSalt(salt);
            
            ClientDB.addClient(client);
            AuthDB.insertRecord(auth);
            
            HttpSession session = request.getSession();
            session.setAttribute("user", client);
            response.sendRedirect("index.jsp");
            
        }
    }
}
