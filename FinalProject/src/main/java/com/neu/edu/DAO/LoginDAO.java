package com.neu.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import org.hibernate.Transaction;


import org.hibernate.query.Query;


import com.neu.edu.POJO.CustomerDetails;

public class LoginDAO extends DAO {
       
	public LoginDAO() {}
	Session session;
    Transaction tx;
   
    
	public CustomerDetails login(String username,String password) throws Exception {
		try
		{
				session = super.sf.openSession();
		        tx = session.beginTransaction();
		        Query query = session.createQuery("from CustomerDetails where username=:name and password=:pass");
		        query.setString("name", username);
		        query.setString("pass", password);
		        
		        CustomerDetails verifed = (CustomerDetails) query.uniqueResult();
		        
		        tx.commit();
		        session.close();
				return verifed; 
	     }
				catch (HibernateException e) {
	 		        rollback();
	 		        throw new Exception("Exception occured: " + e.getMessage());
	 		    }
		
	}
	
	public CustomerDetails verify(String username,String email) throws Exception
	{
		try
		{
				session = super.sf.openSession();
		        tx = session.beginTransaction();
		        Query query = session.createQuery("from CustomerDetails where username=:username and email=:email");
		        query.setString("username", username);
		        query.setString("email", email);
		        
		        CustomerDetails verifed = (CustomerDetails) query.uniqueResult();
		        
		        tx.commit();
		        session.close();
		        
		        return verifed;
		 }
		catch (HibernateException e) {
		        rollback();
		        throw new Exception("Exception occured: " + e.getMessage());
		    }
		
	}
	
	public boolean checkusername(String username)
	{
		try{
			
			
			begin();
			Query query = getSession().createQuery("From CustomerDetails where username=:username");
			query.setString("username", username);
			List<CustomerDetails> list = query.list();
			commit();
			
			if(list.size()==0)
			{
				return false;
			}

		}
		catch(Exception e){
			rollback();
			System.out.println(e.getMessage());
		}
		finally{
			close();
		}
		return true;
	}
	
	
//	 public int updatepassword(String pass)
//	 {
//		 session = super.sf.openSession();
//	        tx = session.beginTransaction();
//	        Query query = session.createQuery("Update CustomerDetails set password=:password");
//	        query.setString("password", pass);
//	        int res  = query.executeUpdate();
//	        
//	        return res;
//	        
//	        
//	 }
	
}
