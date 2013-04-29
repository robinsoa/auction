package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import auction.AuctionCategory;
import auction.AuctionItem;

public class AuctionHelper {
	static String JDBC_URL = "jdbc:mysql://localhost/mydb";
	static String adminUser = "admin";
	static String adminPassword = "password";
	public String message = " ";
	int auctionItemId;
	AuctionItem item = new AuctionItem();
	ResultSet rs1, rs2, rs3;
	
	PreparedStatement getCategories;
	PreparedStatement getAuctionItem;
	PreparedStatement getItemsByCategory;
	PreparedStatement getCategoryByName;
	PreparedStatement addItemStatement;
	PreparedStatement deleteItemStatement;
	PreparedStatement updateHighBid;
	
	public AuctionHelper() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(JDBC_URL, "root", "root1234");
			System.out.println("Connected to mydb database");
			getAuctionItem = conn.prepareStatement("select * from mydb.auctionitem where id = ?");
			getCategories = conn.prepareStatement("select distinct category from mydb.auctionitem");
			getItemsByCategory = conn.prepareStatement("select * from mydb.auctionitem where category = ?");
			getCategoryByName = conn.prepareStatement("select category from mydb.auctionitem");
			addItemStatement = conn.prepareStatement("insert into mydb.auctionitem (title, description, category, highbid) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			deleteItemStatement = conn.prepareStatement("delete from mydb.auctionitem where id = ?");
			updateHighBid = conn.prepareStatement("update mydb.auctionitem set highbid = ?, bidderName = ?, bidderEmail = ? where id = ?");
			
			
			System.out.println("Created prepared statements");
			}
		    catch(Exception e){
		    	System.out.println("Error in constructor: "+ 
		    			e.getClass().getName()+": "+ e.getMessage());
		    	e.printStackTrace();
		    }
		//this.clearDB();
		}//constructor
	
	public void addItem(String title, String category, String description, Double highbid){
		try {
			addItemStatement.setString(1, title);
			addItemStatement.setString(2, description);
			addItemStatement.setString(3, category);
			addItemStatement.setDouble(4, highbid);
			addItemStatement.executeUpdate();
						
			rs3 = addItemStatement.getGeneratedKeys();
			
			while (rs3.next()){
				auctionItemId = (int) rs3.getLong(1);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	} // end of addItem()
	
	public void deleteItem(int index) {
		try {
			deleteItemStatement.setInt(1, index);
			deleteItemStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public AuctionCategory getCategory(String categoryName){
		
		AuctionCategory cat = new AuctionCategory(categoryName);
		
		try {

			getItemsByCategory.setString(1,categoryName);
			rs2 = getItemsByCategory.executeQuery();

			while (rs2.next()){
				AuctionItem item = new AuctionItem(rs2.getInt("id"), rs2.getString("title"), rs2.getString("description"), rs2.getDouble("highbid"), rs2.getString("bidderName"), rs2.getString("bidderEmail"));
				cat.addItem(item);				

			} // end of add items			
		}// end of try 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end of catch
		
		return cat;
	} // end of getCategory()
	
	public AuctionCategory[] getCategoryList(){
		AuctionCategory[] categories = new AuctionCategory[10];
		
		int index = 0;
		
		try {
			rs1 = getCategories.executeQuery();
			
			while (rs1.next()){
				AuctionCategory cat = new AuctionCategory(rs1.getString("category"));
				categories[index] = cat;
				
				getItemsByCategory.setString(1,cat.categoryName);
				rs2 = getItemsByCategory.executeQuery();
				
				while (rs2.next()){
					AuctionItem item = new AuctionItem(rs2.getInt("id"), rs2.getString("title"), rs2.getString("description"), rs2.getDouble("highbid"), rs2.getString("bidderName"), rs2.getString("bidderEmail"));
					categories[index].addItem(item);				
					
				} // end of add items
				
				index++;
			} // end of add categories
		} // end of try 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end of catch
		
		return categories;
		
	} // end of getCategoryList()
	
	public String getErrMsg(){
		return this.message;
		
	} // end of getErrMsg()
	
	public AuctionItem getItem(int id) {
		System.out.println("ID: " + id);
						
		try {
			getAuctionItem.setInt(1, id);
			rs3 = getAuctionItem.executeQuery();
			
			while (rs3.next()){
				AuctionItem item2 = new AuctionItem(rs3.getInt("id"), rs3.getString("title"), rs3.getString("description"), rs3.getDouble("highbid"), rs3.getString("bidderName"), rs3.getString("bidderEmail"));
				item = item2;						
			} // end of while
							
		} // end of try
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // end of catch
		
		return item;		
	} // end of getItem()
	
	public boolean	isAdminLoginCredentials(java.lang.String userLoginName, java.lang.String userPassword){
		boolean result = false;
		if (userLoginName.matches(adminUser) && userPassword.matches(adminPassword))
			result = true;
		
		return result;
	}
	
	public boolean updateBid(int id, java.lang.String bidderName, java.lang.String bidderEmail, double bid){
		boolean result=false;
		
		if (bid > getItem(id).getBid()) {
			try {
				updateHighBid.setDouble(1, bid);
				updateHighBid.setString(2, bidderName);
				updateHighBid.setString(3, bidderEmail);
				updateHighBid.setInt(4, id);
				updateHighBid.executeUpdate();
				result = true;
			} 
			
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		else
			this.message="Unable to update bid.  Please enter a big larger than $" +  getItem(id).getBid();
			
		return result;
	}

}
