package portfolio;

/**
 * The enum <code>BuyerGroup</code> contains a fixed set of constants. <br>
 * The constants are used to calculate the end price in the Item class
 * {@link Item#calculateEndPrice(BuyerGroup)}.
 * 
 * @see Enum
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 */
public enum BuyerGroup {
	/**
	 * Students pay the base price
	 */
	STUDENT,
	/**
	 * Employees pay 180% instead of base price
	 */
	EMPLOYEE,
	/**
	 * Pupil pay 110% instead of base price
	 */
	PUPIL
}
