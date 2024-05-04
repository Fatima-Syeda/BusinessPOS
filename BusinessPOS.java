//Import Java Swing libraries
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//Create a Business POS class which extends JFrame and implements ActionListener
public class BusinessPOS extends JFrame implements ActionListener {

	// Create an array of buttons for all the different items
	JButton[] food = new JButton[20];

	// Store the names of the items in an array
	String[] foodName = { "BBQ Chicken", "Samosas", "Garlic Bread", "Poutine", "Lasagna", "Corn",
			"Sushi", "Biryani", "Fish", "Fries", "Salad", "Fanta", "Sprite", "Mango Smoothie",
			"Coke", "Cheesecake", "Brownies", "Cookie", "Rice Pudding", "Donuts" };

	// Store the prices of the items in an array
	double[] price = { 8.99, 5.99, 3.99, 15.99, 19.99, 4.00, 6.00, 16.50, 14.50, 2.99, 1.50, 1.80,
			1.85, 5.00, 1.70, 5.00, 4.00, 1.00, 10.00, 5.50 };

	// Create an arrayList that has all the food items clicked
	ArrayList<String> nameBought = new ArrayList<String>();

	// Create an arraylist that stores items with empDisc
	ArrayList<String> empDiscBought = new ArrayList<String>();

	// Declare buttons for certain actions
	JButton bMealDisc, bVoid, bClear, bEmpDisc, bTotal;

	// Create labels all the text on the screen
	JLabel lStoreName, lSlogan, lStatus;
	JLabel lCost, lTax, lTotal, lNumItems;

	// Create text fields/areas/ScrollPane
	JTextField tfCost, tfTax, tfTotal, tfNumItems;
	JTextArea totalTextArea;
	JScrollPane totalTAScroll;

	// Declare and initialize all variables
	static double cost = 0;
	static double tax = 0;
	static double total = 0;
	static int numItems = 0;
	static boolean voidClicked = false;
	static boolean mealDisc = false;
	static boolean empDisc = false;
	static final double EMP_DISC = 0.75;
	static final double MEAL_DISC = 0.90;
	static double newPrice = 0;
	static int item1, item2, item3, item4;
	static int mealDiscDone = 0;

