<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

     <c:set var="contextPath" value="${pageContext.request.contextPath}" />
    <form action="login.htm" method="POST">
        <table>
        <tr>
            <td>UserName:</td>
            <td><input type="text" name="username" size="30" required="required" /></td>
        </tr>
        
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password" size="30" required="required"/></td>
        </tr>
        
        <tr>
            <td colspan="2"><input type="submit" value="Login" /></td>
        </tr>
                
        </table>
    </form>
     <a href="forgotpassword.htm">Forgot password?</a>
    <a href="register.htm">Register User</a>
 
</body>
</html>