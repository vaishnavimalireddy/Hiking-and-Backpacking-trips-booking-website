<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trips</title>
</head>
<body>
 <c:choose>
 
     <c:when test="${!empty sessionScope.triplist}">
           

<form action="booktrip.htm" METHOD = "POST">
<h2> List of Trips available </h2>
<table border = "1">
<tr>
<td width ="300"><b>Name</b></td>


<td width ="300"><b>Destination</b></td>


<td width ="600"><b>Description</b></td>


<td width ="300"><b>Type</b></td>


<td width ="300"><b>Date</b></td>


<td width ="100"><b>Duration</b></td>


<td width ="200"><b>Difficulty level</b></td>


<td width ="200"><b>Price</b></td>


<td width ="30"><b></b></td>

</tr>
<c:forEach items="${sessionScope.triplist}" var="trip">
		<tr>
			
			<td width ="300">${trip.getTripname()}</td>
			<td width ="300">${trip.getDestination() }</td>
			<td width ="600">${trip.getDescription() }</td>
			<td width ="300">${trip.getTriptype()}</td>
			<td width ="300">${trip.getDate() }</td>
			<td width ="100">${trip.getDuration()}</td>
			<td width ="200">${trip.getDifficultylevel()}</td>
			<td width ="200">${trip.getPrice()}</td>
			<%-- <td><a href="booktrip.htm?fid=${flight.flight_id}" id="link">Book This Trip</a></td> --%>
			<td width ="300" colspan="2">
			
			<td><a href="booktrip.htm?tripid=${trip.getTripid()}"  id="link">Book Ticket</a></td>
			
		
			
		</tr>	
	</c:forEach>
</table>
</form>
</c:when>
<c:otherwise>
   
   <b> I am sorry, currently there are no trips available</b> <br><br>
    <>            
 </c:otherwise>
</c:choose>
<br>
<br>
<br>


<a href= "logout.htm"> Log out</a> 
<!-- <script>
	function addtripid(tripid){
		$.ajax({
			  url:"booktrip.htm",
			  data: {id:tripid},
			  type:"POST"
			});
			
			 onclick="addtripid(${trip.getTripid()})"
	}
	
</script> -->
</body>
</html>