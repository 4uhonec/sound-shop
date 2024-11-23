package sound.database;

import java.util.*;
import sound.entities.Item;
import java.sql.*;
import java.util.UUID;

public class ItemDB {
    
    public static Item getItemByCode(String code){
        
        Item item = null;
        String query = "select * from item where code = ?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, code);
            
            try(ResultSet rs = statement.executeQuery()){
                rs.next();
                int index = 1;
                UUID id = rs.getObject(index++, java.util.UUID.class);
                String itemCode = rs.getString(index++);
                String name = rs.getString(index++);
                String description = rs.getString(index++);
                String cat = rs.getString(index++);
                int price = rs.getInt(index);
                item = new Item(id, itemCode, name, description, cat, price);
                
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return item;
    }
    
    public static List<Item> getItemsByCategory(String category){
        
        if(category.equals("all"))
            return getItems();
        
        List<Item> list = new ArrayList<>();
        String query = "select * from item where category = ?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, category);
            
            try(ResultSet rs = statement.executeQuery()){
                

                while(rs.next()){

                    int index = 1;
                    UUID id = rs.getObject(index++, java.util.UUID.class);
                    String code = rs.getString(index++);
                    String name = rs.getString(index++);
                    String description = rs.getString(index++);
                    String cat = rs.getString(index++);
                    int price = rs.getInt(index);
                    Item item = new Item(id, code, name, description, cat, price);
                    list.add(item);
                }
                
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    public static List<Item> getItems(){
        
        String query = "select * from item";
        ConnectionPool pool = ConnectionPool.getInstance();

        try(Connection connection = pool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)){
            
            List<Item> items = new ArrayList<>();
            
            while(rs.next()){
                UUID uuid = rs.getObject("item_id", java.util.UUID.class);
                String code = rs.getString("code");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String category = rs.getString("category");
                int price = rs.getInt("price");
                items.add(new Item(uuid, code, name, description, category, price));
            }
            
            return items;
            
        }catch(Exception e){
            
            System.out.println(e.getMessage());
            return null;
            
        }
    }

    public static List<String> getCategories(){
        
        String query = "select distinct category from item order by category;";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query)){
            
            List<String> categories = new ArrayList<>();
            
            while(rs.next()){
                categories.add(rs.getString("category"));
            }
            
            return categories;
            
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static void addItem(Item item){
        
        String query = "insert into item (item_id, code, name, description, category, price) values (?, ?, ?, ?, ?, ?);";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            int index = 1;
            
            statement.setObject(index++, item.getItemId(), Types.OTHER);
            statement.setString(index++, item.getCode());
            statement.setString(index++, item.getName());
            statement.setString(index++, item.getDescription());
            statement.setString(index++, item.getCategory());
            statement.setInt(index, item.getPrice());
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    // TODO: its not exactly updating, but since we are working with code instead of uuid...
    public static void updateItemByCode(Item item, String code){
        
        deleteItemByCode(code);
        addItem(item);
        
    }
    
    public static void deleteItemByCode(String code){
        String query = "delete from item where code=?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, code);
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
