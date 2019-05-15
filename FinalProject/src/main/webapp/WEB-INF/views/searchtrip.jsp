<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="search.htm" method ="Post">


        
 Search By Place : <input type='text' name='place'/> <br />
     Search By Type : <select name="type">
           <option disabled selected value> -- select an option -- </option>
              <option value="Day Hiking">Day Hiking</option>
              <option value="Overnight Hiking">Overnight Hiking</option>
              
            </select> <br />
            
    Search By Date :  <input type='text' name='date'/> <br />
    
     Search By Price :  between <input type="number"  min="0" name='price1'/> and <input type='number' min="0" name='price2'/> <br />
           
Search By Difficulty Level : <select name="difficultylevel">
             <option disabled selected value> -- select an option -- </option> 
              <option value="Easy">Easy</option>
              <option value="Medium">Medium</option>
              <option value="Hard">Hard</option>
             </select><br />
           <input type  = 'submit' value ='Search Trips' name = 'button'/>
 </form>


</body>
</html>