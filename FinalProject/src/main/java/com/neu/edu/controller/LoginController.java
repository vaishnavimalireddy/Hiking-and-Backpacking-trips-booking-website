package com.neu.edu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.json.simple.JSONObject;
import com.neu.edu.DAO.AdminDAO;
//import com.captcha.botdetect.web.servlet.Captcha;
import com.neu.edu.DAO.CustomerDetailsDAO;
import com.neu.edu.DAO.LoginDAO;
import com.neu.edu.DAO.TripDAO;
import com.neu.edu.POJO.Admin;
import com.neu.edu.POJO.CustomerDetails;



@Controller
public class LoginController  {
			   
			@Autowired
			@Qualifier("logindao")
			LoginDAO logindao;
	
			@Autowired
			@Qualifier("admindao")
			AdminDAO admindao;
			
			@Autowired
			@Qualifier("tripdao")
			TripDAO tripdao;
			
			@Autowired
			@Qualifier("custdao")
			CustomerDetailsDAO custdao;
	
			
	
	    Random rand = new Random(); 
	    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
	    public String showLoginForm() {

	        return "login";
	    }
	    
	    @RequestMapping(value = "register.htm", method = RequestMethod.GET)
	    public String showRegisterForm() {

	        return "register";
	    }
	    
	    
	    @RequestMapping(value = "forgotpassword.htm", method = RequestMethod.GET)
	    public String showForgotPasswordForm() {

	        return "forgotpassword";
	    }
	    
	    private String hashPassword(String plainTextPassword){
			return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
		}
	    
	    
//	    @Autowired
//	    LoginDAO logindao;
//	    
	    
	    
	    public boolean sendEmail(String useremail, int rand) {
	        try {
	            Email email = new SimpleEmail();
	            email.setHostName("smtp.googlemail.com");
	            email.setSmtpPort(465);
	            email.setAuthenticator(new DefaultAuthenticator("vaishnavimalireddy@gmail.com", "newfate@123"));
	            email.setSSLOnConnect(true);
	            email.setFrom("no-reply@msis.neu.edu"); // This user email does not
	                                                    // exist
	            email.setSubject("Web tools lab ");
	            
	            email.setMsg("Your verification code is "+rand); // Retrieve email from the DAO and send this
	            email.addTo(useremail);
	            email.send();
	            
	        } catch (EmailException e) {
	            System.out.println("Email cannot be sent");
	            return false;
	        }
	        return true;
	    }
	    public boolean sendEmail1(CustomerDetails cust) {
	        try {
	            Email email = new SimpleEmail();
	            email.setHostName("smtp.googlemail.com");
	            email.setSmtpPort(465);
	            email.setAuthenticator(new DefaultAuthenticator("vaishnavimalireddy@gmail.com", "newfate@123"));
	            email.setSSLOnConnect(true);
	            email.setFrom("no-reply@msis.neu.edu"); // This user email does not
	                                                    // exist
	            email.setSubject("Web tools lab ");
	            
	            email.setMsg("Your password is "+cust.getPassword()); // Retrieve email from the DAO and send this
	            email.addTo(cust.getEmail());
	            email.send();
	            
	        } catch (EmailException e) {
	            System.out.println("Email cannot be sent");
	            return false;
	        }
	        return true;
	    }
	   
	    
	    @RequestMapping(value = "register.htm", method = RequestMethod.POST)
	    public String handleRegistration(HttpServletRequest request, ModelMap map,HttpServletResponse response) throws IOException {
	        //Captcha captcha = Captcha.load(request, "CaptchaObject");
	        //String captchaCode = request.getParameter("captchaCode");
	         
	    	String action = request.getParameter("action");
			if(action.equalsIgnoreCase("checkusername"))
			{
				PrintWriter out = response.getWriter();
				String username = request.getParameter("username");
				
				if(logindao.checkusername(username)){
		              
		              JSONObject obj = new JSONObject();
		               obj.put("message","Username already exists");
		               out.println(obj);
		               
		           }
		          else{
		              out.println("Username is available");
		          }
				return null;
			}
			else if(action.equalsIgnoreCase("register"))
			{
				    	 HttpSession session = request.getSession();
				        
				         String name = request.getParameter("name");
						 String email = request.getParameter("email");
						 String username = request.getParameter("username");
						 String password = request.getParameter("password");
				         //String hpass = request.getParameter(password);
						 String address = request.getParameter("address");
						 String phone = request.getParameter("phone");
						 CustomerDetails cust = new CustomerDetails();
						 cust.setName(name);
						 cust.setEmail(email);
						 cust.setUsername(username);
						 cust.setPassword(password);
						 cust.setAddress(address);
						 cust.setPhonenumber(phone);
						 cust.setRole("Customer");
						 
						 
				            
				            session.setAttribute("customer", cust);
				            
				            
				           
				            int randumnumber = rand.nextInt(5000000);
				            
					    		boolean check  = sendEmail(cust.getEmail(),randumnumber);
					    		if(check == true)
					    		{   
					    			
					    			session.setAttribute("randumnumber1", randumnumber);
					    			 return "registerverify";
					    		}
					    		else
					    		{
					    			return "emailerror";
					    			
					    		}
			}
			
			return "error";
				       
	    }
	    
