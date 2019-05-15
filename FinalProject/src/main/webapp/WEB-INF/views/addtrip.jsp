<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>




<html>
<head>
<meta charset="UTF-8">
<title>Add Trip</title>
</head>

<script type="text/javascript">

  /* function checkdate()
  {
	   String = document.getElementById("tripdate").value;
       if (new Date(input.value) < new Date()) {
	            alert("You should select a day future than today!");
	            return false;
       }
       return true;
  } */



  var xmlHttp;
  
	try     // Firefox, Opera 8.0+, Safari
	{
		xmlHttp=new XMLHttpRequest();
	}
	catch (e)
	{
		try  // Internet Explorer
		{
			xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		}
		catch (e)
		{
			try
			{
				xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e)
			{
				alert("Your browser does not support AJAX!");
				return false;
			}
		}
	}


	
  function checkdate() {
      var tripdate = document.getElementById("tripdate").value;
      var act = "action=checkdate&tripdate="+tripdate;

      xmlHttp.onreadystatechange = function stateChanged()
      {
          if (xmlHttp.readyState == 4)
          {
              //alert(xmlHttp.responseText);
              var json = JSON.parse(xmlHttp.responseText);
              document.getElementById("error").innerHTML="";
              document.getElementById("error").innerHTML = json.message;
              
          }
      };
      xmlHttp.open("POST", "addtrip.htm", true);
      xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      xmlHttp.send(act);
     return false;
  }
     
   

  
 </script>
 
 
<body>
<form action ="addtrip.htm?action=addtrip" method = "POST" >
<div id="error" style="color:red"></div>
<table>
		<tr>
			<td width ="100">
			 Trip Name : 
			</td>
			
			<td>
			<input type="text" name="tripname" required ="required"/> 
			</td>
		</tr>
		
		<tr>
			<td width ="10">
			 Trip Destination : 
			</td>
			
			<td>
			<input type="text" name="destination" required ="required"/> 
			</td>
		</tr>
		
		<tr>
			<td width ="10">
			 Trip Price : 
			</td>
			
			<td>
			<input type="number" name="price" min="0" required ="required" id ="price" oninput="check(this)" /> 
			</td>
		</tr>
		
		<tr>
			<td width ="10">
			 Max Persons : 
			</td>
			
			<td>
			<input type="number" name="maxpersons" min="0" required ="required" id="maxpersons" oninput="check(this)"/> 
			</td>
		</tr>
		
		<tr>
			<td width ="10">
			 Trip Description : 
			</td>
			
			<td>
			<input type="text" name="description" required ="required"/> 
			</td>
		</tr>
		
		<tr>
			<td width ="10">
			 Trip Date(yyyy-mm-dd) : 
			</td>
			
			<td>
			<input type="text" name="tripdate" required ="required" id="tripdate" onblur="return checkdate()" /> 
			</td>
		</tr>
		
		<tr>
			<td width ="200">
			 Difficulty level : 
			</td>
			
			<td>
			<select name="difficultylevel">
		              <option value="Easy">Easy</option>
		              <option value="Medium">Medium</option>
		              <option value="Hard">Hard</option>
		     </select>
			</td>
		</tr>
		
		<tr>
			<td width ="100">
			 Duration : 
			</td>
			
			<td>
			<input type="text" name="duration" required ="required"/> 
			</td>
		</tr>
		
		<tr>
			<td width ="100">
			 Trip Type : 
			</td>
			
			<td>
			<select name="type">
		              <option value="Day Hiking">Day Hiking</option>
		              <option value="Overnight Hiking">Overnight Hiking</option>
		              
		     </select>
			</td>
		</tr>
		

</table>
<br>
<br>

<input type="Submit" value="Add Trip"/>
</form>
</body>
</html>