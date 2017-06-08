<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1 align="center">请输入限定条件</h1>
	<hr>
	
	
	<form action="Dao" method="post">
	    <table>
	         <tr>
	              <td>请选择操作数的格式</td>
	              <td>
	                  <input type="radio" value="1" name="caozuoshu" checked="checked">整数
	                  <input type="radio" value="2" name="caozuoshu">分数
	              </td>
	         </tr>
	         <br>
	         <tr>
	              <td>表达式是否有括号</td>
	              <td>
	                  <input type="radio" value="1" name="kuohao" checked="checked">无
	                  <input type="radio" value="2" name="kuohao">有
	              </td>
	         </tr>
	         <br>
	         <tr>
	              <td>结果是否有负数</td>
	              <td>
	                  <input type="radio" value="1" name="fushu" checked="checked">无
					  <input type="radio" value="2" name="fushu">有
	              </td>
	         </tr>
	         <br>
	         <tr>
	              <td>结果是否有余数</td>
	              <td>
	                  <input type="radio" value="1" name="yushu" checked="checked">无
					  <input type="radio" value="2" name="yushu">有
	              </td>
	         </tr>
	         <br>
	         <tr>
	              <td>操作数的最大范围</td>
	              <td><input type="text" name="fanwei" ></td>            
	         </tr>
	         <br>
	         <tr>
	              <td>产生题目个数</td>
	              <td><input type="text" name="timunum" ></td>            
	         </tr>
	         <br>
	      
	    </table>
	 <input type="submit" value="确定">
	</form>
</center>
</body>
</html>