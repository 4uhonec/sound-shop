
package sound.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sound.database.ItemDB;
import sound.entities.Item;


public class DetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String code = request.getParameter("item");
        
        Item item = ItemDB.getItemByCode(code);
        request.setAttribute("item", item);
        getServletContext().getRequestDispatcher("/includes/item-details.jsp").forward(request, response);
        
        
    }
}
