package portfolio;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * This class is used to test the whole program.
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 *
 */
public class testMain {
	public static void main(String[] args) {

		Card card = new Card(20.00, BuyerGroup.STUDENT);

		Calendar c1 = Calendar.getInstance();

		Meal meal1 = new Meal();
		Item burger = new Item("Burger", 2.00);
		Item pizza = new Item("Pizza", 5.00);
		meal1.addItem(pizza);
		meal1.addItem(burger);
		card.charge(meal1);

		Meal meal2 = new Meal();
		Item salad = new Item("Salad", 4.00);
		Item pasta = new Item("Pasta", 5.00);
		meal2.addItem(salad);
		meal2.addItem(pasta);
		card.charge(meal2);

		Meal meal3 = new Meal();
		Item banana = new Item("Banana", 0.20);
		Item apple = new Item("Apple", 0.40);
		Item refund = new Item("Refund", -5.00);
		meal3.addItem(banana);
		meal3.addItem(apple);
		meal3.addItem(salad);
		meal3.addItem(refund);
		card.charge(meal3);

		Meal meal4 = new Meal();
		Item napkins = new Item("Napkins", 0.0);
		meal4.addItem(napkins);
		card.charge(meal4);

		card.getBalance();

		Calendar c2 = Calendar.getInstance();

		card.printBalance(c1, c2);

	}
}
