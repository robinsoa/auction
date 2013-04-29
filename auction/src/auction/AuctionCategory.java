package auction;

import java.util.ArrayList;

public class AuctionCategory {
	
	public String categoryName = "";
	public ArrayList<AuctionItem> auctionItems = new ArrayList<AuctionItem>();
	
	public AuctionCategory(String catName){
		categoryName = catName;
	}
	
	public void addItem(AuctionItem item) {
		auctionItems.add(item);		
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public ArrayList<AuctionItem> getItemList(){
		return auctionItems;
	}

}
