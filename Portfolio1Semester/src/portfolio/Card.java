package portfolio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;

/**
 * The <code>Card</code> class simulates a Card used by the {@link BuyerGroup}
 * to purchase meals. It has all the necessary methods to charge the card and
 * print the balance to a XML document. The Card class contains:
 * <ul>
 * <li>Card Constructor {@link #Card}.
 * <li>method to get the balance {@link #getBalance}.
 * <li>method to get calculate the meal price {@link #calculateMealPrice}.
 * <li>method to charge the card {@link #charge}.
 * <li>method to print the balance to a XML file {@link #printBalance}.
 * </ul>
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 *
 */
public class Card {

	/**
	 * Stores a value for BuyerGroup
	 * 
	 * @see #group
	 */
	private BuyerGroup group;

	/**
	 * Stores the balance
	 * 
	 * @see #getBalance()
	 */
	private double balance;

	/**
	 * array which stores the purchased meals.
	 * 
	 * @see #purchasedMeals
	 */
	private Meal[] purchasedMeals;

	/**
	 * A constructor for a card.
	 * 
	 * @param balance the <code>balance</code> thats on the card.
	 * @param group   affiliation of the persons BuyerGroup <code>group</code>.
	 */
	public Card(double balance, BuyerGroup group) {
		this.balance = balance;
		this.group = group;
		this.purchasedMeals = new Meal[200];
	}

	/**
	 * Returns the specific monetary value.
	 * 
	 * @return the <code>balance</code> remaining on the card
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Calculates the meal price based on the {@link BuyerGroup}
	 * 
	 * @param meal  collection of items
	 * @param group affiliation of the persons <code>BuyerGroup</code>
	 * @return <code>endPrice</code>
	 */
	public double calculateMealPrice(Meal meal, BuyerGroup group) {
		double endPrice = 0.0;
		for (int i = 0; meal.getItems()[i] != null && i < meal.getItems().length; i++) {
			endPrice = endPrice + meal.getItems()[i].calculateEndPrice(group);

		}
		return endPrice;
	}

	/**
	 * The <code>charge</code> method deducts the amount to be debited from the
	 * account balance.
	 * <p>
	 * The method will throw an <b>IllegalArgumentException</b> if the user does not
	 * have enough balance and also if the meal is full.
	 * </p>
	 * 
	 */
	public void charge(Meal meal) {
		int i = 0;
		// checks if there is enough money on card.
		// if there is not enough money on the card it will throw an
		// IllegalArgumentException.
		if (balance - calculateMealPrice(meal, group) < 0) {

			throw new IllegalArgumentException("Not enough money to complete the transaction!");
		} else {
			while (purchasedMeals[i] != null && i < purchasedMeals.length) {
				i++;

			}
			// Checks if meal is full.
			if (i == purchasedMeals.length && purchasedMeals[i] != null) {
				// Throws an IllegalArgumentException
				throw new IllegalArgumentException("Meal is full! ");
			} else {
				// deducts the amount to be debited from the account balance
				balance = balance - calculateMealPrice(meal, group);
				// adds the meal to the purchased meal array
				purchasedMeals[i] = meal;
			}
		}
	}

	/**
	 * Outputs the value of the balance to an XML file. <br>
	 * Please change the output path to your path. <br>
	 * Example: "C:\\Users\\user\\Desktop" <br>
	 * <p>
	 * Only the meals purchased in the Time between <code>from</code> and
	 * <code>to</code> will be added to the XML file, <br>
	 * </p>
	 * 
	 * @param from which date the statement of account should start.
	 * @param to   which date the statement of account should end.
	 */
	public void printBalance(Calendar from, Calendar to) {
		try {
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("statementOfAccount.xml"), "UTF-8");
			writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
			writer.write("<!DOCTYPE statementOfAccount SYSTEM \"statementOfAccount.dtd\">\n");
			writer.write("<statementOfAccount>\n");
			// cleaner output instead of: myFile.write("\t<startdatum>" + from.getTime() +
			// "" + "</startdatum>\n")
			writer.write("\t<startDate>" + from.get(Calendar.DATE) + "." + (((int) from.get(Calendar.MONTH)) + 1) + "."
					+ from.get(Calendar.YEAR) + " " + from.get(Calendar.HOUR) + ":" + from.get(Calendar.MINUTE) + ":"
					+ from.get(Calendar.SECOND) + " Uhr" + "</startDate>\n");
			for (int i = 0; i < purchasedMeals.length; i++) {

				// If first value is >= 0 and the second value is <= 0 the date of the purchased
				// meal(s) is between - or equal to - "from" and "to"

				if (purchasedMeals[i].getCal().compareTo(from) >= 0 && purchasedMeals[i].getCal().compareTo(to) <= 0) {
					writer.write("\t<meal>\n");
					writer.write("\t\t<purchaseDate>" + purchasedMeals[i].getCal().get(Calendar.DATE) + "."
							+ (((int) purchasedMeals[i].getCal().get(Calendar.MONTH)) + 1) + "."
							+ purchasedMeals[i].getCal().get(Calendar.YEAR) + "</purchaseDate>\n");
					writer.write("\t\t<totalPrice UNIT=\"euro\">"
							+ Item.round(calculateMealPrice(purchasedMeals[i], group)) + "</totalPrice>\n");

					// [i] goes through the meals and [j] goes through the items
					for (int j = 0; j < purchasedMeals[i].getItems().length; j++) {
						if (purchasedMeals[i].getItems()[j] != null) {
							writer.write("\t\t<item>\n");
							writer.write("\t\t\t<name>" + purchasedMeals[i].getItems()[j].getName() + "</name>\n");
							writer.write("\t\t\t<price UNIT=\"euro\">"
									+ purchasedMeals[i].getItems()[j].calculateEndPrice(group) + "" + "</price>\n");
							writer.write("\t\t</item>\n");
						}
						if (purchasedMeals[i].getItems()[j] == null) {
							break;
						}

					}
					writer.write("\t</meal>\n");

				}
				// check if the next purchased meal array is empty
				if (purchasedMeals[i + 1] == null) {
					break;
				}
			}
			writer.write("\t<balance UNIT=\"euro\">" + Item.round(getBalance()) + "</balance>\n");
			writer.write("\t<endDate>" + to.get(Calendar.DATE) + "." + (((int) to.get(Calendar.MONTH)) + 1) + "."
					+ to.get(Calendar.YEAR) + " " + to.get(Calendar.HOUR) + ":" + to.get(Calendar.MINUTE) + ":"
					+ to.get(Calendar.SECOND) + " Uhr" + "</endDate>\n");

			writer.write("</statementOfAccount>\n");

			writer.close();
			System.out.println("File sucessfully created");
		} catch (

		IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}

	}

}
