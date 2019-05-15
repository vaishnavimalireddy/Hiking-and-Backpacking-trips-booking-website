<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set value="${customeritems}" var="customeritems"/>

 Total Cost for the trip is :  <font color="red" >${customeritems.getTotalPrice()} </font>
<br> 
<h3> Enter payment details</h3>

<form action="payment.htm" method="POST">

	Card Number :
	<input type="number" name="card" required="required" /> <br>
	Bank Name  :
	<input type="text" name="bankname" required="required" /> <br>
	Name on card  :
	  <input type="text" name="name" required = "required"/><br>
	Expiration Month  :
	 <input type="number" name="expmonth" max="12" required ="required"/><br>
	Expiration Year  :
	  <input type="number" name="expyear" min="19" required ="required"/> <br>
	  
	CVV 
	 <input type="number" name="cvv" required="required"   /> <br> <br>
	
	    <input type="submit" value="Book Trip" />
	
	
	
</form>



</body>
</html>