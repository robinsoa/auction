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
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the bid
	 */
	public double getBid() {
		return bid;
	}

	/**
	 * @param bid the bid to set
	 */
	public void setBid(double bid) {
		this.bid = bid;
	}

	/**
	 * @return the bidderName
	 */
	public String getBidderName() {
		return bidderName;
	}

	/**
	 * @param bidderName the bidderName to set
	 */
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}

	/**
	 * @return the bidderEmail
	 */
	public String getBidderEmail() {
		return bidderEmail;
	}

	/**
	 * @param bidderEmail the bidderEmail to set
	 */
	public void setBidderEmail(String bidderEmail) {
		this.bidderEmail = bidderEmail;
	}
	
	
	//

}
