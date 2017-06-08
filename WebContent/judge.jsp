<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>判断页面</title>
</head>
<body>
	<h1 align="center" style="color:red ">回答结果</h1>
	<hr>
	<%	
	    String[][] b=(String[][])session.getAttribute("jieguo");            //获取正确结果       
	    //int re=Integer.parseInt(((String)session.getAttribute("n1")));   //获取题目数量	 
		String a[]=request.getParameterValues("result");         //获取文本框中输入的结果
		int j=0;
	    int z=0;
	    int rig[]=new int [b.length];
		int wro[]=new int [b.length];	 
		
	    int numr=0,numw=0;
		for(int i=0;i<b.length;i++)                //进行比较
		{
			if(a[i].equals(b[i][1]))
			{
				numr++;
				rig[j]=i+1;
				j++;
			}
			else
			{
				numw++;
				wro[z]=i+1;
				z++;
			}	
		}
		
		out.print("<center>");
	    out.print("做对了"+numr+"道题，题号为：");
	    for(int i=0;i<numr;i++)
	    {
	    	out.print(rig[i]+" ，");
	    }
	    out.print("<br>");	
	    out.print("做错了"+numw+"道题，题号为：");
	    for(int i=0;i<numw;i++)
	    {
	    	out.print(wro[i]+" ，");
	    }
	    out.print("</center>");
	%>
	<a href="Require.jsp">继续做题</a> 
	<a href="judge.jsp">返回</a>
</body>
</html>