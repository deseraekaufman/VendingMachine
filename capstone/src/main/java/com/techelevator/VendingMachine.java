package com.techelevator;

//the purpose of this class is to maintain and update vending machine inventory

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
//bigdecimal here
    private double balance = 0;
    //creating a map to hold our inventory list
    private Map<String, Snack> items = new HashMap<String, Snack>();

    //this is our constructor, and the only thing it does is call the method "updateInventoryList"
    public VendingMachine() {
        updateInventoryList();
    }

    public void updateInventoryList() {
        // creates a scanner, creates a "file" object to represent our file, then scanner reads the file "vendingMachine.txt"
        try (Scanner fileReader = new Scanner(new File("ExampleFiles/VendingMachine.txt"))) {
            // for every line in the file while there is a line
            while (fileReader.hasNext()) {
                // go through each line and make an array split on the pipes
                String line = fileReader.nextLine();
                String[] lineToArray = line.split("\\|");
                //we made the slot number of our vending machine inventory list equal to the key of our "items" map that we created above
                //now, we have split our single line that we read from the VendingMachine.txt into an array of string elements
                //our array should contain each of different pieces of information that are provided per line in our .txt file
                //then, for each element in our array....
                //for (int i = 0; i < lineToArray.length; i++) {
                    //we assign the value at index 0, which we know to be the item's slot number, to a variable titled "slot"
                    String slot = lineToArray[0];
                    //same as above, we know that our snack type value is located at the 3rd index based on our .txt file
                    String snackType = lineToArray[3];
                    //here we have different if statements based on what snack type
                    //we use the information located at indexes 1 and 2 to instantiate a new "snack" object of the given type
                    //the snack object, along with its key "slot", will now be added to the map
                    if (snackType.equals("Chip")) {
                        Chip chips = new Chip(lineToArray[1], Double.parseDouble(lineToArray[2]));
                        items.put(slot, chips);
                    }
                    if (snackType.equals("Gum")) {
                        Gum gum = new Gum(lineToArray[1], Double.parseDouble(lineToArray[2]));
                        items.put(slot, gum);
                    }
                    if (snackType.equals("Candy")) {
                        Candy candy = new Candy(lineToArray[1], Double.parseDouble(lineToArray[2]));
                        items.put(slot, candy);
                    }
                    if (snackType.equals("Drink")) {
                        Drink drink = new Drink(lineToArray[1], Double.parseDouble(lineToArray[2]));
                        items.put(slot, drink);
                    }
                //}
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void printItemList() {
        for (Map.Entry<String, Snack> item : items.entrySet()) {
            System.out.print(item.getKey());
            Snack snack = item.getValue();
            System.out.println(" " + snack.getName() + " " + snack.getPrice() + " " + snack.getQuantity());
        }
    }

    public void displayInventory() {
        for (Map.Entry<String, Snack> item : items.entrySet()) {
            System.out.print(item.getKey());
            Snack snack = item.getValue();
            if (snack.getQuantity() == 0) {
                System.out.println(snack.getName() + " : " + "SOLD OUT");
            } else {
                System.out.println(" " + snack.getName() + " " + "$" + snack.getPrice());
            }
        }
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void feedMoney(double depositAmount){
            balance += depositAmount;
    }

    public void newBalance(String input) {
            Snack snack = items.get(input);
            balance -= snack.getPrice();
        }

    public boolean isValidOption(String input) {
        //Snack snack = items.get(input);
        if (items.containsKey(input)){
            return true;
        }
        return false;
    }

    public boolean hasSufficientBalance(String input) {
        Snack snack = items.get(input);
        if (snack != null && snack.getPrice() < getBalance()) {
            return true;
        }
            return false;
        }

    public boolean isInStock(String input) {
        Snack snack = items.get(input);
        if (snack != null && snack.getQuantity()>0) {
            return true;
        }
        return false;
    }

    public void updateQuantity(String input) {
            Snack snack = items.get(input);
                int newSnackQuantity = snack.getQuantity() - 1;
                snack.setQuantity(newSnackQuantity);
            }

    public String getNoiseVM(String input) {
        Snack snack = items.get(input);
        return snack.getNoise();
    }

    //todo look at below - started audit log

//    public void auditLog() {
//        try (PrintWriter dataOutput = new PrintWriter(
//                // Passing true to the FileOutputStream constructor says to append
//                new FileOutputStream(("ExampleFiles/Log.txt")), true)) {
//        } catch (FileNotFoundException e) {
//            System.err.println("Cannot open the file for writing.");
//        }
//    }






}





