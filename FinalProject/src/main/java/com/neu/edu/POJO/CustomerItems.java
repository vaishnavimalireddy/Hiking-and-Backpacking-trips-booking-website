package com.neu.edu.POJO;

import javax.persistence.*;


@Entity
@Table
public class CustomerItems {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int itemid;
	
	@Column
	private int numberofpersons;
	
	
	@Column(name="totalprice")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name="tripid")
	private Trip trip;
	
	
	@ManyToOne
	@JoinColumn(name="customerid")
    private CustomerDetails custdetails;

	
	
	public int getNumberofpersons() {
		return numberofpersons;
	}
	public void setNumberofpersons(int numberofpersons) {
		this.numberofpersons = numberofpersons;
	}
	public CustomerDetails getCustdetails() {
		return custdetails;
	}
	public void setCustdetails(CustomerDetails custdetails) {
		this.custdetails = custdetails;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public CustomerItems() {
		
	}
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	
	

}
