<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forgot password?</title>
</head>
<body>
       <form action="forgotpassword.htm" method="POST">
        <table>
            <tr>
                <td>Enter your email:</td>
                <td><input type="text" name="useremail" size="30"
                    required="required" /></td>
            </tr>
            <tr>
                <td>Enter your username:</td>
                <td><input type="text" name="username" size="30"
                    required="required" /></td>
            </tr>
            
                  <tr>
                <td colspan="2"><input type="submit" value="Verify" /></td>
            </tr>
        </table>
        
        </form>
        
        <br/>
           
</body>
</html>