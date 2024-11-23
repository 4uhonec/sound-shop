
package sound.controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sound.database.ItemDB;
import sound.entities.Item;


//@WebServlet(name = "CatalogServlet")
public class CatalogServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String category = request.getParameter("category");
        List<Item> items = ItemDB.getItemsByCategory(category);
        
        request.setAttribute("items", items);

        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        
    }
}