	    @RequestMapping(value = "registerverify.htm", method = RequestMethod.POST)
	    public String verifyuser(HttpServletRequest request, ModelMap map) {
	    	
	    	HttpSession session = request.getSession();
	    	
	    	CustomerDetails cust = (CustomerDetails)session.getAttribute("customer");
	    	
	    	int verificationcode = Integer.parseInt(request.getParameter("verificationcode"));
	    	//int c = Integer.parseInt("code");
	    	int randomnumber = (Integer) session.getAttribute("randumnumber1");
	    	
	    	System.out.println(randomnumber);
	    	if(verificationcode==randomnumber)
	    	
	    	{
	    		  try {
						custdao.register(cust);
						session.setAttribute("loggedInUser", cust);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        
	    		return "registersuccess";
	    		
	    	}
	    	
	    	else
	    	{
	    		return "registererror";
	    	}
	    	
	    	
	    }
	    @RequestMapping(value = "forgotpassword.htm", method = RequestMethod.POST)
	    public String verifyForgotPasswordForm(HttpServletRequest request, ModelMap map) throws Exception {
	    	HttpSession session = request.getSession();
	    	
	    	String email = request.getParameter("useremail");
	    	String username = request.getParameter("username");
	    	
	    	CustomerDetails res = logindao.verify(username,email);
	    	if(res==null)
	    	{
	    		return "forgotpassworderror";
	    	}
	    	else
	    	{
	    		int randumnumber = rand.nextInt(5000000);
		        
		    	boolean check  = sendEmail(email,randumnumber);
		    	
	    		if(check == true)
	    		{   
	    			
	    			session.setAttribute("randumnumber2", randumnumber);
	    			session.setAttribute("res", res);
	    			
	    			 return "forgotpasswordverify";
	    		}
	    		else
	    		{
	    			return "emailerror";
	    			
	    		}
	    		
	    	}
	    	
	    }
	    
	    @RequestMapping(value = "forgotpasswordverify.htm", method = RequestMethod.POST)
	    public String forgotpasswordverifyuser(HttpServletRequest request,ModelMap map) {
	    	
	    	HttpSession session = request.getSession();
	    	

	    	int verificationcode = Integer.parseInt(request.getParameter("code"));
	    	//int c = Integer.parseInt("code");
	    	CustomerDetails cust = (CustomerDetails) session.getAttribute("res");
	    	
	    	int randomnumber = (Integer) session.getAttribute("randumnumber2");
	    	
	    	System.out.println(randomnumber);
	    	
	 
	    	if(verificationcode==(randomnumber))
	    	
	    	{
	    		boolean check= sendEmail1(cust);
	    		if(check == true)
	    		{   
	    			return "forgotpasswordsuccess";
	    		}
	    		else
	    		{
	    			return "emailerror";
	    		}
	    		
	    		
	    	}
	    	
	    	else
	    	{
	    		return "forgotpassworderror";
	    	}
	    	
	    	
	    }
	    
	    
	    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
	    public String LoginForm(HttpServletRequest request,ModelMap map) throws Exception {
	    	HttpSession session = request.getSession();
	    	
	    	  String username = request.getParameter("username");
	          String password = request.getParameter("password");
//	          Admin admin = new Admin();
//		    	admin.setUsername("admin");
//		    	admin.setPassword("admin");
		    	//admindao.insert(admin);
	          //String encryptpassword = hashPassword(password);
	          //System.out.println(encryptpassword);
	          CustomerDetails verifiedUser = logindao.login(username,password);
	         Admin ad = admindao.verify(username,password);
	  		if(verifiedUser != null) {
	  			
	  			session.setAttribute("loggedInUser", verifiedUser);
	  			
	  				
	  				return "redirect:tripmain.htm";
	  			
	  		}
	  		else if(ad!=null) {
	  			session.setAttribute("adminuser", ad);
	  			return "adminpage";
	  		}
	  		else
	  		{
	  			return "loginerror";
	  		}
	    	 
	    	
	    }
	    
	    
	    
	    
}
