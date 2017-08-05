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
		
		double changeDub = ((double)(changeDue)) / 100;
		
		int twenties = (changeDue - (changeDue % 2000)) / 2000;
		changeDue = changeDue - (twenties * 2000);
		
		int tens = (changeDue - (changeDue % 1000)) / 1000;
		changeDue = changeDue - (tens * 1000);

		int fives = (changeDue - (changeDue % 500)) / 500;
		changeDue = changeDue - (fives * 500);
		
		int ones = (changeDue - (changeDue % 100)) / 100;
		changeDue = changeDue - (ones * 100);
		
		int quarters = (changeDue - (changeDue % 25)) / 25;
		changeDue = changeDue - (quarters * 25);
		
		int dimes = (changeDue - (changeDue % 10)) / 10;
		changeDue = changeDue - (dimes * 10);
		
		int nickles = (changeDue - (changeDue % 5)) / 5;
		changeDue = changeDue - (nickles * 5);

		int pennies = changeDue;
		
		printChangeDue(changeDub, twenties, tens, fives, ones, quarters, dimes, nickles, pennies);
	}
	
	public static int convertToPennies(double money) {
		int pennies = (int)(Math.round(money * 100));
		return pennies;
	}
	
	public static void printChangeDue(double change, int twenty, int ten, int five, int one, int quart, int dime, int nick, int pen) {
		
		System.out.println("\n**************************\n");
		System.out.println("Change Due: $" + change);
		System.out.println("**************************\n");
		System.out.println(twenty + " twenty dollar bills \n" +
				ten + " ten dollar bills\n" +
				five + " five dollar bills\n" +
				one + " one dollar bills\n" + 
				quart + " quarters\n" + 
				dime + " dimes\n" + 
				nick + " nickles\n" + 
				pen + " pennies\n");
	}

}
