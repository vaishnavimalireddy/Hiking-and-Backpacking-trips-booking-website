package com.neu.edu.controller;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import com.neu.edu.DAO.BookDAO;
import com.neu.edu.DAO.CustomerDetailsDAO;
import com.neu.edu.POJO.CustomerDetails;
import com.neu.edu.POJO.CustomerItems;
import com.neu.edu.POJO.Payment;
import com.neu.edu.POJO.Trip;
import com.neu.edu.view.MyPDFView;


@Controller
public class BookController{
    
	
	@Autowired
	@Qualifier("paymentValidator")
	PaymentValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
	} 
	 
	
	
	@Autowired
	@Qualifier("bookdao")
	BookDAO bookdao;
	
	@Autowired
	@Qualifier("custdao")
	CustomerDetailsDAO custdao;

	
	
	
	 @RequestMapping(value = "payment.htm", method = RequestMethod.GET)
	 public String PaymentForm(HttpServletRequest request, ModelMap map) throws Exception {
		 
		 return "payment";
		 
	 }
	
	
	 @RequestMapping(value = "bookdetails.htm", method = RequestMethod.POST)
	 public String BookingDetails(HttpServletRequest request, ModelMap map,HttpServletResponse response) throws Exception {
		
		 
		 try{
			   HttpSession session = request.getSession();
			   CustomerDetails user = (CustomerDetails) session.getAttribute("loggedInUser");
			   
			   if(user==null)
			   {
				   
				   return "login";
			   }
			   else
			   {    
				   String action = request.getParameter("action");
					if(action.equalsIgnoreCase("checkmaxpersons"))
					{
						PrintWriter out = response.getWriter();

			            String snumberofpersons = request.getParameter("numberofpersons");
			
					    int numberofpersons=Integer.parseInt(snumberofpersons);
					    Trip trip = (Trip) session.getAttribute("trip");
						  int maxpersons = bookdao.getmaxpersons(trip.getTripid());
						  System.out.println(maxpersons);
						if(numberofpersons > maxpersons){
				              
				              JSONObject obj = new JSONObject();
				               obj.put("message","Limit seats are available");
				               out.println(obj);
				               
				           }
				          else{
				              out.println("they are available");
				          }
						return null;
					}
					else if(action.equalsIgnoreCase("book"))
					{
				   
				   
				   
				            String snumberofpersons = request.getParameter("numberofpersons");
				
						    int numberofpersons=Integer.parseInt(snumberofpersons);
						    session.setAttribute("numberofpersons", numberofpersons);
						    System.out.println(numberofpersons);
						    Trip trip = (Trip) session.getAttribute("trip");
						    
						    Double price = trip.getPrice();
						    Double totalprice = numberofpersons * price;
						    int maxpersons = bookdao.getmaxpersons(trip.getTripid());
						   
						  
						    
						    CustomerItems custitems = new CustomerItems();
						    custitems.setTotalPrice(totalprice);
						    custitems.setTrip(trip);
						    custitems.setCustdetails(user);
						    custitems.setNumberofpersons(numberofpersons);
						    
		
						    
						    CustomerItems c = bookdao.save(custitems);
						    session.setAttribute("customeritems",custitems);
						    
						    
						    
					        return "payment";
						   
					}
			   }
			 }
			 catch(Exception e)
			 {
			 System.out.println(e.getMessage());
			 }
			
		 
		 
		 return "error";
  
		 
	 }
	 
	 
	 @RequestMapping(value = "payment.htm", method = RequestMethod.POST)
	 public String PaymentDetails(HttpServletRequest request, ModelMap map) throws Exception {
		 
		 try{
			   HttpSession session = request.getSession();
			   CustomerDetails user = (CustomerDetails) session.getAttribute("loggedInUser");
			   
			   if(user==null)
			   {
				   
				   return "login";
			   }
			   else
			   {
//				   validator.validate(payment, result);
				   String card = request.getParameter("card");
				   String bankname = request.getParameter("bankname");
				   String nameoncard = request.getParameter("name");
				   String expm = request.getParameter("expmonth");
				   String expy = request.getParameter("expyear");
				   
				   Long cardn = Long.parseLong(card);
				   Payment p = new Payment();
				   p.setCardnumber(cardn);
				   p.setBankName(bankname);
				   p.setCustomerName(nameoncard);
				   p.setExpirationMonth(expm);
				   p.setExpirationYear(expy);
				   p.setCustomer(user);
				
				  bookdao.savepayment(p);
				  
				  int max = (Integer) session.getAttribute("numberofpersons");
				  CustomerItems custitems  = (CustomerItems) session.getAttribute("customeritems");
				  int tripid = custitems.getTrip().getTripid();
				   bookdao.reducemaxpersons(tripid,max);
				   session.setAttribute("payment",p);
			       return "confirmpage";
			   
			   }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
		
		 
		 
		 
		 return null;
		 
	 }
	 

	    @RequestMapping(value="download.htm", method=RequestMethod.GET)
		public View downloadTripDetails(HttpServletRequest request) 
		{
	    	try
	    	{
	    	View view = new MyPDFView();
	    	return view;
	    	
	    	
	    	}
	    	catch(Exception e)
	    	{
	    		System.out.println(e.getMessage());
	    	
	    		
	    	}
			return null;
	    	
	    	
	    	
			
	    	
		}
	    
	    
	    @RequestMapping(value="sendemail.htm", method=RequestMethod.GET)
		public String sendTripDetails(HttpServletRequest request) 
		{    HttpSession session = request.getSession();
		   CustomerDetails user = (CustomerDetails) session.getAttribute("loggedInUser");
		   
		   if(user==null)
		   {
			   
			   return "login";
		   }
		   else
		   {
	    	
			    	CustomerItems cust = (CustomerItems) session.getAttribute("customeritems");
			    	try {
			            Email email = new SimpleEmail();
			            email.setHostName("smtp.googlemail.com");
			            email.setSmtpPort(465);
			            email.setAuthenticator(new DefaultAuthenticator("vaishnavimalireddy@gmail.com", "newfate@123"));
			            email.setSSLOnConnect(true);
			            email.setFrom("no-reply@msis.neu.edu"); // This user email does not
			                                                    // exist
			            email.setSubject("Trip Confirmation");
			            
			            email.setMsg("Thank you for booking the trip! Here are the trip details"+"\n\n\n\n" +"Trip Name :"+cust.getTrip().getTripname()+"\n\n"
			            		+"Trip Destination :"+cust.getTrip().getDestination()+"\n\n"+"Trip Duration :"+cust.getTrip().getDuration()+"\n\n"
			            		+"Trip Date  :"+cust.getTrip().getDate()+"\n\n"+"Number of Persons : "+cust.getNumberofpersons()+"\n\n"
			            		+"Name  :"+cust.getCustdetails().getName()+"\n\n"+"Email :"+cust.getCustdetails().getEmail()+"\n\n"+"Phone Number :"+cust.getCustdetails().getPhonenumber()
			            		+"\n\n"+" Total Price:"+cust.getTotalPrice()
			             ); // Retrieve email from the DAO and send this
			            email.addTo(cust.getCustdetails().getEmail());
			            email.send();
			            
			        } catch (EmailException e) {
			            System.out.println("Email cannot be sent");
			            return "error";
			        }
			     
			    	
			    	
			    	return "emailsuccess";
				   }
	    	
	    	
		}
	    
	   
	
	
}
