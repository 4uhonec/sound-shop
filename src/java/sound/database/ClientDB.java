
package sound.database;

import sound.entities.Client;
import java.sql.*;
import java.util.UUID;

public class ClientDB {
    
    public static void addClient(Client client){
        
        String query = "insert into client(client_id, role, first_name, last_name, address, email) "
                + "values(?, ?, ?, ?, ?, ?)";
        ConnectionPool pool = ConnectionPool.getInstance();
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            int index = 1;
            statement.setObject(index++, client.getUuid(), Types.OTHER);
            statement.setString(index++, client.getRole());
            statement.setString(index++, client.getFirstName());
            statement.setString(index++, client.getLastName());
            statement.setString(index++, client.getAddress());
            statement.setString(index++, client.getEmail());
            statement.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    public static Client getClientByEmail(String email){
        
        String query = "select * from client where email = ?;";
        ConnectionPool pool = ConnectionPool.getInstance();
        Client client = null;
        
        try(Connection connection = pool.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)){
            
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                client = new Client();
                int index = 1;
                client.setUuid(rs.getObject(index++, java.util.UUID.class));
                client.setRole(rs.getString(index++));
                client.setFirstName(rs.getString(index++));
                client.setLastName(rs.getString(index++));
                client.setAddress(rs.getString(index));
                client.setEmail(email);
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return client;
        
    }
    
}
