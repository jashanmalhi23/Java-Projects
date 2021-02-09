import java.util.Scanner;
public class Shop {

	static double pdiscount = 0;
	static double discount = 0;
	
	public static String numSuffix(int i) {
		int rem = i % 10;
		switch (rem) {
		case 0:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
		return (i + "th");
		case 1:
		if (i % 100 != 11)
		return (i + "st");
		else
		return (i + "th");
		case 2:
		if (i % 100 != 12)
		return (i + "nd");
		else
		return (i + "th");
		case 3:
		if (i % 100 != 13)
		return (i + "rd");
		else
		return (i + "th");
		default:
		break;
		}
		return "";
		}
	
	
	public static int Setup(Scanner input, String[] names, double[]prices, double[]discounts) {
		System.out.print("Please enter the number of items:");
		int items = input.nextInt();
		
		
		
		for (int i = 0; i < items; i++){ 
			
			System.out.print("Enter name of the " + numSuffix(i+1) + " product:"); 
			names[i] = input.next(); 
			
			System.out.print("Enter the per package price of " + names[i] +": ");
			prices[i] = input.nextDouble();	
			
			System.out.print("Enter the number of packages ('x') to qualify for Special Discount (buy 'x' get 1 free) for " + names[i] + " or 0 if no Special Discount offered:");
			discounts[i] = input.nextDouble();
		}
		
		
		System.out.print("Enter the dollar amount to qualify for Additional Discount (or 0 if none offered): ");
		pdiscount = input.nextDouble(); 
		if(pdiscount != 0) {
		System.out.print("Please enter the discount rate (0.1 for 10%):");
		discount = input.nextDouble();	  
		while(discount<0 || discount>=0.5) {
				while (discount<0) {
					System.out.println("Invalid Input. Enter a value >= 0: ");
					discount=input.nextDouble();
				}
				while (discount>=0.5) {
					System.out.println("Invalid input. Enter a value <= 0.5: ");
					discount=input.nextDouble();
		}
		}
		}
		return items;
		
	} 
	public static void Buy(Scanner input, String[] names, double[] prices, int[] amounts, int items) {
	
		for (int i = 0; i < items; i++){
			System.out.print("Enter the number of " + names[i] + " packages to buy: ");
			amounts[i] = input.nextInt(); 
			while(amounts[i]<0) {
				while (amounts[i]<0) {
					System.out.println("Invalid Input. Enter a value >= 0: ");
					amounts[i]=input.nextInt();
			}
			}
		}
		
		
	}
	public static void List(String [] names, double[] prices, int[] amounts, int items) {
		
		for(int i = 0; i < items; i++) {
			if (amounts[i] > 0) {
		
			System.out.println(amounts[i] + " packages of " + names[i] + " @  $" + prices[i] + " per pkg = $" + (amounts[i]*prices[i]));
			
	}
		
			}
	
	}
	public static void Checkout(double[] prices, int[] amounts, double[]discounts) {
		
		double subtotal = 0;
		double specialdis= 0;
		for(int i = 0; i < amounts.length; i++) {
		subtotal = prices[i]*amounts[i] + subtotal;
		}
		for(int i = 0; i < amounts.length; i++) {
		specialdis += prices[i]*(int)(amounts[i]/(discounts[i]+1));
		}
		
		double newsubtotal= subtotal - specialdis;
		double adddis=0;
		
		if(newsubtotal>pdiscount)
			adddis= newsubtotal*discount;
		else
			adddis=0;
		
		double finalsubtotal= newsubtotal-adddis;
		if(specialdis==0 && adddis>0) {
			System.out.println("Original Sub Total: $" + subtotal);
			System.out.println("No Special Discounts applied");
			System.out.println("New Sub Total: $" + newsubtotal);
			System.out.println("Additional " + discount*10 + "% Discount: -$" + adddis);
			System.out.println("Final Sub Total: $" + finalsubtotal);
		}
		if(specialdis==0 && adddis==0) {
			System.out.println("Original Sub Total: $" + subtotal);
			System.out.println("No Special Discounts applied");
			System.out.println("New Sub Total: $" + newsubtotal);
			System.out.println("You did not qualify for an Additional Discount");
			System.out.println("Final Sub Total: $" + finalsubtotal);
		}
		if(specialdis>0 && adddis==0) {
			System.out.println("Original Sub Total: $" + subtotal);
			System.out.println("Special Discounts: -$" + specialdis);
			System.out.println("New Sub Total: $" + newsubtotal);
			System.out.println("You did not qualify for an Additional Discount");
			System.out.println("Final Sub Total: $" + finalsubtotal);
		}
		
		if(specialdis>0 && adddis>0) {
		System.out.println("Original Sub Total: $" + subtotal);
		System.out.println("Special Discounts: -$" + specialdis);
		System.out.println("New Sub Total: $" + newsubtotal);
		System.out.println("Additional " + discount*10 + "% Discount: -$" + adddis);
		System.out.println("Final Sub Total: $" + finalsubtotal);
		}
		System.out.println("Thanks for coming!");
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		int restart =1;
		
		while(restart ==1) {
			
		final int MAX = 100;
		int set = 0;
		int buy = 0;
		int items = 0;

		String[] names = new String[MAX];
		double[] prices = new double[MAX];
		int[] amounts = new int[MAX];
		double[] discounts = new double[MAX];
		
		
		do{
			
			System.out.println("This program supports 4 functions:");
			System.out.println("1. Setup Shop");
			System.out.println("2. Buy");
			System.out.println("3. List Items");
			System.out.println("4. Checkout");
			System.out.print("Please choose the function you want:");
			int func = input.nextInt();
			
		if ( 1 <= func || func >= 4){
		
				
		if (func == 1){
			if (set == 0){
				items = Setup (input, names, prices, discounts);
				
					
			}
			
			set = 1;
			
		}
		
		if (func == 2){
			if (set == 1){
				System.out.println();
				Buy(input, names, prices, amounts, items);
				System.out.println();
				
				
			}
			else{ 
				System.out.println();
				System.out.print("Shop is not setup yet!"); 
				System.out.println();
				System.out.println();
				
		}
			buy = 1;
			
		}
			
		if (func == 3){
		
			if (buy == 1){
			System.out.println();
			List(names, prices, amounts, items);
			System.out.println();
		}		
		
			if (set == 1 && buy == 0){
			System.out.println();
			System.out.println("Try again: You have not bought anything");
			System.out.println(); 
			
			
		}	
			if (set == 0){
			System.out.println();
			System.out.println("Shop is not setup yet!");
			System.out.println();
		}
				
		}
		
		
		if (func == 4){
			if (set == 0){
				System.out.println();
				System.out.println("Shop is not setup yet!");
				System.out.println(); 
				continue;
		}
			if (buy == 0){
				System.out.println();
				System.out.println("Try again: You have not bought anything");
				System.out.println();
				continue;
		}
			if (buy == 1){
				Checkout(prices, amounts, discounts);
				break;
				
				//4. End the program
		} 	
				
			}
	
		
		else if (func > 4){
			System.out.println();
			System.out.println("Error: Do not know " + func);	
			System.out.println();
		}	
		
			}

		
		}
		while(true);
		System.out.println("Would you like to re-run (1 for yes, 0 for no)?");
		restart=input.nextInt();
		}	
	}
}
