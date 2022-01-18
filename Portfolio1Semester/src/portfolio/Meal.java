package portfolio;

import java.util.Calendar;

/**
 * The class <code>Meal</code> is used to describe a Meal, the class models all
 * items that a customer pays for at once. A meal can hold up to 50 items.
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 *
 */
public class Meal {
	/**
	 * Stores a value for calendar.
	 */
	private Calendar calendar;

	/**
	 * Array to store objects of type Item.
	 * 
	 * @see Item#Item(String, double)
	 */
	private Item[] items;

	/**
	 * Constructor for Meal which is used to create a <code>Meal</code> object. <br>
	 * Sets the <code>items</code> array to a capacity of 50.<br>
	 * Create calendar using the <b>current</b> time zone and locale of the system.
	 */
	public Meal() {
		items = new Item[50];
		calendar = Calendar.getInstance(); // creating calendar object.

	}

	/**
	 * Adds an item to the next free spot in the <code>items</code> array.
	 * <p>
	 * The method goes over the whole array and checks the array for empty places.
	 * <br>
	 * If it finds an empty place the method will add the item and return the value
	 * <code>true</code> to give feedback that the item has been successfully added.
	 * <br>
	 * If no free spot has been found the method will return <code>false</code> ->
	 * item has not been successfully added.
	 * </p>
	 * 
	 * @param nextItem Item thats going to be added to the <code>items</code> array
	 * @return <code>true</code> -> item has been added. <code>false</code> -> item
	 *         has not been added.
	 */
	public boolean addItem(Item nextItem) {
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				items[i] = nextItem;

				return true; // Item has been added

			}
		}
		return false; // Item has not been added
	}

	/**
	 * Getter method for calendar
	 * 
	 * @return the value of {@link #calendar}
	 */
	public Calendar getCal() {
		return calendar;
	}

	/**
	 * Getter method for items
	 * 
	 * @return the value of {@link #items}
	 */
	public Item[] getItems() {
		return items;
	}
}
