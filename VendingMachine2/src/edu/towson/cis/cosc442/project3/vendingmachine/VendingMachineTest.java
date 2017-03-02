package edu.towson.cis.cosc442.project3.vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	VendingMachine vendingMachine;
	VendingMachineItem item;
	
	@Before
	public void setUp() throws Exception {
		vendingMachine = new VendingMachine();
		item = new VendingMachineItem("Test Item", 10.00);
	}

	@After
	public void tearDown() throws Exception {
		System.gc();
	}

	/**
	 * Tests that an added item was actually added
	 */
	@Test
	public void addItemTest() {	
		vendingMachine.addItem(item, VendingMachine.A_CODE);
		assertEquals(item, vendingMachine.getItem(VendingMachine.A_CODE));
	}
	
	/**
	 * Tests that a removed item was actually removed
	 */
	@Test
	public void removeItemTest() {	
		vendingMachine.addItem(item, VendingMachine.A_CODE);
		vendingMachine.removeItem(VendingMachine.A_CODE);
		assertEquals(null, vendingMachine.getItem(VendingMachine.A_CODE));
	}
	
	/**
	 * Tests that money was inserted into the VendingMachine
	 */
	@Test 
	public void insertMoneyTest() {
		vendingMachine.insertMoney(10.0);
		assertEquals(10.0, vendingMachine.getBalance(), 0.001);
	}
	
	/**
	 * Tests that money inserted into the VendingMachine is not < 0
	 */
	@Test (expected = VendingMachineException.class)
	public void insertMoneyLessThanZeroTest() {
		vendingMachine.insertMoney(-8.00);
	}
	
	/**
	 * Tests the getBalance method
	 */
	@Test 
	public void getBalanceTest() {
		assertEquals(0.00, vendingMachine.getBalance(), 0.001);
	}
	
	/**
	 * Tests the makePurchase method to:
	 * 1) Make sure the vending machine returned true
	 * 2) Make sure item was removed from machine
	 * 3) Make sure balance is updated after purchase
	 **/
	@Test
	public void makePurchaseTest() {
		vendingMachine.addItem(item, VendingMachine.A_CODE);
		vendingMachine.insertMoney(15.00);
		assertEquals(true,vendingMachine.makePurchase(VendingMachine.A_CODE));
		assertEquals(null,vendingMachine.getItem(VendingMachine.A_CODE)); 
		assertEquals(5.00, vendingMachine.getBalance(),0.001); 
	}
	
	
	/**
	 * Assures that if the customer does not have enough money to pay for an item, 
	 * the purchase cannot be made
	 */
	@Test
	public void makePurchaseNotEnoughFundsTest() {
		vendingMachine.addItem(item, VendingMachine.A_CODE);
		assertEquals(false, vendingMachine.makePurchase(VendingMachine.A_CODE));
	}
	
	/**
	 * Tests if an item is not available (not added to the vending machine, return false)
	 */
	@Test
	public void makePurchaseItemNotAvailableTest() {
		assertEquals(false, vendingMachine.makePurchase(VendingMachine.A_CODE));
	}
	
	/**
	* Tests that the correct amount of change is returned and no change is left in the machine
	*/
	@Test
	public void returnChangeTest() {
		vendingMachine.insertMoney(6.00);
		assertEquals(6.00,vendingMachine.returnChange(),0.001);
		assertEquals(0.00,vendingMachine.getBalance(),0.001);
	}
}
