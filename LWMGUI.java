/* a class that creates a GUI to process returns and sales from a given customer account. it is the view and controller. 
 * This class takes user input from a GUI and uses it to create a Wine object. This Wine object is then passed as a parameter 
 * in methods that call CustomerAccount methods to process orders and update a running balance
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LWMGUI extends JFrame implements ActionListener {
	
	// instance variables for the components I will need
	private JPanel top, middle, bottom;
	private JTextField nameField, quantityField, priceField, amountField, balanceField;
	private JLabel lName, lQuantity, lPrice, wine, lAmount, lBalance;
	private JButton processSale, processReturn;
	private CustomerAccount customerAccountObject;

	// constructor of frame
	public LWMGUI(CustomerAccount x) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Lilybank Wine Merchants: "+x.getName());
		setSize(700, 200);
		setLocation(100, 100);
		customerAccountObject = x;

		// set layout
		layoutComponents();
		setVisible(true);
	}

	// method to arrange GUI
	private void layoutComponents() {

		// TOP 3 TEXTFIELDS
		top = new JPanel();
		top.setBackground(Color.white);

		lName = new JLabel("Name:");
		lQuantity = new JLabel("Quantity:");
		lPrice = new JLabel("Price: £");

		nameField = new JTextField("", 20);
		quantityField = new JTextField("", 10);
		priceField = new JTextField("", 10);

		// MIDDLE TWO BUTTONS
		middle = new JPanel();
		middle.setBackground(Color.red);

		processSale = new JButton("Process sale");
		processSale.addActionListener(this);
		processReturn = new JButton("Process return");
		processReturn.addActionListener(this);

		middle.add(processSale);
		middle.add(processReturn);

		top.add(lName);
		top.add(nameField);
		top.add(lQuantity);
		top.add(quantityField);
		top.add(lPrice);
		top.add(priceField);

		// BOTTOM LABELS
		bottom = new JPanel();
		bottom.setBackground(Color.white);

		lAmount = new JLabel("Amount of transaction:");
		lBalance = new JLabel("Current balance:");
		wine = new JLabel("Wine purchased/returned: ");

		amountField = new JTextField("", 10);
		amountField.setEditable(false);
		balanceField = new JTextField("", 10);
		balanceField.setEditable(false);

		bottom.add(wine);
		bottom.add(lAmount);
		bottom.add(amountField);
		bottom.add(lBalance);
		bottom.add(balanceField);

		// add panels to pane
		add(middle, BorderLayout.CENTER);
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
		
		this.updateBalanceField();
	}

	// method for getting user input info and processing sales and returns appropriately - triggered by buttons with eventListeners
	public void actionPerformed(ActionEvent e) {

		//don't let user submit empty name string
		if (nameField.getText().trim().isEmpty()) {JOptionPane.showMessageDialog(null, "Enter a wine");
			this.clearTextFields();} 
		else {
			//get textfield data into strings, trimming away empty spaces before and after
			String x = nameField.getText().trim();
			String y = quantityField.getText().trim();
			String z = priceField.getText().trim();

			try {
				//parse numbers out of strings
				int q = Integer.parseInt(y);
				double p = Double.parseDouble(z);
				
				//don't let user put 0 or negative numbers in for these
				if (q <= 0 || p <= 0) {throw new IllegalArgumentException();}
				
				else {
					//create wine object here
					Wine case1 = new Wine(x, q, p);
					
					//sale or return depending on which button pressed
					if (e.getSource() == this.processSale) 
						this.processSale(case1);
					
					else if (e.getSource() == this.processReturn) 
						this.processReturn(case1);	
				}
			
			} catch (NumberFormatException nfx) {
				JOptionPane.showMessageDialog(null, "Invalid format entered"); //if quantity is not an integer, if quantity or price has a letter or something in it
			} catch (IllegalArgumentException iax) {
				JOptionPane.showMessageDialog(null, "Amount/price must be above 0!"); 
			} finally {this.clearTextFields();} //happens regardless of success or error
		}
	}
	
	//method to update the balance field on GUI
	public void updateBalanceField() {
		if (customerAccountObject.getBalance() < 0) 
			this.balanceField.setText(String.format("£%.2f CR",Math.abs(customerAccountObject.getBalance()))); 
			else
				this.balanceField.setText(String.format("£%.2f",Math.abs(customerAccountObject.getBalance())));
	}
	
	
	// method to process sale
	public void processSale(Wine x) {
		this.amountField.setText(String.format("£%.2f",customerAccountObject.processSale(x))); 
		this.updateBalanceField();
		this.wine.setText("Wine purchased: "+x.getWine()); 
	}
	
	//method to process return
	public void processReturn(Wine x) {
		this.amountField.setText(String.format("£%.2f",customerAccountObject.processReturn(x))); 
		this.updateBalanceField();
		this.wine.setText("Wine returned: "+x.getWine()); 
	}
	
	// method to clear the three text fields 
	public void clearTextFields() {
		nameField.setText("");
		quantityField.setText("");
		priceField.setText("");
	}
	

}




