
package sound.controllers;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sound.database.ItemDB;
import sound.entities.Item;

public class AddItemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String code = request.getParameter("code");
        
        if(ItemDB.getItemByCode(code) != null){
            request.setAttribute("message", "Item with this code already exists in database");
            request.getRequestDispatcher("/includes/add-new-item.jsp").forward(request, response);
        }else{
                    
            UUID id = UUID.randomUUID();
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            int price = Integer.parseInt(request.getParameter("price"));
            
            Item item = new Item();
            item.setItemId(id);
            item.setName(name);
            item.setDescription(description);
            item.setCategory(category);
            item.setPrice(price);
            ItemDB.addItem(item);
            
            request.getRequestDispatcher("/includes/item-added.jsp").forward(request, response);
        
        }

        
    }



}
