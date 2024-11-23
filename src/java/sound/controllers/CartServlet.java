
package sound.controllers;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sound.entities.Cart;


public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String code = request.getParameter("item");
        ServletContext sc = request.getServletContext();
        Cart cart = (Cart) sc.getAttribute("cart");
        String action = request.getParameter("action");
        
        if(action.equals("add")){
            cart.addItem(code);
        }else if(action.equals("delete")){//remove from cart
            cart.removeItemByCode(code);
        }
        
        sc.setAttribute("cart", cart);
        
        request.getRequestDispatcher("/includes/cart.jsp").forward(request, response);

        
    }
}
