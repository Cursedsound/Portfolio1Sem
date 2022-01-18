package portfolio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests for the {@link Card#charge charge} method
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 *
 */
class TestCharge {
	private static Card cardChargeStudent, cardChargeEmployee, cardChargePupil, cardChargedRefund, cardChargeNegative,
			cardChargeZero;
	private static Meal mealCharge, mealChargeRefund, mealChargeNegative, mealChargeZero;
	private static Item burger, pizza, salad, napkins, cola, doener, pasta;
	private static boolean thrown;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		// Setting the balance and buyer groups on the cards
		cardChargeStudent = new Card(8.00, BuyerGroup.STUDENT);
		cardChargeEmployee = new Card(10.00, BuyerGroup.EMPLOYEE);
		cardChargePupil = new Card(10.00, BuyerGroup.PUPIL);
		cardChargedRefund = new Card(8.00, BuyerGroup.STUDENT);
		cardChargeNegative = new Card(8.00, BuyerGroup.STUDENT);
		cardChargeZero = new Card(8.00, BuyerGroup.EMPLOYEE);

		// creating meal objects
		mealCharge = new Meal();
		mealChargeRefund = new Meal();
		mealChargeNegative = new Meal();
		mealChargeZero = new Meal();

		// creating and adding the items which are used for more than one test.
		burger = new Item("Burger", 1);
		pizza = new Item("Pizza", 3);
		mealCharge.addItem(burger);
		mealCharge.addItem(pizza);
	}

	@Test
	void testChargeStudent() {
		// arrange
		cardChargeStudent.charge(mealCharge);
		double expected = 4;
		// act
		double result = cardChargeStudent.getBalance();

		// assert
		assertEquals(expected, result, "Result is wrong! ");
	}

	@Test
	void testChargeEmployee() {
		// arrange
		cardChargeEmployee.charge(mealCharge);
		double expected = 2.8;
		// act
		double result = cardChargeEmployee.getBalance();

		// assert
		assertEquals(expected, result, 0, "Result is wrong! ");
	}

	@Test
	void testChargePupil() {
		// arrange
		cardChargePupil.charge(mealCharge);
		double expected = 5.6;
		// act
		double result = cardChargePupil.getBalance();

		// assert
		assertEquals(expected, result, 0, "Result is wrong! ");
	}

	/**
	 * Gives a refund to the card
	 */
	@Test
	void testChargeRefund() {
		// arrange
		// the value -6 will add 6 to the balance
		salad = new Item("Salad", -6);
		mealChargeRefund.addItem(salad);
		cardChargedRefund.charge(mealChargeRefund);
		double expected = 14;
		// act
		double result = cardChargedRefund.getBalance();

		// assert
		assertEquals(expected, result, "Result is wrong! ");
	}

	/**
	 * Charges the card with the amount <code>0</code> to see if the balance stays
	 * the same.
	 */
	@Test
	void testChargeZero() {
		// arrange
		napkins = new Item("Napkins", 0);
		mealChargeZero.addItem(napkins);
		cardChargeZero.charge(mealChargeZero);
		double expected = 8;
		// act
		double result = cardChargeZero.getBalance();

		// assert
		assertEquals(expected, result, "Result is wrong! ");
	}

	/**
	 * Charging the card with more than what's on the balance to test the
	 * IllegalArgumentException.
	 */
	@Test
	@DisplayName("testIllegalArgumentException")
	void testChargeNegative() {
		// arrange
		thrown = false;
		cola = new Item("Cola", 2);
		doener = new Item("Döner", 5);
		pasta = new Item("Pasta", 5);

		// act
		try {
			mealChargeNegative.addItem(cola);
			mealChargeNegative.addItem(doener);
			mealChargeNegative.addItem(pasta);
			cardChargeNegative.charge(mealChargeNegative);
			// catching the IllegalArgumentException
		} catch (IllegalArgumentException e) {
			thrown = true;
		}

		// assert
		assertTrue(thrown);

	}

}
