package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IntegerDAO.Typeshu;

/**
 * Servlet implementation class Dao
 */
@WebServlet("/Dao")
public class Dao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
        int caozuoshu=Integer.parseInt(request.getParameter("caozuoshu"));
        int kuohao=Integer.parseInt(request.getParameter("kuohao"));
        int fushu=Integer.parseInt(request.getParameter("fushu"));
        int yushu=Integer.parseInt(request.getParameter("yushu"));
        String n1=request.getParameter("timunum");
        int n=Integer.parseInt(request.getParameter("timunum"));
        int t1=Integer.parseInt(request.getParameter("fanwei"));
        
        PrintWriter out=response.getWriter();
        Typeshu shu=new Typeshu();         
        String a[][]=new String [n][2];
        
        switch(caozuoshu)
        {
	         case 1:
	         {
		         try 
		         {
					a=shu.Integer(kuohao, t1, n);   //获取二维数组，第一个是表达式，第二个是结果
				 } 
		         catch (ClassNotFoundException | SQLException e) 
		         {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }    
		         request.getSession().setAttribute("jieguo", a);	
		         response.sendRedirect(request.getContextPath()+"/output.jsp");
		         break;
	         }
	         case 2:
	         {
	        	 try 
	        	 {
					a=shu.fenshu(kuohao,n,t1);           //获取二维数组，第一个是表达式，第二个是结果
				 } 
	        	 catch (ClassNotFoundException | SQLException e) 
	        	 {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }         
		         request.getSession().setAttribute("jieguo", a);
		         response.sendRedirect(request.getContextPath()+"/output.jsp");	        	
	        	 break;
	         }
        }
	}

}
