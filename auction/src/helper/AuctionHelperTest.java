package helper;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

public class AuctionHelperTest extends TestCase {
	@Test
	
	public void testAddItem() {
		AuctionHelper instance = new AuctionHelper();
		instance.addItem("JUnitTest1", "JUnitCategory1", "JUnitDescription1", 5.00);
		System.out.println("Item id: " + instance.auctionItemId);
		assertEquals("Add JUnit Item 1: ", "JUnitTest1", instance.getCategory("JUnitCategory1").getItemList().get(0).getTitle());
		instance.deleteItem(instance.auctionItemId);
	}
	
	
	public void testGetCategories() {
		AuctionHelper instance = new AuctionHelper();
		instance.addItem("JUnitTest1", "JUnitCategory1", "JUnitDescription1", 5.00);
		System.out.println("Testing Category: " + instance.getCategoryList()[0].categoryName);
		assertEquals("Category 1: ", "JUnitCategory1", instance.getCategoryList()[instance.getCategoryList().length-1].categoryName);
		instance.deleteItem(instance.auctionItemId);

		//fail("Not yet implemented");
	}
	
	public void testGetItemsByCategory() {
		AuctionHelper instance = new AuctionHelper();
		instance.addItem("JUnitTest1", "JUnitCategory1", "JUnitDescription1", 5.00);
		System.out.println("Testing CategoryList: " + instance.getCategoryList()[0].getItemList().get(0).getTitle());
		
		//assertEquals("Category 1: ", "JUnitCategory1", instance.getCategoryList()[0].categoryName);
		assertEquals("Category 1, Item 1: ", "JUnitTest1", instance.getCategoryList().toString().contains(getName()));
		//assertEquals("Category 1, Item 1: ", "TestItem", instance.getCategoryList()[0].auctionItems.get(0).getTitle());
		instance.deleteItem(instance.auctionItemId);
	}
	
	public void testGetItem() {
		AuctionHelper instance2 = new AuctionHelper();
		instance2.addItem("JUnitTest2", "JUnitCategory2", "JUnitDescription2", 5.00);
		System.out.println("(GetITem) Item id: " + instance2.auctionItemId);
		System.out.println("Testing GetItem: " + instance2.getItem(instance2.auctionItemId).getTitle());
		
		assertEquals("Item id 1: ", "JUnitTest2", instance2.getItem(instance2.auctionItemId).getTitle());
		//assertEquals("Category 1, Item 1: ", "TestItem", instance.getCategoryList()[0].getItemList().get(0).getTitle());
		
		instance2.deleteItem(instance2.auctionItemId);
		
	}
	
	public void testGetCategory() {
		AuctionHelper instance = new AuctionHelper();
		instance.addItem("JUnitTest1", "JUnitCategory1", "JUnitDescription1", 5.00);
		assertEquals("Item id 1: ", "JUnitTest1", instance.getCategory("JUnitCategory1").getItemList().get(0).getTitle());
		instance.deleteItem(instance.auctionItemId);
	}
		
	
	public void testUpdateBid() {
		AuctionHelper instance3 = new AuctionHelper();
		instance3.addItem("JUnitTest3", "JUnitCategory3", "JUnitDescription3", 5.00);
		instance3.updateBid(instance3.auctionItemId, "JUnit Name3", "junit3@email.com", 7.55);
		
		assertEquals("Test UpdateBid: ", 7.55, instance3.getItem(instance3.auctionItemId).getBid());
		instance3.deleteItem(instance3.auctionItemId);
		
	}
	
	public void testIsAdminLoginCredentials() {
		AuctionHelper instance = new AuctionHelper();
		boolean result = instance.isAdminLoginCredentials("admin", "password");
		
		assertEquals("Login: ", true, result);
		instance.deleteItem(instance.auctionItemId);
	}
}
