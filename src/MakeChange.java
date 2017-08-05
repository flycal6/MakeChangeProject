import java.util.Scanner;

/* In the cash register we will calculate the amount of change returned to a customer based on the 
 * purchase price and the amount tendered. We will also notify the attendant how many of each piece 
 * of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) is needed to make the change for the customer. 
 * Change should be provided using the largest bill and coin denominations as possible. Denominations that 
 * are not used should not be displayed.
 * */
public class MakeChange {
	public static void main(String[] args) {
		askForPrice();
	}

	public static void askForPrice() {
		Scanner input = new Scanner(System.in);

		System.out.println("What is the price? :  ");
		double price = input.nextDouble();

		askForAmountTendered(price, input);
		input.close();
	}
	
	public static void askForAmountTendered(double price, Scanner input) {
		double tendered;

		do {

			System.out.println("What amount was tendered?:  ");
			tendered = input.nextDouble();

			if (tendered < price) {
				System.out.println("You need to poney up some more money.");
			}
		} while (tendered < price);
		
		if (tendered == price) {
			System.out.println("Thanks for exact change!");
		}
	}

}
