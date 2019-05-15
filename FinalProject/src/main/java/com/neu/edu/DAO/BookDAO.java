package com.neu.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neu.edu.POJO.CustomerItems;
import com.neu.edu.POJO.Payment;

public class BookDAO extends DAO{
    
	public BookDAO() {}
	Session session;
    Transaction tx;
    
    public int reducemaxpersons(int tripid,int max) throws Exception
    {
    	try
    	{
    	session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("Update Trip set maxpersons = maxpersons- :max where tripid=:tripid");
        query.setInteger("tripid",tripid);
        query.setInteger("max",max);
        int res = query.executeUpdate();
        return res;
          }
        catch (HibernateException e) {
	        rollback();
	        throw new Exception("Exception occured: " + e.getMessage());
	    }
    }
    
    public Payment savepayment(Payment p) throws Exception
    {
    	 try {
             begin();
             
             getSession().save(p);
             commit();
             return p;

         } catch (HibernateException e) {
             rollback();
             throw new Exception("Exception while creating customer: " + e.getMessage());
         }
    
    }
    public CustomerItems save(CustomerItems a) throws Exception {
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
    
    public int increasemaxpersons(int tripid,int max) throws Exception
    {
    	try
    	{
    	session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("Update Trip set maxpersons = maxpersons + :max where tripid=:tripid");
        query.setInteger("tripid",tripid);
        query.setInteger("max",max);
        int res = query.executeUpdate();
        return res;
          }
        catch (HibernateException e) {
	        rollback();
	        throw new Exception("Exception occured: " + e.getMessage());
	    }
    }
    
    public int getmaxpersons(int tripid) throws Exception
    {
    	try
    	{
    	session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("select maxpersons from Trip where tripid = :tripid");
        query.setInteger("tripid",tripid);
        
        int res = (Integer) query.uniqueResult();
        return res;
          }
        catch (HibernateException e) {
	        rollback();
	        throw new Exception("Exception occured: " + e.getMessage());
	    }
    }
    
    
    
    
    
}
