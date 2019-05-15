package com.neu.edu.DAO;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.neu.edu.POJO.Trip;

public class TripDAO extends DAO {
       
	public TripDAO() {}
	Session session;
    Transaction tx;
   
    
    public List<Trip> getTripList() throws Exception{
    	try
    	{
		session = super.sf.openSession();
        tx = session.beginTransaction();
//        Criteria crit = session.createCriteria(Trip.class);
//        crit.add(Restrictions.gt("maxpersons",0));
        Query query = session.createQuery("from Trip where maxpersons>0");
         List<Trip> modelList = query.list();
        //List<Trip> modelList = crit.list();
        return modelList;
    	}
        catch (HibernateException e) {
		        rollback();
		        throw new Exception("Exception occured: " + e.getMessage());
		    }
	}
    
   
    
    public Trip gettripdetails(int tripid) throws Exception
    {  
    	try
    	{
    	session = super.sf.openSession();
        tx = session.beginTransaction();
        Query query = session.getNamedQuery("tripdetails");
        query.setInteger("tripid",tripid);
        Trip trip = (Trip) query.uniqueResult();
        return trip;
    	}
    	catch (HibernateException e) {
	        rollback();
	        throw new Exception("Exception occured: " + e.getMessage());
	    }
    	
    }
    
   
    
    public List<Trip> search(Map<String, Object> mapA)
    {  
    	try
    	{
		    
		    	session = super.sf.openSession();
		        tx = session.beginTransaction();
		        Criteria crit = session.createCriteria(Trip.class);
				
		        crit.add(Restrictions.gt("maxpersons",0));
		    	
			        Set s = mapA.entrySet();
			        Iterator it = s.iterator();
			
			        while(it.hasNext()){
			        	Map.Entry<String,Object> entry = (Map.Entry<String,Object>)it.next();
			
			            String key             = entry.getKey();
			            System.out.println(key);
			            
			            if(key.equals("Destination"))
			            {
			               String value         = (String) entry.getValue();
			               System.out.println(value);
			               if(value!=" ")
			            	   crit.add(Restrictions.eq(key,value));
			   	            //t.setDestination(value);
		
			            }
			            if(key.equals("triptype"))
			            { 
			            	String value         = (String) entry.getValue();
			            	System.out.println(value);
				               if(value!=null) {
//				            	   t.setTriptype(value);
				   	            crit.add(Restrictions.eq(key,value));
			            			}
			            	
			            }
			            if(key.equals("difficultylevel"))
			            {
			            	String value         = (String) entry.getValue();
			            	System.out.println(value);
				               if(value!=null)
				            	   crit.add(Restrictions.eq(key,value));
				   	            
			            }
			            if(key.equals("date"))
			            {
			            	java.sql.Date value         = (java.sql.Date)entry.getValue();
			            	System.out.println(value);
				               if(value!=null)
				               {System.out.println(value);
				            	   crit.add(Restrictions.like(key,value+"%"));
				               }
				   	            
			            }
			            if(key.equals("price"))
			            {
			            	List<Double> value         = (List<Double>) entry.getValue();
			            	System.out.println(value);
			            	Double p1 = value.get(0);
			            	Double p2 = value.get(1);
			            	System.out.println(p1);
			            	System.out.println(p2);
				               if(value!=null)
				               { 
				            	   
				               Criterion a = Restrictions.ge(key,p1);
				               Criterion b = Restrictions.le(key,p2);
				            	   LogicalExpression orexp =  Restrictions.and(a,b);
				            	  crit.add(orexp);
				               }
			                   
				   	            
			            }
			           
			        }
//			       crit.add(Example.create(t));
		    	List<Trip> results = crit.list();
		    	System.out.println(results.size());
				return results;
    	}
    	catch(Exception e)
    	{
    		
    		System.out.println(e.getMessage());
    	}
    	return null;
    	
    }

	public Double getmaxprice() throws Exception {
		// TODO Auto-generated method stub
		try
    	{
		session = super.sf.openSession();
        tx = session.beginTransaction();
        Criteria crit = session.createCriteria(Trip.class);
        crit.setProjection(Projections.max("price"));
 
        Double p = (Double) crit.uniqueResult();
        return p;
    	}
        catch (HibernateException e) {
		        rollback();
		        throw new Exception("Exception occured: " + e.getMessage());
		    }
	}
    

}
