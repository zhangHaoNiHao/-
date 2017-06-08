<%@ page language="java" import="IntegerDAO.Typeshu" import="java.util.*" import="java.sql.*" import="java.text.*"  contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">答题页面</h1>
 <hr> 
      <form action="judge.jsp" method="post">     
      <center>
      	<table border="2">
      	<% 
      		 String a[][]=(String[][])request.getSession().getAttribute("jieguo"); 
	      	 for(int i=0;i<a.length;i++)
	         {   
	        	 out.print("<tr>");
	        	 out.print("<td>");
	        	 out.print(a[i][0]);
	        	 out.print("</td>");
	        	 out.print("<td>");
	        	 out.print("<input name='result'>");		        	 
	        	 out.print("</td>");		        	 
	        	 out.print("</tr>");
	         }  	
      	%>
      	</table>
      	<input type="submit" value="提交" style="color:blue">
     </form> 
</body>
</html>