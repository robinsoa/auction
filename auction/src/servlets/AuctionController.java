package servlets;

import helper.AuctionHelper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuctionController
 */
@WebServlet("/AuctionController")
public class AuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuctionController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext ctx = this.getServletContext();
        RequestDispatcher dispatcher = ctx.getRequestDispatcher("/auctionitem.jsp");
        
        String bidderName = request.getParameter("bidderName");
        String bidderEmail = request.getParameter("bidderEmail");
        //Double bid = Double.parseDouble(request.getParameter("bid"));
        int id = Integer.parseInt(request.getParameter("id"));
        
        AuctionHelper aHelper = new AuctionHelper();
        
        request.setAttribute("id", id);
        request.setAttribute("control", "");
        request.setAttribute("bidderName", bidderName);
        request.setAttribute("bidderEmail", bidderEmail);
        aHelper.message =" ";
        System.out.println("Get");
        dispatcher.forward(request,response);
        return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session; 
		session = request.getSession();
		
		System.out.println("User name from session: " + session.getAttribute("userName"));
                      
        AuctionHelper aHelper = new AuctionHelper();             
        
        System.out.println("Control: " + request.getParameter("control"));
        
        
        switch(request.getParameter("control")) {
        case "updateBid": {        	
            int id = Integer.parseInt(request.getParameter("id"));
        	ServletContext ctx = this.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/auctionitem.jsp");
            request.setAttribute("id", id);
            aHelper.message =" ";
        	request.setAttribute("message", aHelper.getErrMsg());
            System.out.println("3.  updateBid");
            System.out.println("id: " + Integer.parseInt(request.getParameter("id")));
            System.out.println("bidderName: " + request.getParameter("bidderName"));
            System.out.println("bidderEmail: " + request.getParameter("bidderEmail"));
            //System.out.println("bid: " + Double.parseDouble(request.getParameter("bid")));
            
            if (request.getParameter("bidderName").isEmpty()){
            	aHelper.message = "Please enter your name.";
            	request.setAttribute("message", aHelper.getErrMsg());
            	dispatcher.forward(request,response);
            }
            
            else if (request.getParameter("bidderEmail").isEmpty()){
            	aHelper.message = "Please enter your email address.";
            	request.setAttribute("message", aHelper.getErrMsg());
            	dispatcher.forward(request,response);
            }
            
            else if (request.getParameter("bid").isEmpty()){
            	aHelper.message = "Please enter a bid.";
            	request.setAttribute("message", aHelper.getErrMsg());
            	dispatcher.forward(request,response);            	
            }          
            
            else {
            	if (Double.parseDouble(request.getParameter("bid"))<aHelper.getItem(id).getBid()){
            		aHelper.message = "Your bid must be greater than " + aHelper.getItem(id).getBid();
                	request.setAttribute("message", aHelper.getErrMsg());
                	dispatcher.forward(request,response);
            	}
            	
            	else{
            		aHelper.updateBid(Integer.parseInt(request.getParameter("id")), request.getParameter("bidderName"), request.getParameter("bidderEmail"), Double.parseDouble(request.getParameter("bid")));
                	aHelper.message =" ";
                	request.setAttribute("message", aHelper.getErrMsg());
                	dispatcher.forward(request,response);
            	}
            	
            }
            	
            
             	
            
            return;
                    	
        }  // end of case "updateBid"
        
        case "addItem": {
        	System.out.println("2.  addItem");
        	if ( (session.getAttribute("userName") != null || !session.getAttribute("userName").equals("")) ){
        		String title = request.getParameter("title");
                String description = request.getParameter("description");
                String category = request.getParameter("category");
                request.setAttribute("title", title);
                request.setAttribute("description", description);
                request.setAttribute("category", category);
                ServletContext ctx = this.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/admin.jsp");
                aHelper.addItem(title, category, description, 0.0);
                
                dispatcher.forward(request,response);
                return;
        	}
        	
            
        }  // end of case "addItem"
        
        case "login": {
        	System.out.println("Username: " + request.getParameter("userName"));
        	System.out.println("Password: " + request.getParameter("password"));
        	
        	String user = request.getParameter("userName");
        	String password = request.getParameter("password");
        	
        	if (user == null) {
        		ServletContext ctx = this.getServletContext();
                RequestDispatcher dispatcher = ctx.getRequestDispatcher("/auctionitem.jsp");
                System.out.println("1c.  Not Authenticated");
                request.setAttribute("message", aHelper.getErrMsg());
                dispatcher.forward(request,response);
                return;
        	}
        	
        	else {
        		boolean result = aHelper.isAdminLoginCredentials(user, password);
            	
            	System.out.println("Result: " + result);
            	
            	if (result) {        	
                	String userName = request.getParameter("userName");
                	session.setAttribute("userName", userName);
                    ServletContext ctx = this.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/admin.jsp");
                    System.out.println("1a.  Authenticated");
                    aHelper.message ="";
                    dispatcher.forward(request,response);
                    return;
                }
            	
            	else if (!result) {        	
                	ServletContext ctx = this.getServletContext();
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher("/index.jsp");
                    System.out.println("1b.  Not Authenticated");
                    aHelper.message = "Login Failed.  Please try again.";
                    request.setAttribute("message", aHelper.getErrMsg());
                    dispatcher.forward(request,response);
                    return;
                }
        		
        	}  // end of else       	
        	
        } // end of case "login"
        
        default:  {
        	int id = Integer.parseInt(request.getParameter("id"));
        	ServletContext ctx = this.getServletContext();
            RequestDispatcher dispatcher = ctx.getRequestDispatcher("/auctionitem.jsp");
            System.out.println("Updated Bid");
            request.setAttribute("id", id);
            dispatcher.forward(request,response);
            return;
        }  // end of default
        	
        } // end of switch

	} // end of doPost

} // end of AuctionController
