package portfolio;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * A few Tests for the {@link Item#calculateEndPrice calculateEndPrice} method
 * 
 * @author Giuseppe Fallea
 * @version 1.0.0
 *
 */
class TestCalculateEndPrice {
	private static Item testItemDecimal, testItemNegative, testItemZero, testItemRoundUp, testItemRoundDown;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		testItemDecimal = new Item("Pizza", 5.20);
		testItemNegative = new Item("Burger", -3.00);
		testItemZero = new Item("Coke Zero", 0.00);
		testItemRoundUp = new Item("Doughnut", 1.657);
		testItemRoundDown = new Item("Fisch", 1.6549);

	}

	/**
	 * Testing buyer group {@link BuyerGroup#STUDENT Student}
	 */
	@Test
	public void testCalculateEndPriceDecimalStudent() {
		// arrange
		double expected = 5.20;

		// act
		double result = testItemDecimal.calculateEndPrice(BuyerGroup.STUDENT);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceDecimal Ergebnis falsch");

	}

	/**
	 * Testing buyer group {@link BuyerGroup#EMPLOYEE Employee}
	 */
	@Test
	public void testCalculateEndPriceDecimalEmployee() {
		// arrange
		double expected = 9.36;

		// act
		double result = testItemDecimal.calculateEndPrice(BuyerGroup.EMPLOYEE);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceDecimal Ergebnis falsch");

	}

	/**
	 * Testing buyer group {@link BuyerGroup#PUPIL Pupil}
	 */
	@Test
	public void testCalculateEndPriceDecimalPupil() {
		// arrange
		double expected = 5.72;

		// act
		double result = testItemDecimal.calculateEndPrice(BuyerGroup.PUPIL);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceDecimal Ergebnis falsch");

	}

	/**
	 * Tests if its possible to get a refund.
	 */
	@Test
	public void testCalculateEndPriceNegative() {
		// arrange
		double expected = -3.00;

		// act
		double result = testItemNegative.calculateEndPrice(BuyerGroup.STUDENT);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceNegative Ergebnis falsch");

	}

	/**
	 * Test with the value 0.
	 */
	@Test
	public void testCalculateEndPriceZero() {
		// arrange
		double expected = 0.00;

		// act
		double result = testItemZero.calculateEndPrice(BuyerGroup.STUDENT);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceZero Ergebnis falsch");

	}

	/**
	 * Tests whether the second decimal place is rounded correctly
	 * {@link #testItemRoundUp}
	 *
	 */
	@Test
	public void testCalculateEndPriceRoundUp() {
		// arrange
		double expected = 1.66;

		// act
		double result = testItemRoundUp.calculateEndPrice(BuyerGroup.STUDENT);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceRound Ergebnis falsch");

	}

	/**
	 * Tests if rounding down is always performed from the second decimal place
	 * onwards
	 * 
	 * @link {@link #testItemRoundDown}
	 */
	@Test
	public void testCalculateEndPriceRoundDown() {
		// arrange
		double expected = 1.65;

		// act
		double result = testItemRoundDown.calculateEndPrice(BuyerGroup.STUDENT);

		// assert
		assertEquals(expected, result, "testCalculateEndPriceRound Ergebnis falsch");

	}

}
