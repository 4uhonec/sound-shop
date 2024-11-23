
package sound.database;

import java.sql.*;
import sound.entities.Auth;

public class AuthDB {
    
    //before inserting record in calling method, have to check with recordExists()
    public static void insertRecord(Auth auth){
        
        String query = "insert into password (email, password, salt) values (?, ?, ?);";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, auth.getEmail());
            statement.setString(2, auth.getPassword());
            statement.setString(3, auth.getSalt());
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static Auth getAuth(String email){
        
        String query = "select * from password where email = ?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        Auth auth = null;
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                auth = new Auth();
                auth.setEmail(email);
                auth.setPassword(rs.getString(2));
                auth.setSalt(rs.getString(3));
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return auth;
    }
    
    public static boolean isEmailExists(String email){
        
        String query = "select * from password where email = ?;";
        boolean exists = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            exists = rs.next();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return exists;
        
    }
    
}
