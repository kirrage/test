//a class used in processing orders of quantities of wines of a certain price
	public class Wine {
		
		//instance variables
		private String wine = "";
		private double price;
		private int quantity;
		
		//constructor
		public Wine(String w, int q, double p) {
			wine = w;
			quantity = q;
			price = p;
		}
		//accessors
		public String getWine() {
			return wine;
		}
		
		public int getQuantity() {
			return quantity;
		}
		
		public double getPrice() {
			return price;
		}
		
		
		
		
	}