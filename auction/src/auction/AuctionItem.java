package auction;

public class AuctionItem {
	int id;
	String title;
	String description;
	double bid;
	String bidderName;
	String bidderEmail;
	
	// Constructors
	
	public AuctionItem(){
		this.id = -1;
		this.title = "";
		this.description = "";
		this.bid = 0;
		this.bidderName = "";
		this.bidderEmail = "";	
		
	}
	
	public AuctionItem(int id, String title, String description, double bid, String bidderName, String bidderEmail){
		this.id = id;
		this.title = title;
		this.description = description;
		this.bid = bid;
		this.bidderName = bidderName;
		this.bidderEmail = bidderEmail;		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the bid
	 */
	public double getBid() {
		return bid;
	}

	/**
	 * @return the bidderName
	 */
	public String getBidderName() {
		return bidderName;
	}

	/**
	 * @return the bidderEmail
	 */
	public String getBidderEmail() {
		return bidderEmail;
	}

	
	
	//

}
