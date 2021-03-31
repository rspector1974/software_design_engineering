/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication.system;

/**
 *
 * @author robin.spector_snhu
 *///public class for outputting user role file
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
//Used to output user type file to variable fileOutput
public class userTypeSelector {
    //user type FINAL vars
    final String adminUser ="admin";
    final String vetUser="veterinarian";
    final String zookeeperUser="zookeeper";
    String userType;//user type variable
    String fileOutput = "";//variable to store user file text
    //open user type file 
    String outputUserFile (String userType) throws FileNotFoundException, IOException{
        try (FileInputStream fileByteStream = new FileInputStream(userType + ".txt")) {
            @SuppressWarnings("resource")
			Scanner inFS = new Scanner(fileByteStream);
            while (inFS.hasNextLine()) {
                //continue scanning filing until end
                //assign file text to fileOutput maintaining line structure
                fileOutput = fileOutput + inFS.nextLine() + "\n";
            }
            // close() may throw IOException if fails
        }
         
    return(fileOutput);//return fileOutput to Main
        
    }
        
    }


