/*class AssEx1 with a main to start the program and get customer name and initial bank balance from user 
 * input to create a CustomerAccount object, and then create a LWMGUI object by passing it the CustomerAccount 
 * object as a parameter
*/
import javax.swing.*;

public class AssEx1 {
	
public static void main(String[] args) {

		// create JOptionPane 
		int inputNameAndBalance = JOptionPane.showConfirmDialog(null, "Do you wish to begin a transaction?");
		if (inputNameAndBalance == JOptionPane.YES_OPTION) {

			String writeName = JOptionPane.showInputDialog(null, "Enter the customer's name");

			// report entered data back to user 
			if ((writeName != null) && (writeName.length() > 0)) {
				JOptionPane.showMessageDialog(null, "You entered \"" + writeName + "\"", "Success!",
						JOptionPane.INFORMATION_MESSAGE);
			}

			// close program if textfield is empty or is user closes window
			else
				System.exit(0);

			// get balance as double, keep asking if format is wrong
			boolean acceptable = false;
			while (!acceptable) {
				String inputBalance = JOptionPane.showInputDialog(null, "Enter the customer's balance");
				if (inputBalance != null) {

					try {
						double bal = Double.parseDouble(inputBalance.trim());
						
						//will only reach this if user puts something in the field, and if a double can be parsed from it
						acceptable = true; 
						
						//converts balance input into an integer, because that's how we want it to be stored in the CustomerAccount object (pence not pounds)
						int balanceInPence = (int) Math.round(bal*100);
						
						//make CustomerAccount 
						CustomerAccount cac = new CustomerAccount(balanceInPence, writeName); 
						
						//make a GUI object using this CustomerAccount 
						new LWMGUI(cac);
						
					} catch (NumberFormatException x) {JOptionPane.showMessageDialog(null, "Incorrect format");}
				
				} else
					System.exit(0);
			}
			
		} else
			System.exit(0);
	}
}