	BusinessPOS() {

		// Make font objects
		Font buttonFont = new Font("Lucida Console", Font.PLAIN, 13);
		Font ctrlButtonFont = new Font("Lucida Console", Font.PLAIN, 13);
		Font text = new Font("Courier New", Font.PLAIN, 12);

		// Set the size and tile of the window
		setSize(1100, 600);
		setTitle("My Restaurant");
		// setResizable(false);

		// Create all JPanels
		JPanel mainP = new JPanel();
		mainP.setLayout(new BorderLayout());
		mainP.setBorder(new LineBorder(new Color(0, 255, 255), 10));
		mainP.setBackground(new Color(195, 177, 225));

		JPanel northP = new JPanel();
		northP.setLayout(new GridLayout(3, 1));
		northP.setBackground(new Color(195, 177, 225));

		JPanel southP = new JPanel();
		southP.setLayout(new BorderLayout());
		southP.setBackground(new Color(255, 182, 193));

		JPanel westP = new JPanel();
		westP.setLayout(new GridLayout(5, 5));

		JPanel eastP = new JPanel();
		eastP.setLayout(new BorderLayout());
		eastP.setBackground(new Color(195, 177, 225));

		JPanel eastSouthP = new JPanel();
		eastSouthP.setLayout(new GridLayout(4, 2));
		eastSouthP.setBackground(new Color(195, 177, 225));

		// Instantiate all buttons
		for (int i = 0; i < 20; ++i) {
			food[i] = new JButton(foodName[i]);
			food[i].setFont(buttonFont);
			food[i].addActionListener(this);
			food[i].setForeground(
					new Color((int) (Math.random() * 236), (int) (Math.random() * 128), (int) (Math.random() * 128)));

			// Add the buttons to the west panel
			westP.add(food[i]);

		} // end for

		// Create buttons and add them to their panels
		bMealDisc = new JButton("MealDisc");
		bMealDisc.setFont(ctrlButtonFont);
		bMealDisc.addActionListener(this);
		westP.add(bMealDisc);

		bVoid = new JButton("*VOID*");
		bVoid.setFont(ctrlButtonFont);
		bVoid.addActionListener(this);
		westP.add(bVoid);

		bClear = new JButton("*CLEAR*");
		bClear.setFont(ctrlButtonFont);
		bClear.addActionListener(this);
		westP.add(bClear);

		bEmpDisc = new JButton("EmpDisc");
		bEmpDisc.setFont(ctrlButtonFont);
		bEmpDisc.addActionListener(this);
		westP.add(bEmpDisc);

		bTotal = new JButton("Total");
		bTotal.setFont(ctrlButtonFont);
		bTotal.addActionListener(this);
		westP.add(bTotal);

		// Instantiate the JLabels and JTextFields and add to panels
		lStoreName = new JLabel("Moody Foodies");
		lStoreName.setFont(new Font("Vladimir Script", Font.BOLD, 35));
		lStoreName.setHorizontalAlignment(SwingConstants.CENTER);
		northP.add(lStoreName);

		lSlogan = new JLabel("If you're feeling moody, come to Moody Foodies!");
		lSlogan.setFont(new Font("Script MT Bold", Font.PLAIN, 14));
		lSlogan.setHorizontalAlignment(SwingConstants.CENTER);
		lSlogan.setForeground(Color.DARK_GRAY);
		northP.add(lSlogan);

		northP.add(new JLabel(""));

		lStatus = new JLabel("Ready...");
		southP.add(lStatus);

		lCost = new JLabel("Cost:");
		tfCost = new JTextField();
		tfCost.setFont(text);
		tfCost.setEditable(false);
		tfCost.setText(String.format("%15.2f", 0.0));
		tfCost.setBackground(new Color(255, 182, 193));
		eastSouthP.add(lCost);
		eastSouthP.add(tfCost);

		lTax = new JLabel("Tax:");
		tfTax = new JTextField();
		tfTax.setFont(text);
		tfTax.setEditable(false);
		tfTax.setText(String.format("%15.2f", 0.0));
		tfTax.setBackground(new Color(0, 255, 255));
		eastSouthP.add(lTax);
		eastSouthP.add(tfTax);

		lTotal = new JLabel("Total:");
		tfTotal = new JTextField();
		tfTotal.setFont(text);
		tfTotal.setEditable(false);
		tfTotal.setText(String.format("%15.2f", 0.0));
		tfTotal.setBackground(new Color(255, 182, 193));
		eastSouthP.add(lTotal);
		eastSouthP.add(tfTotal);

		lNumItems = new JLabel("# of items");
		tfNumItems = new JTextField();
		tfNumItems.setFont(text);
		tfNumItems.setEditable(false);
		tfNumItems.setText(String.format("%15d", 0));
		tfNumItems.setBackground(new Color(0, 255, 255));
		eastSouthP.add(lNumItems);
		eastSouthP.add(tfNumItems);

		// Create a total text are and add a Scroll to it
		totalTextArea = new JTextArea(15, 30);
		totalTextArea.setEditable(false);
		totalTextArea.setFont(text);
		totalTAScroll = new JScrollPane(totalTextArea,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		eastP.add("North", totalTAScroll);
		eastP.add("South", eastSouthP);

		// Add all the panel into the main panel
		mainP.add("North", northP);
		mainP.add("West", westP);
		mainP.add("East", eastP);
		mainP.add("South", southP);

		// Add the main panel to the JFrame
		add(mainP);

		setVisible(true);

	}// BusinessPOS()

	public void actionPerformed(ActionEvent e) {
		// Check if a food item button was clicked

		for (int i = 0; i < 20; ++i) {

			// Check if a food object was clicked and the discount buttons have not been
			// clicked
			if (e.getSource() == food[i] && voidClicked == false && mealDisc == false && empDisc == false) {

				// Display 'running' on the status bar
				lStatus.setText("Running...");

				// Add the name of the items to an arrayList
				nameBought.add(foodName[i]);

				// Add that item and price to the screen
				totalTextArea.setText(totalTextArea.getText() + String.format("%-20s %5.2f \n", foodName[i], price[i]));

				// Add price to cost
				cost += price[i];

				// Keep track of number of items
				numItems += 1;
			} // end if
		} // end for

		// If the total button is clicked, display all the costs and number of items
		if (e.getSource() == bTotal) {

			// calculate the tax and total cost
			tax = 0.13 * cost;
			total = cost + tax;

			// Display the number of the appropriate text fields
			tfCost.setText(String.format("%15.2f", cost));
			tfTax.setText(String.format("%15.2f", tax));
			tfTotal.setText(String.format("%15.2f", total));
			tfNumItems.setText(String.format("%15d", numItems));

			// Display that transaction is complete on the status bar
			lStatus.setText("Transaction Complete");
		} // end if

		// If Clear button is clicked, clear everything from the screen and reset
		// all variables
		if (e.getSource() == bClear) {
			lStatus.setText("Ready...");
			mealDiscDone = 0;
			nameBought.clear();
			voidClicked = false;
			totalTextArea.setText("");
			tfCost.setText(String.format("%15.2f", 0.0));
			tfTax.setText(String.format("%15.2f", 0.0));
			tfTotal.setText(String.format("%15.2f", 0.0));
			tfNumItems.setText(String.format("%15d", 0));

			bEmpDisc.setEnabled(true);
			bMealDisc.setEnabled(true);

			cost = 0;
			tax = 0;
			total = 0;
			numItems = 0;
		} // end if

		// Check if meal discount button is clicked and there are items on the screen
		// and
		// the mealDiscount has not been used yet
		if (e.getSource() == bMealDisc && nameBought.size() >= 0 && mealDiscDone == 0) {
			mealDisc = true;
			lStatus.setText("Items must include: BBQ Chicken, Garlic Bread, Sprite, and Brownies");
		} // end if

		// If the meal discount has already been used, display that it can't be used
		// again
		// on the status bar
		else if (e.getSource() == bMealDisc && (nameBought.size() != 0 || mealDiscDone == 1)) {
			lStatus.setText("Cannot have a meal discount more than once");
		} // end else if

		// If meal discount button is clicked
		if (mealDisc == true) {

			// Set variables that check for appropriate items to 0
			item1 = 0;
			item2 = 0;
			item3 = 0;
			item4 = 0;

			// Set mealDisc to false
			mealDisc = false;

			// Loop through items bought and check if it has appropriate items for the meal
			// discount
			for (int i = 0; i < nameBought.size(); i++) {

				// If the appropriate item is found, set that item's variable to 1
				if (nameBought.get(i) == "BBQ Chicken") {
					item1 = 1;
				} // end if

				if (nameBought.get(i) == "Garlic Bread") {
					item2 = 1;
				} // end if

				if (nameBought.get(i) == "Sprite") {
					item3 = 1;
				} // end if

				if (nameBought.get(i) == "Brownies") {
					item4 = 1;
				} // end if

			} // end for

			// If all items for the meal discount are in the list
			if (item1 == 1 && item2 == 1 && item3 == 1 && item4 == 1) {

				// calculate the discounted price
				newPrice = cost * MEAL_DISC;

				// Set mealDiscDone to 1 so that person can't get a meal discount again
				mealDiscDone = 1;

				// Update status bar
				lStatus.setText("10% discount on running total");

				// Display the discounted price to the screen
				totalTextArea.setText(totalTextArea.getText() + "\nMeal Discount: 10% off\n");
				totalTextArea
						.setText(totalTextArea.getText() + String.format("%-20s %5.2f \n", "Running Total:", cost));
				totalTextArea.setText(
						totalTextArea.getText() + String.format("%-20s %5.2f \n", "New Total Cost:", newPrice));
				totalTextArea.setText(totalTextArea.getText() + "\n");

				// Set cost to the new price
				cost = newPrice;
			} // end if

		} // end if

		// If button for employee discount is clicked, set empDisc to true
		if (e.getSource() == bEmpDisc) {
			empDisc = true;
			lStatus.setText("Select item for employee discount");

		} // end if

		// If empDisc is true
		if (empDisc == true) {

			// Get user to click on the food item they want the discount on
			for (int i = 0; i < 20; i++) {
				if (e.getSource() == food[i]) {

					// Add item to list for bought items
					nameBought.add(foodName[i]);
					empDiscBought.add(foodName[i]);

					// Calculate the new price of the item and ad it to the running total cost
					newPrice = EMP_DISC * price[i];
					cost += newPrice;
					numItems += 1;

					totalTextArea.setText(totalTextArea.getText() + "\nEmployee Discount: 25% off\n");
					totalTextArea
							.setText(totalTextArea.getText() + String.format("%-20s %5.2f \n", foodName[i], newPrice));
					totalTextArea.setText(totalTextArea.getText() + "\n");

					// Set empDisc to false and update the status bar
					empDisc = false;
					// lStatus.setText("Running...");
				} // end if
			} // end for
		} // end if

		// If the void button is clicked
		if (e.getSource() == bVoid) {

			// Create toggle
			voidClicked = !voidClicked;

		} // end if

		if (voidClicked == true && nameBought.size() == 0) {
			lStatus.setText("No items on list to void");
		}

		// if voidClicked is false
		if (voidClicked == false) {

			bEmpDisc.setEnabled(true);
			bMealDisc.setEnabled(true);

		}

		// If voidClicked is true
		if (voidClicked == true && nameBought.size() > 0) {

			lStatus.setText("Select item to void");

			bEmpDisc.setEnabled(false);
			bMealDisc.setEnabled(false);

			if (e.getSource() == bMealDisc || e.getSource() == bEmpDisc) {
				lStatus.setText("Please click void again if done selceting items");
			}

			// Create a variable to keep track of the number of item found in the list to
			// void
			int num = 0;

			// get user to click on a food button to void
			for (int i = 0; i < 20; i++) {

				if (e.getSource() == food[i]) {

					// Loop through the array list that keeps track of all the items selected
					for (int j = 0; j < nameBought.size(); j++) {

						// If the item clicked is found in the list
						if (nameBought.get(j) == foodName[i]) {

							if (empDiscBought.contains(nameBought.get(j))) {
								cost -= price[i] * EMP_DISC;
								numItems -= 1;

								totalTextArea.setText(totalTextArea.getText() + "\nVOID\n");
								totalTextArea.setText(totalTextArea.getText()
										+ String.format("%-20s-%5.2f \n", foodName[i], price[i] * EMP_DISC));
								totalTextArea.setText(totalTextArea.getText() + "\n");
								lStatus.setText("Item voided");

								// Remove that item from the list
								nameBought.remove(j);
								empDiscBought.remove(j);
								num++;
							}

							else if (nameBought.contains(foodName[i])) {

								// Subtract the price of the voided item and subtract 1 from numItems
								cost -= price[i];
								numItems -= 1;

								// Display the item voided on the screen
								totalTextArea.setText(totalTextArea.getText() + "\nVOID\n");
								totalTextArea.setText(totalTextArea.getText()
										+ String.format("%-20s-%5.2f \n", foodName[i], price[i]));
								totalTextArea.setText(totalTextArea.getText() + "\n");
								lStatus.setText("Item voided");

								nameBought.remove(j);
								num++;
							}

						}

						// If the list does not contain the item that needs to be voided

						else {

							// Display that the item is not in the list
							lStatus.setText("Item not on the list");
						} // end else

						// When one item is voided, break
						if (num == 1) {
							break;

						} // end if(num==1)
					} // end if(nameBought.get(j)==foodName[i])

				} // end if(e.getSource()==food[i])

			} // end for

		} // end if(voidClicked==true)

	}// Action Performed

	public static void main(String[] args) {
		new BusinessPOS();

	}// end main

}// end main
