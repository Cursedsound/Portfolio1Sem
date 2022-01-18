package portfolio;

/**
 * The class <code>Item</code> is used to describe an Item,<br>
 * it contains:
 * <ul>
 * <li>Item Constructor {@link #Item(String, double)}.
 * <li>method to get the name of the {@link #getName()}.
 * <li>method to get the base price of the Item {@link #getBasePrice()}.
 * <li>method to round the end price {@link #round(double)}.
 * <li>method to calculate the end price {@link #calculateEndPrice(BuyerGroup)}.
 * </ul>
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 *
 */
public class Item {

	/**
	 * Stores the name of the Item
	 */
	private String name;

	/**
	 * Stores the base price of the Item
	 */
	private double basePrice;

	/**
	 * Constructor is used to create an <code>Item</code>.
	 * 
	 * @param name      sets the name of the Item.
	 * @param basePrice sets the base price of the Item.
	 */
	public Item(String name, double basePrice) {
		this.name = name;
		this.basePrice = basePrice;

	}

	/**
	 * Method to get the item name
	 * 
	 * @return Item name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method to get the item base price
	 * 
	 * @return Item base price
	 */
	public double getBasePrice() {
		return this.basePrice;
	}

	/**
	 * Rounds a <code>double</code> to two decimal places.
	 * <p>
	 * if the third decimal place of the <code>double</code> is greater than or
	 * equal to five, <br>
	 * the method will round the <code>double</code> up.<br>
	 * in all the other cases the method will always round down.
	 * </p>
	 * 
	 * @param toRound the <code>double</code> to be rounded
	 * @return the rounded <code>double</code> either rounded up or down
	 */
	public static double round(double toRound) {
		if ((int) (toRound * 1000) % 10 >= 5) {

			return Math.ceil((toRound * 100.0)) / 100;

		} else {
			return Math.floor((toRound * 100)) / 100;

		}

	}

	/**
	 * Calculates the end price of the item based on the {@link BuyerGroup} <br>
	 * and rounds the result with {@link #round(double)}.
	 * 
	 * @see <a href=
	 *      "https://www.fiktive-mensa.de/Preistabelle.html">Preistabelle</a>.
	 * @param group affiliation of the persons BuyerGroup <code>group</code>.
	 * @return the rounded value while taking the surcharge into account
	 */
	public double calculateEndPrice(BuyerGroup group) {
		if (group == BuyerGroup.STUDENT) {
			return round(basePrice);

		} else if (group == BuyerGroup.EMPLOYEE) {
			return round(basePrice / 100 * 180);

		} else if (group == BuyerGroup.PUPIL) {
			return round(basePrice / 100 * 110);

		} else {
			return 0.0;
		}
	}
}