
package sound.utils;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.*;
import sound.database.ItemDB;
import sound.entities.Cart;
import sound.entities.Item;
import sound.entities.LineItem;

public class SoundStoreContextListener implements ServletContextListener{

    //Initializing some attributes with session start
    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        ServletContext sc = event.getServletContext();
        Cart cart = new Cart();
        cart.setItems(new ArrayList<LineItem>());
        List<Item> items = ItemDB.getItems();
        List<String> categories = ItemDB.getCategories();
        
        sc.setAttribute("cart", cart);
        sc.setAttribute("items", items);
        sc.setAttribute("categories", categories);

        sc.setAttribute("user", null);
        sc.setAttribute("message", "");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        
        ServletContext sc = event.getServletContext();
        sc.setAttribute("user", null);
        
    }
    
}
