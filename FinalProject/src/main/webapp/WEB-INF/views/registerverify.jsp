<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

    Please enter verification code which has been sent to your email 
    <br/>
    <br/>
    <form action ="registerverify.htm" method = "POST" >
    <input type="text" name="verificationcode" size="30"/>
     <br/> 
      <br/> 
    <input type="submit" value="Verify" />
    </form>
   
</body>
</html>