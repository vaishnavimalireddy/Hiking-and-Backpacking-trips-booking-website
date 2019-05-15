package com.neu.edu.POJO;

import java.util.List;

import javax.persistence.*;


@NamedQueries({
	@NamedQuery(
	name = "tripdetails",
	query = "from Trip where tripid=:tripid"
	),
	@NamedQuery(
			name = "tripdetailsdelete",
			query = "delete from Trip where tripid=:tripid"
			),
	@NamedQuery(
			name = "alltrips",
			query = "from Trip"
			)
})


@Entity
@Table
public class Trip {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tripid;
    
    @Column(name="tripName")
	private String tripname;
    
    @Column(name="destination")
	private String Destination;
    
    @Column
	private double price;
	
    @Column
	private int maxpersons;
    
    @Column
	private String description;
    
    @Column
	private java.sql.Date date;
	
	@Column(name="difficulty")
	private String difficultylevel;
	
	@Column
	private String duration;
	
	@Column(name="type")
	private String triptype;
	
	@OneToMany(mappedBy= "trip",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CustomerItems> customeritems;
	
	

//	@OneToOne
//	@JoinColumn(name="customerid")
//	private User customerdetails;

	
	
	public java.sql.Date getDate() {
		return date;
	}
	
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	
	public String getDifficultylevel() {
		return difficultylevel;
	}
	
	public void setDifficultylevel(String difficultylevel) {
		this.difficultylevel = difficultylevel;
	}
	
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getTriptype() {
		return triptype;
	}
	public void setTriptype(String triptype) {
		this.triptype = triptype;
	}
	public List<CustomerItems> getCustomeritems() {
		return customeritems;
	}
	public void setCustomeritems(List<CustomerItems> customeritems) {
		this.customeritems = customeritems;
	}
	
	
	
	
	public int getTripid() {
		return tripid;
	}
	public void setTripid(int tripid) {
		this.tripid = tripid;
	}
	public String getTripname() {
		return tripname;
	}
	public void setTripname(String tripname) {
		this.tripname = tripname;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getMaxpersons() {
		return maxpersons;
	}
	public void setMaxpersons(int maxpersons) {
		this.maxpersons = maxpersons;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<CustomerItems> getCartitems() {
		return customeritems;
	}
	public void setCartitems(List<CustomerItems> cartitems) {
		this.customeritems = cartitems;
	}
	
	
}
