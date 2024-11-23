
package sound.controllers;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sound.database.ItemDB;
import sound.entities.Item;


public class EditItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if(action.equals("delete")){
            
            ItemDB.deleteItemByCode(request.getParameter("item"));
            response.sendRedirect("index.jsp");
            
        }else if(action.equals("edit")){
            
            String code = request.getParameter("item");
            Item item = ItemDB.getItemByCode(code);
            request.setAttribute("item", item);
            request.getRequestDispatcher("/includes/edit-item.jsp").forward(request, response);

        }else if(action.equals("submit-update")){
                
            UUID id = UUID.fromString(request.getParameter("id"));
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            int price = Integer.parseInt(request.getParameter("price"));
            
            Item item = new Item();
            item.setItemId(id);
            item.setCode(code);
            item.setName(name);
            item.setDescription(description);
            item.setCategory(category);
            item.setPrice(price);
            ItemDB.updateItemByCode(item, code);
            request.setAttribute("item", item);
            
            request.getRequestDispatcher("/includes/item-details.jsp").forward(request, response);
            
        }else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        
    }
    
}
