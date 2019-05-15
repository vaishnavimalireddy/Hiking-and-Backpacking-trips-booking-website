package com.neu.edu.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.DAO.TripDAO;
import com.neu.edu.POJO.CustomerDetails;
import com.neu.edu.POJO.Trip;

@Controller
public class TripController {
   
	
	
	@Autowired
	@Qualifier("tripdao")
	TripDAO tripdao;
	
	 @RequestMapping(value = "tripmain.htm", method = RequestMethod.GET)
	    public String showTripForm() {

	        return "tripmain";
	    }
	  
	 @RequestMapping(value = "triplist.htm", method = RequestMethod.GET)
	    public String showTripList(TripDAO tripdao ,HttpServletRequest request) throws Exception {
		    HttpSession session = request.getSession();
		     List<Trip> triplist = tripdao.getTripList();
			session.setAttribute("triplist",triplist);
	        return "triplist";
	    }
	  
	 @RequestMapping(value = "searchtrip.htm", method = RequestMethod.GET)
	    public String showSearchForm() {

	        return "searchtrip";
	    }
	  
	
	 @RequestMapping(value = "booktrip.htm", method = RequestMethod.GET)
	 public String BookTrip(HttpServletRequest request, ModelMap map) throws Exception {
		    
		 try{
		   HttpSession session = request.getSession();
		   CustomerDetails user = (CustomerDetails) session.getAttribute("loggedInUser");
		   
		   if(user==null)
		   {
			   
			   return "login";
		   }
		   else
		   {
			    String id = request.getParameter("tripid");
			    System.out.println(id);
			    int tripid=Integer.parseInt(id);
			    System.out.println(tripid);
			    //int res =  tripDAO.reducemaxpersons(tripid);
			    Trip trip = tripdao.gettripdetails(tripid);
			    
			    session.setAttribute("trip", trip);
			    session.setAttribute("tripid", tripid);
			    return "bookingdetails";
		   }
		 }
		 catch(Exception e)
		 {
			 System.out.println(e.getMessage());
		 }
		return "error";
	    }
	 
	 
	 @RequestMapping(value = "search.htm", method = RequestMethod.POST)
	 public String SearchTrip(HttpServletRequest request, ModelMap map) throws Exception {
		
		 HttpSession session = request.getSession();
		   CustomerDetails user = (CustomerDetails) session.getAttribute("loggedInUser");
		   
		   if(user==null)
		   {
			   
			   return "login";
		   }
		   else
		   {
				 try
				 {
				 String Destination = request.getParameter("place");
				
				 String triptype = request.getParameter("type");
				 String date = request.getParameter("date");
				 String difficultylevel = request.getParameter("difficultylevel");
			
				 String sprice1 = request.getParameter("price1"); 
				 String sprice2 = request.getParameter("price2");
				 List<Double> list =null;
				 Double price1 = 0.0;
				 Double price2 = 0.0;
				 java.sql.Date sqldate = null;
				 
				 if(sprice1=="" && sprice2!="")
				 {
					 
					 System.out.println(price1);
						 
					 System.out.println(price2);
					
					 price2 = Double.parseDouble(sprice2);
					 if(price1<price2)
					 {
					 list = new ArrayList<Double>();
					 list.add(price1);
					 list.add(price2);
					 }
					 else
					 {
						 return "error";
					 }
				 }
				 if(sprice2=="" && sprice1!="")
				 {
					  price2 = tripdao.getmaxprice();
					 System.out.println(price1);
						 
					 System.out.println(price2);
					
					 price1 = Double.parseDouble(sprice1);
					 if(price1<price2)
					 {
					 list = new ArrayList<Double>();
					 list.add(price1);
					 list.add(price2);
					 }
					 else
					 {
						 return "error";
					 }
				 }
				 else if(sprice1!= "" && sprice2!="")
				 {
					 price1 = Double.parseDouble(sprice1);
					 price2 = Double.parseDouble(sprice2);
					 if(price1<price2)
					 {
					 list = new ArrayList<Double>();
					 list.add(price1);
					 list.add(price2);
					 }
					 else
					 {
						 return "error";
					 }
				 }
					
				 if(date!="")
				 {
					 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				    	java.util.Date tripdate = sdf1.parse(date);
				     
				    	 sqldate = new java.sql.Date(tripdate.getTime()); 
				 }
				 
				 Map<String,Object> mapA = new HashMap<String,Object>();
				 if(Destination!="")
				 {
				 mapA.put("Destination",Destination);
				 }
				 if(triptype!=null)
				 {
				 mapA.put("triptype", triptype);
				 }
				 if(difficultylevel!=null)
				 {
				 mapA.put("difficultylevel",difficultylevel);
				 }
				 if(sqldate!=null)
				 mapA.put("date", sqldate);
				 if(list!=null)
				 {
				 mapA.put("price",list);
				 }
				 System.out.println(mapA.size());
				 List<Trip> triplist = tripdao.search(mapA);
				 session.setAttribute("triplist",triplist);
				
				 return "triplist";
				 }
				 catch(Exception e)
				 {
					 System.out.println(e.getMessage());
				 }
				 return "error";
		   }
		 
	 }
	 
	 
	 
	 
	
	
}
