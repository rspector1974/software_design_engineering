/*
 CS-499 Software Enhancement 1 Software Design
 and Engineering improvement
 Robin Spector
 SNHU
 @author robin.spector_snhu
 .
 */
package authentication.system;

// Includes

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
// add JFrame and Action listener to public class
public class AuthenticationSystem extends JFrame implements ActionListener {
// GUI Declarations
private static final long serialVersionUID = 1L;
	JPanel panel;
	JLabel user_label, password_label, message;
	JTextField userName_text;
	JPasswordField password_text;
	JButton login;
	AuthenticationSystem() {
	// User name Label
	user_label = new JLabel();
	user_label.setText("User Name :");
	userName_text = new JTextField();
	// Password Label
	password_label = new JLabel();
	password_label.setText("Password :");
	password_text = new JPasswordField();
	// Login Button
	login = new JButton("Login");
	      panel = new JPanel(new GridLayout(3, 1));
	      panel.add(user_label);
	      panel.add(userName_text);
	      panel.add(password_label);
	      panel.add(password_text);
	      message = new JLabel();
	      panel.add(message);
	      panel.add(login);
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      // layout and listener info
	      login.addActionListener(this);
	      add(panel, BorderLayout.CENTER);
	      setTitle("Zoo Authentication System");
	      setSize(800,500);
	      setVisible(true);
	   }
   //define global variable for counter
	int counter = 4;
//Main method, JFrames makes the main method very short
@SuppressWarnings("null")
public static void main(String[] args) throws IOException  {
	new AuthenticationSystem();

}
public void actionPerformed(ActionEvent ae) {
    String userName = userName_text.getText(); // assign user name field value with action event
    @SuppressWarnings("deprecation")
	String password = password_text.getText(); // assign password value with action event. This is masked in the GUI
    boolean credstatus = false; 
    String original = password;  //assigns password to original password
    //MD5 Hash generation
    MessageDigest md = null ;
    	try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException ex) {
    Logger.getLogger(AuthenticationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    md.update(original.getBytes());
    byte[] digest = md.digest();
    StringBuffer sb = new StringBuffer();
    for (byte b : digest) {
    sb.append(String.format("%02x", b & 0xff));
         }
    String userMD5pass = sb.toString();//assigns MD5 Hash to userMD5pass
    credentialsFileStream inputFiles = new credentialsFileStream();
     try {
		credstatus = inputFiles.fileInput(userName,userMD5pass);
	} catch (IOException e) {
		e.printStackTrace();
	}
        //checks credentials file for both the user name and the MD5 hash 
        //Control structure if valid credentials are entered
     if (credstatus == true) {        
    	 //stop while loop when credstatus is true
         message.setText("Credentials are correct! Opening secured file...");
         userTypeSelector userType = new userTypeSelector();
         String userRoll = credentialsFileStream.getUserType();
//output from userTypeSelector class
               
    try {
    	JOptionPane.showMessageDialog(null,userType.outputUserFile(userRoll));
    } catch (IOException e) {
    	e.printStackTrace();
    }
        }
    else {//control structure for counting invalid login attempts
    	message.setText(counter + " tries remaining");
    	String popUpMessage = "Invalid Credentials " + counter + " more trys before forced exit";
    	JOptionPane.showMessageDialog(null,popUpMessage);
    	counter = counter -1;//decrease counter by 1 on each invalid attempt
    if (counter < 0) {
    	JOptionPane.showMessageDialog(null,"Exiting now!");
    	setVisible(false);
    
    }
}
}
}
    



        



    
            
    
    
    
   

