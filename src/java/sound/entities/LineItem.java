
package sound.entities;

import java.io.Serializable;
import java.util.UUID;

public class LineItem implements Serializable{
    
    private UUID lineItemId;
    private Item item;
    private int quantity = 1;
    
    public LineItem(){}

    public UUID getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(UUID lineItemId) {
        this.lineItemId = lineItemId;
    }
    
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getTotal(){
        return item.getPrice() * quantity;
    }
    
    public void incrementQuantity(){
        this.setQuantity(this.getQuantity()+1);
    }
    
}
