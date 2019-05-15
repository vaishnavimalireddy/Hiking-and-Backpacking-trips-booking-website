package com.neu.edu.POJO;

import java.util.List;


import javax.persistence.*;



@Entity
@Table
public class CustomerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerid;

	@Column(name="name")
	private String name;
	
	
	@Column
	private String email;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	
	private String confirmpassword;
	
	@Column
	private String address;
	
	@Column
	private String phonenumber;
	
	@Column
	private String role;

	
	public CustomerDetails() {
		
	}
	


	@OneToMany(mappedBy= "custdetails",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	List<CustomerItems> tripitems ;
	

	public List<CustomerItems> getTripitems() {
		return tripitems;
	}
	public void setTripitems(List<CustomerItems> tripitems) {
		this.tripitems = tripitems;
	}
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getName() {
		return name;
	}
	public void setName(String cusname) {
		this.name = cusname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	
	
	
	

}
