<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
   
   <%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Form</title>
<script type="text/javascript">

  
  function Validate() {
      var password = document.getElementById("pass").value;
      var confirmPassword = document.getElementById("pass1").value;
      if (password != confirmPassword) {
          alert("Passwords do not match.");
          return false;
      }
      return true;
  }

  function registerUser(){
		var isValid = true;
		var node = document.getElementById("error");
		
		var txtContent = node.textContent;
		
		
		if(txtContent=="Username already exists")
			{
			isValid = false;
			document.getElementById("notvalid").innerHTML= "";
			alert("Please enter unique username");
			}
		return isValid;
		
	}
  var xmlHttp;
  xmlHttp = GetXmlHttpObject();
  function checkUser() {
     if (xmlHttp == null)
      {
          alert("Your browser does not support AJAX!");
          return;
      }
      var username = document.getElementById("username").value;
      var act = "action=checkusername&username="+username;

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
      xmlHttp.open("POST", "register.htm", true);
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

      <form action="register.htm?action=register" method="POST" onSubmit =" return registerUser()" > 
         <div id="notvalid"></div>
         <div id="error" style="color:red"></div>
        <table>
        <tr>
            <td>Name:</td>
            <td><input size="30" type="text" name ="name" required="required"  /></td>
        </tr>
        
         <tr>
            <td>Email:</td>
            <td><input  type="email" name="email" required="required"  size="30" /></td>
        </tr>
        
         <tr>
            <td>Username :</td>
            <td><input name="username" type="text" size="30" required="required" id="username" onblur="return checkUser()" /></td>
            
        </tr>
        
        
        <tr>
            <td>Password:</td>
            <td><input type="password"  name="password" required="required" size="30" id="pass" /></td>
        </tr>
         <tr>
            <td>Confirm Password:</td>
            <td><input  type="password"  name="conpassword" required="required" size="30" id="pass1" /></td>
        </tr>
        
          <tr>
            <td>Address :</td>
            <td><input type="text" name="address" required="required" size="30" /></td>
        </tr>
        
          <tr>
            <td>Phone No :</td>
            <td><input type="tel" name="phone" required="required" size="30" /></td>
        </tr>
        
        <%-- <tr>
	         <td colspan="2">
	                <label for="captchaCode" class="prompt">Retype the characters from the picture:</label> 
	                <%
	                    // Adding BotDetect Captcha to the page
	                    Captcha captcha = Captcha.load(request, "CaptchaObject");
	                    captcha.setUserInputID("captchaCode");
	
	                    String captchaHtml = captcha.getHtml();
	                    out.write(captchaHtml);
	                %> 
	                <input id="captchaCode" type="text" name="captchaCode" required="required"/>
	          </td>
        </tr> --%>
        <tr> 
        
            <td colspan="2"><input type="submit" value="Register" onclick="return Validate()"/></td>
        </tr>
        
         
                
        </table>
   
</form>



</body>
</html>