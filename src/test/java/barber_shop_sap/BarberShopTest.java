package barber_shop_sap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import shop.BarberShop;

public class BarberShopTest {
	
	private BarberShop shop;
	
	@Before
	public void intialize() throws InterruptedException {
		shop = BarberShop.getInstance();
		shop.add();
		shop.add();
	}
	
	@Test
	public void testAdded() {
		assertEquals(2,shop.getConsumedChairs());
	}
	
	@Test
	public void testMaxCapacity(){
		assertFalse(shop.getConsumedChairs() == shop.getMaxCapacity());
	}
	
	@Test
	public void testRemove() throws InterruptedException{
		shop.add();
		shop.remove();
		assertTrue(shop.getConsumedChairs() == 2);
	}
	
	
}
