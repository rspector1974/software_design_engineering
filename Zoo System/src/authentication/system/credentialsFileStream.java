/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication.system;

/**
 *
 * @author robin.spector_snhu
 *///Public class for outputting from credentials file
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
public class credentialsFileStream {
    FileInputStream fileByteStream = null; // File input stream
    Scanner inFS = null;                   // Scanner object
    boolean credStatus = false; //credential status  variable
    String fileLine = ""; //data from text file variable
    static private String userType;//user roll variable 
    

 
// file input from credential file method
 public boolean fileInput(String userName, String MD5pw) throws FileNotFoundException, IOException {
    
    fileByteStream  = new FileInputStream("credentials.txt");
    inFS  = new Scanner(fileByteStream);
    //scan the file for the user name and MD5 hashed password until they are found or the end of the file has been reached
    while (!fileLine.contains(userName) && !fileLine.contains(MD5pw) && inFS.hasNextLine()) { 
        fileLine  = inFS.nextLine(); //add additional lines to fileLine
    }
    //if fileLine constraints the user name and password set credStatus to true
    if (fileLine.contains(userName) && fileLine.contains(MD5pw)){
        credStatus = true;
            //set the user type based on the contents of the credentials file
            if (fileLine.contains("admin")){
                userType = "admin";
            }
            else if (fileLine.contains("zookeeper")){
                userType = "zookeeper";
            }
            else if (fileLine.contains("veterinarian")){
                userType = "veterinarian";
            }
                    
        }
    else {
        credStatus = false;
    }
     
   
    fileByteStream.close (); // close() may throw IOException if fails

return(credStatus);//return whether the users credentials are correct to main
    
}   

    


    //Method used to return userType to main
    public static String getUserType() {
        

        return (userType);
    }
}
