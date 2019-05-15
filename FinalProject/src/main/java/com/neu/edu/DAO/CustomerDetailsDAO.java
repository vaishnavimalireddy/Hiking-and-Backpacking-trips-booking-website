package com.neu.edu.DAO;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.neu.edu.POJO.CustomerDetails;
import com.neu.edu.POJO.CustomerItems;

public class CustomerDetailsDAO extends DAO {
	public CustomerDetailsDAO() {}
	Session session;
    Transaction tx;
	public CustomerDetails register(CustomerDetails u) throws Exception {
        try {
            begin();
            
            getSession().save(u);
            commit();
            return u;

        } catch (HibernateException e) {
            rollback();
            throw new Exception("Exception while creating customer: " + e.getMessage());
        }
    }
	
	public CustomerItems getitems(int custid) throws Exception {
		
		try {
			
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.createQuery("from CustomerItems where custdetails=:custdetails");
        query.setInteger("custdetails", custid);
    
        CustomerItems ci = (CustomerItems) query.list();
        
        tx.commit();
        session.close();
        
		return ci;
	     }
	
		catch (HibernateException e) {
	        rollback();
	        throw new Exception("Exception occured: " + e.getMessage());
	    }
		
		
	}
	
	

}
