package com.neu.edu.DAO;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neu.edu.POJO.Admin;
import com.neu.edu.POJO.Trip;

public class AdminDAO extends DAO {
	
	public AdminDAO() {}
	
	Session session;
    Transaction tx;
   

	public Admin insert(Admin a) throws Exception {
        try {
            begin();
            
            getSession().save(a);
            commit();
            return a;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating admin: " + e.getMessage());
        }
    } 
	public Admin verify(String username,String pass) throws Exception {
		
		try {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from Admin where username=:username and password=:password");
        query.setString("username", username);
        query.setString("password", pass);
        query.setMaxResults(1);
        Admin ad = (Admin) query.uniqueResult();
        
        tx.commit();
        session.close();
        
		return ad;
	     }
	
		catch (HibernateException e) {
	        rollback();
	        throw new Exception("Exception occured: " + e.getMessage());
	    }
		
		
	}
	
	
    public Integer addtrip(Trip trip) throws Exception{
    	try {
			session = super.sf.openSession();
	        tx = session.beginTransaction();
	        Integer id = (Integer)session.save(trip);
	        tx.commit();
	        session.close();
	        return id;
            }
	
 			catch (HibernateException e) {
 		        rollback();
 		        throw new Exception("Exception occured: " + e.getMessage());
 		    }
	}
    
    
    public Integer deletetrip(int tripid) throws Exception
    {
    	try {
    	session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.getNamedQuery("tripdetailsdelete");
        query.setInteger("tripid", tripid);
    	int res = query.executeUpdate();
    	return res;
    	
		    }
			
			catch (HibernateException e) {
		        rollback();
		        throw new Exception("Exception occured: " + e.getMessage());
		    }
    	
    }
    
    public List<Trip> triplist() throws Exception {
    	try {
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.getNamedQuery("alltrips");
        List<Trip> ltrip = query.list();
        tx.commit();
        session.close();
        return ltrip;
    	}
    	
    	catch (HibernateException e) {
            rollback();
            throw new Exception("Exception occured: " + e.getMessage());
        }
    	
	}
    

}
