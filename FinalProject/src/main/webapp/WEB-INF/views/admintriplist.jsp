<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>

<td width ="300"><b>Trip ID</b></td>

<td width ="300"><b>Name</b></td>


<td width ="300"><b>Destination</b></td>


<td width ="600"><b>Description</b></td>


<td width ="300"><b>Type</b></td>


<td width ="300"><b>Date</b></td>


<td width ="100"><b>Duration</b></td>

<td width ="300"><b>Max Persons </b></td>

<td width ="200"><b>Difficulty level</b></td>


<td width ="100"><b>Price</b></td>


</tr>
<c:forEach items="${sessionScope.triplist}" var="trip">
		<tr>
			<td width ="300">${trip.getTripid()}</td>
			<td width ="300">${trip.getTripname()}</td>
			<td width ="300">${trip.getDestination() }</td>
			<td width ="600">${trip.getDescription() }</td>
			<td width ="300">${trip.getTriptype()}</td>
			<td width ="300">${trip.getDate() }</td>
			<td width ="100">${trip.getDuration()}</td>
			<td width ="300">${trip.getMaxpersons()}</td>
			<td width ="200">${trip.getDifficultylevel()}</td>
			<td width ="300">${trip.getPrice()}</td>
			
			
		</tr>	
	</c:forEach>
</table>
</body>
</html>