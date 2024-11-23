
package sound.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import sound.database.AuthDB;
import sound.entities.Auth;

public class PasswordUtils {

    public static String getSalt() {

        try {
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            random.nextBytes(salt);

            return Base64.getEncoder().encodeToString(salt);
        }catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static String hashPassword(String password, String salt) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());

            return Base64.getEncoder().encodeToString(hashedPassword);
        }catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }

        return null;
    }


    public static boolean validatePassword(String pass, String email){
        
        boolean validated = false;
        
        if(!AuthDB.isEmailExists(email))
            return validated;
        else{
            Auth auth = AuthDB.getAuth(email);
            String hashedPassword = hashPassword(pass, auth.getSalt());
            validated = hashedPassword.equals(auth.getPassword());
        }
        
        return validated;
    }

}
