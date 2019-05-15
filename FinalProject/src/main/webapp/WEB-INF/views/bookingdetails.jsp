<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Confirm booking</title>
<script>

function bookpersons(){
	var isValid = true;
	var node = document.getElementById("error");
	
	var txtContent = node.textContent;
	
	
	if(txtContent=="Limit seats are available")
		{
		isValid = false;
		document.getElementById("notvalid").innerHTML= "";
		alert("Please enter less persons");
		}
	return isValid;
	
}
var xmlHttp;
xmlHttp = GetXmlHttpObject();
function checkPersons() {
 if (xmlHttp == null)
  {
      alert("Your browser does not support AJAX!");
      return;
  }
  var numberofpersons = document.getElementById("numberofpersons").value;
  var act = "action=checkmaxpersons&numberofpersons="+numberofpersons;

  xmlHttp.onreadystatechange = function stateChanged()
  {
      if (xmlHttp.readyState == 4)
      {
          //alert(xmlHttp.responseText);
          var json = JSON.parse(xmlHttp.responseText);
          document.getElementById("error").innerHTML="";
          document.getElementById("error").innerHTML = json.message;
          alert("Please enter less persons");
          
      }
  };
  xmlHttp.open("POST", "bookdetails.htm", true);
  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlHttp.send(act);
 return false;
}

function GetXmlHttpObject()
{
  var xmlHttp = null;
  try
  {
      // Firefox, Opera 8.0+, Safari
      xmlHttp = new XMLHttpRequest();
  } catch (e)
  {
      // Internet Explorer
      try
      {
          xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
      } catch (e)
      {
          xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
      }
  }
  return xmlHttp;
}






</script>
</head>
<body>
 <c:set value="${trip}" var="trip"/>

<form action="bookdetails.htm?action=book" METHOD = "POST" onSubmit ="return checkPersons()">
<div id="notvalid"></div>
         <div id="error" style="color:red"></div>
<h2> Booking Details </h2>
<table >
		<tr>
				<td><b>Trip Name</b></td>
				<td>${trip.getTripname()}</td>
		</tr>
		
		<tr>
			<td><b>Trip Id</b></td>
			<td>${trip.getTripid()}</td>
			</tr>
		
		<tr>
			<td><b>Price</b></td>
			<td>${trip.getPrice()}</td>
		</tr>
		
		
		<tr>
			<td><b>Number of Persons</b></td>
			<td><input type="number" name="numberofpersons" id = "numberofpersons" min="1" /></td>
		</tr>
		
		<tr>
			<td><b>Trip Type</b></td>
			<td>${trip.getTriptype()}</td>
		</tr>
		
		<tr>
		
				<td><b>Date</b></td>
				<td>${trip.getDate() }</td>
				
		</tr>
		
		<tr>
			<td><b>Duration</b></td>
			<td>${trip.getDuration()}</td>
		</tr>
		
		<tr>
			<td><b>Difficulty level</b></td>
			<td>${trip.getDifficultylevel()}</td>
		</tr>
		
	

</table>

<br>
<br>


<input type="Submit" value = "Confirm Booking" /> 
<br>
<br>
<a href= "logout.htm"> Log out</a> 
</form>
</body>
</html>