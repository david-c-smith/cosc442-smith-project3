/**
 * 
 */
package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Dave
 *
 */
public class VendingMachineItemTest {
	
	private VendingMachineItem item;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		item = new VendingMachineItem("Test item", 10);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		System.gc();
	}
	
	/**
	 * Testing the constructor for name and price values 
	 */
	@Test
	public void constructorSetValuesTest () {
		assertEquals("Test item", item.getName());
		assertEquals(10, item.getPrice(), 0.001);
	}
	
	/**
	 * Testing the exception for a price < 0 in the constructor
	 */
	@Test (expected = VendingMachineException.class)
	public void VendingMachineItemLessThanOrEqualToZeroTest () {
		item = new VendingMachineItem("Test item", 0);
		item = new VendingMachineItem("Test item", -3);

	}
	
	/**
	 * Testing the getName method for values
	 */
	@Test
	public void getNameTest() {
		assertEquals("Test item", item.getName());
	}
	
	/**
	 * Testing the getPrice method for values 
	 */
	@Test
	public void getPriceTest() {
		assertEquals(10, item.getPrice(), 0.001);
	}
}
