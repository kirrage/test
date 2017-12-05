//this class acts as a model in the model-view-controller structure of GUIs, and contains methods to alter the balance of a customer after returns or sales
public class CustomerAccount {
	
	private final double serviceCharge = 0.20;
	
	private int balance;
	private String name;
	
	
	//constructor
	public CustomerAccount(int b, String n) {
		balance = b;
		name = n;
	}
	
	//method to process sale of quantity of bottles
	public double processSale(Wine x) {
		// use pence for calculations
		int costInPence = (int) Math.round(x.getPrice()*100);
		int costTotal = x.getQuantity()*costInPence;
		//update balance
		balance = balance+costTotal;
		//return a double for the GUI
		double costDouble = (double) costTotal/100;
		return costDouble;	
	}
	
	//method to process return 
	public double processReturn(Wine x) {
		int costInPence = (int) Math.round(x.getPrice()*100);
		int costTotal = x.getQuantity()*costInPence;
		//factor in the return service charge - done as a double because serviceCharge is double
		double costTotalWithService = ((double) costTotal/100)-(((double) costTotal/100)*serviceCharge);
		//updates the balance as an integer
		costTotal = (int) Math.round(costTotalWithService*100);
		balance = balance-costTotal;
		
		//return the double to update GUI
		return costTotalWithService;	
	}
	
	//accessor for balance
	public double getBalance() {
		//convert balance (stored as pence) to pounds format
		double balancePounds = (double) balance/100;
		return balancePounds;
	}
	//accessor for name - not used in the program
	public String getName() {
		return name;
	}

}


