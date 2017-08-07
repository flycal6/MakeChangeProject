import java.text.DecimalFormat;
import java.util.Scanner;

/* In the cash register we will calculate the amount of change returned to a customer based on the 
 * purchase price and the amount tendered. We will also notify the attendant how many of each piece 
 * of currency ($20 ,$10 ,$5 ,$1, .25c, .10c, .05c, .01c) is needed to make the change for the customer. 
 * Change should be provided using the largest bill and coin denominations as possible. Denominations that 
 * are not used should not be displayed.
 * */
public class MakeChange {
	public static void main(String[] args) {
		getPrice();
	}

	public static void getPrice() {
		Scanner input = new Scanner(System.in);

		System.out.println("What is the price? :  ");
		double price = input.nextDouble();

		getAmountTendered(price, input);
		input.close();
	}

	public static void getAmountTendered(double price, Scanner input) {
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

		if (tendered > price) {
			makeChange(tendered, price);
		}
	}

	public static void makeChange(double tendered, double price) {
		int tenderedPennies = convertToPennies(tendered);
		int pricePennies = convertToPennies(price);

		int changeDue = tenderedPennies - pricePennies;

		double changeDub = ((double) (changeDue)) / 100;

		int twenties = 0, tens = 0, fives = 0, ones = 0, quarters = 0, dimes = 0, nickels = 0;

		int[] divisor = { 2000, 1000, 500, 100, 25, 10, 5 };
		int[] denominations = { twenties, tens, fives, ones, quarters, dimes, nickels };

		// determine how many of each denomination to give back
		for (int i = 0; i < denominations.length; i++) {
			denominations[i] = (changeDue - (changeDue % divisor[i])) / divisor[i];
			changeDue = changeDue - (denominations[i] * divisor[i]);
		}

		int pennies = changeDue;

		printChangeDue(changeDub, denominations, pennies);
	}

	public static int convertToPennies(double money) {
		int pennies = (int) (Math.round(money * 100));
		return pennies;
	}

	public static void printChangeDue(double change, int[] denominations, int pen) {
		// Ensure printing of at 2 decimal places
		DecimalFormat df = new DecimalFormat("#.00");
		String changeStr = df.format(change);

		System.out.println("\n**************************\n");
		System.out.println("Change Due: $" + changeStr);
		System.out.println("**************************\n");

		String bill = " dollar bill";
		String[] value = { " twenty" + bill, " ten" + bill, " five" + bill, " one" + bill, " quarter", " dime",
				" nickel" };

		for (int i = 0; i < denominations.length; i++) {
			if (denominations[i] > 1) {
				System.out.println(denominations[i] + value[i] + "s");
			} else if (denominations[i] > 0) {
				System.out.println(denominations[i] + value[i]);
			}
		}
		
//		Pennies kept separate due to spelling
		if (pen > 1) {
			System.out.println(pen + " pennies\n");
		} else if (pen > 0) {
			System.out.println(pen + " penny\n");
		}

	}

}
