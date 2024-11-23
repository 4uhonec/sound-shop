
package sound.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import sound.database.ItemDB;

public class Cart implements Serializable{
    
    private List<LineItem> items;
    
    public Cart(){
        this.items = new ArrayList<>();
    }

    public List<LineItem> getItems() {
        return items;
    }

    public void setItems(List<LineItem> items) {
        this.items = items;
    }
    
    public boolean isContainingItem(String code){
        
        boolean containing = false;
        
        for(LineItem lineItem: items){
            if(lineItem.getItem().getCode().equals(code)){
                containing = true;
                return containing;
            }
        }
        
        return containing;
    }
    
    public void addItem(String code){
        
        if(!this.isContainingItem(code)){
            LineItem lineItem = new LineItem();
            Item item = ItemDB.getItemByCode(code);
            lineItem.setItem(item);
            lineItem.setQuantity(1);
            lineItem.setLineItemId(UUID.randomUUID());
            this.items.add(lineItem);
        }else{
            for(LineItem lineItem: items){
                if(lineItem.getItem().getCode().equals(code)){
                    lineItem.incrementQuantity();
                    return;
                }
            }
        }
    }
    
    public void removeItemByCode(String code){
        
        for(int i = 0; i < items.size(); i++){
            if(items.get(i).getItem().getCode().equals(code)){
                items.remove(i);
                return;
            }
        }
    }
    
}
