package com.neu.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.DAO.AdminDAO;
import com.neu.edu.POJO.Admin;
import com.neu.edu.POJO.Trip;

@Controller
public class AdminController {

	@Autowired
	@Qualifier("admindao")
	AdminDAO admindao;
	
	
	
	
	
	
	    @RequestMapping(value = "addtrip.htm", method = RequestMethod.GET)
	    public String showRegisterForm() {

	        return "addtrip";
	    }
	    
	    
	    @RequestMapping(value = "deletetrip.htm", method = RequestMethod.GET)
	    public String showForgotPasswordForm() {

	        return "deletetrip";
	    }
	    
	    @RequestMapping(value = "logout.htm", method = RequestMethod.GET)
	    public String  LogoutForm(HttpServletRequest request) {
	    	HttpSession session = request.getSession();
	    	session.invalidate();
	        return "login";
	    }
	
	 
    @RequestMapping(value = "addtrip.htm", method = RequestMethod.POST)
    public String addTrip(HttpServletRequest request, ModelMap map,HttpServletResponse response) throws IOException {
    	 
		   HttpSession session = request.getSession();
		   Admin user = (Admin) session.getAttribute("adminuser");
		   
		   if(user==null)
		   {
			   
			   return "login";
		   }
		   else
		   {
			   try
			   {
				   
				   
			    	String action = request.getParameter("action");
					if(action.equalsIgnoreCase("checkdate"))
						
					{   
						
						PrintWriter out = response.getWriter();
						
						String tripdate = request.getParameter("tripdate");
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date = sdf1.parse(tripdate);
						
				    	System.out.println(date);
				    	java.sql.Date date1 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
				    	 if (date.before(date1)) {
				    		 
				    		 JSONObject obj = new JSONObject();
				               obj.put("message","Date should be after todays date");
				               out.println(obj);
				               
				             return null;
				         }
				    	 
				    	 else{
				              out.println("Date is valid");
				          }
				    	 
				    	 return null;
					
					}
					else if(action.equalsIgnoreCase("addtrip"))
					{
						
					
						    	String tripname= request.getParameter("tripname");
						    	String destination = request.getParameter("destination");
						    	String dprice = request.getParameter("price");
						    	Double price = Double.parseDouble(dprice);
						    	String dmaxpersons = request.getParameter("maxpersons");
						    	int maxpersons = Integer.parseInt(dmaxpersons);
						    	String description = request.getParameter("description");
						    	
						    	String tripdate = request.getParameter("tripdate");
						    	System.out.println(tripdate);
						    	String difficultylevel = request.getParameter("difficultylevel");
						    	String duration = request.getParameter("duration");
						    	String type = request.getParameter("type");
						    	
						    
						    	Trip trip = new Trip();
						    	trip.setTripname(tripname);
						    	trip.setDestination(destination);
						    	trip.setPrice(price);
						    	trip.setMaxpersons(maxpersons);
						    	trip.setDescription(description);
						    	
						    	
						    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						    	java.util.Date date = sdf1.parse(tripdate);
						     
						    	java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
						    	
						    	
						    	trip.setDate(sqlStartDate);
						    	//trip.setDate(tripdate);
						    	trip.setDifficultylevel(difficultylevel);
						    	trip.setDuration(duration);
						    	trip.setTriptype(type);
						    	
						    	int res = admindao.addtrip(trip);
						    	
						    	if(res!=0)
						    	{
						    		System.out.println("Inserted");
						    		return "adminpage";
						    	}
						    	else
						    	{
						    		return "error";
						    		
						    	}
					}
					return "error";
			   }
			   catch(Exception e)
			   {
				   System.out.println(e.getMessage());
				   return "error";
			   }
			    	
			    	
		   }
		
    }
    	
		@RequestMapping(value = "deletetrip.htm", method = RequestMethod.POST)
		public String deleteTrip(HttpServletRequest request ,ModelMap map) throws Exception {
			 HttpSession session = request.getSession();
			   Admin user = (Admin) session.getAttribute("adminuser");
			   
			   if(user==null)
			   {
				   
				   return "login";
			   }
			   else
			   {
			
				   	String id = request.getParameter("tripid");
                       int tripid = Integer.parseInt(id);
                       int res = admindao.deletetrip(tripid);
                       
                       if(res!=0)
   			    	{
   			    		return "adminpage";
   			    	}
   			    	else
   			    	{
   			    		return "error";
   			    		
   			    	}
			   }
		}
                       
         @RequestMapping(value = "admintriplist.htm", method = RequestMethod.GET)
         public String admintriplist(HttpServletRequest request, ModelMap map,AdminDAO admindao)throws Exception  {
        	 HttpSession session = request.getSession();
        	 Admin user = (Admin) session.getAttribute("adminuser");
			   
			   if(user==null)
			   {
				   
				   return "login";
			   }
			   else
			   {
		        	
		        	 
		        	 List<Trip> ltrip = admindao.triplist();
		        	 
		        	 session.setAttribute("triplist",ltrip);
		        	 
		        	 
		        	 return "admintriplist";
			   }
        	        
      	 
         }
        	 

    }

