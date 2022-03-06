package com.techelevator;

import com.techelevator.view.Menu;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

//the purpose of this class is to allow a user to interact with our vending machine object

public class VendingMachineCLI {

	//instance variables - final - main menu options
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	//instance variables - purchase menu options
	private static final String PURCHASE_MENU_FEED_MONEY = "Feed money";
	private static final String PURCHASE_MENU_SELECT_ITEM = "Select item";
	private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_ITEM, PURCHASE_MENU_FINISH_TRANSACTION};
	//creating our scanner to hold user input
	private Scanner userInput = new Scanner(System.in);
	//creating an instance of the object type "Vending Machine"
	private VendingMachine vM = new VendingMachine();

	//creating an object type "Menu" from our Menu class
	private Menu menu;

	//constructor
	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}


	public void run() {
		//while the CLI is running...
		while (true) {
			//call the "getChoiceFromOptions" method on our menu object, assign output to string variable "choice"
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			//based on the choice, do 1 of 2 things
			//if the user choses to display menu options...
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				//update the inventory list by calling the "updateInventoryList" method on our vM object
				//vM.updateInventoryList();
				//display the inventory to the user
				vM.displayInventory();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				//or else, call the "handlePurchaseMenu" method
				handlePurchaseMenu();
			}
		}
	}

	public void handlePurchaseMenu() {
		while (true) {
			System.out.println("Balance: $" + vM.getBalance());
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			System.out.println(choice);
			//todo - when the user enters an invalid amount to deposit, do we want them to have to choose feed money again?
			//todo - because if not we are forcing them to stay in the deposit menu
			//todo - because they then have to deposit money in order to get back out to the purchase menu and finish the transaction
			if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
				boolean stopAsking = false;
				while (!stopAsking) {
					try {

						System.out.println("Enter deposit amount (in whole dollars): ");
						String input = userInput.nextLine();
						Integer depositAmount = Integer.parseInt(input);

						if (depositAmount >= 0) {

							vM.feedMoney(depositAmount);

							stopAsking = true;
						} else {
							System.out.println("Error: Cannot deposit negative number");
						}

					} catch (NumberFormatException e) {

							System.out.println("Previous entry invalid, try again.");

					}
				}
			}

				if (choice.equals(PURCHASE_MENU_SELECT_ITEM)) {
					boolean repeat = false;
					while (!repeat) {
						if (vM.getBalance() != 0) {
							vM.displayInventory();
							System.out.println("Balance: $" + vM.getBalance() + "\n" + "Enter selection: ");
							String input = userInput.nextLine().toUpperCase();
							if (vM.isValidOption(input)) {
								if (vM.hasSufficientBalance(input)) {
									if (vM.isInStock(input)) {
										vM.updateQuantity(input);
										vM.newBalance(input);
										System.out.println(vM.getNoiseVM(input));
										repeat = true;
									} else {
										System.out.println("Error: item selected is not in stock");
										repeat = true;
									}
								} else {
									System.out.println("Error: insufficient funds");
									repeat=true;
								}
							} else {
								System.out.println("Error: please enter valid selection");
								repeat = false;
							}
						} else {
							System.out.println("Error: please deposit money before making selection.");
							repeat = false;
						}
					}
				}

				if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
					double quarter = .25;
					double dime = .10;
					double nickel = .05;

					double modQtrs = vM.getBalance() % quarter;
					double modDimes = modQtrs % dime;
					double modNickels = modDimes % nickel;

					double numQtrs = (vM.getBalance() - modQtrs) / quarter;
					double numDimes = (modQtrs - modDimes) / dime;
					double numNickels = (modDimes - modNickels) / nickel;
					System.out.println("Please take your change: " + "\n" + "Quarters: " + numQtrs + "\n" + "Dimes: " + numDimes + "\n" + "Nickels: " + numNickels);
					vM.setBalance(0);
					run();
				}
			}
		}


	//main method
	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
