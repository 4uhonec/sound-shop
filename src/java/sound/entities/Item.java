
package sound.entities;

import java.io.Serializable;
import java.util.UUID;


public class Item implements Serializable{
    
    private UUID itemId;
    private String code;
    private String name;
    private String description;
    private String category;
    private int price;
    
    public Item(){};

    public UUID getItemId() {
        return itemId;
    }

    public Item(UUID itemId, String code, String name, String description, String category, int price) {
        this.itemId = itemId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
    }

    public void setItemId(UUID ItemId) {
        this.itemId = ItemId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImageURL() {
        String imageURL = "/sound-shop/images/" + code + "_preview.jpg";
        return imageURL;
    }
    
    @Override
    public String toString() {
        return "Item{" + "catalogId=" + code + ", name=" + name + ", description=" + description + ", category=" + category + ", price=" + price + '}';
    }
    
}
