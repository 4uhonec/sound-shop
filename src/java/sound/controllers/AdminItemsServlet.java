
package sound.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sound.database.ItemDB;
import sound.entities.Item;


public class AdminItemsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        
        if(action.equals("delete")){
            
            ItemDB.deleteItemByCode(request.getParameter("item"));
            List<Item> items = ItemDB.getItems();
            request.setAttribute("items-list", items);
            request.getRequestDispatcher("/includes/admin-items-list.jsp").forward(request, response);
            
        }else if(action.equals("edit")){
            
            String code = request.getParameter("item");
            Item item = ItemDB.getItemByCode(code);
            request.setAttribute("item", item);
            request.getRequestDispatcher("/includes/edit-item.jsp").forward(request, response);
            
        }else if(action.equals("add-item")){
            
            
            
        }else{
        
        List<Item> items = ItemDB.getItems();
        request.setAttribute("items-list", items);
        request.getRequestDispatcher("/includes/admin-items-list.jsp").forward(request, response);
        
        }
        
    }
    
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
            item.setCode(code);
            item.setName(name);
            item.setDescription(description);
            item.setCategory(category);
            item.setPrice(price);
            ItemDB.addItem(item);
            
            request.getRequestDispatcher("/includes/item-added.jsp").forward(request, response);
        
        }
    }
}
