

import java.io.PrintWriter;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/LoginServlet")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html");
	     PrintWriter writer = response.getWriter();
	     try {
	    	 writer.println("<h2>Hello from HelloServlet</h2>");
         } finally {
             writer.close();  
         }
		
		
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
 
        String login = request.getParameter("login");
        String pass = request.getParameter("password");      
                
        Boolean bRes = false;
        String passDb = null;
        int UserType = 0;
        //request.setAttribute("login", login);
        
		try {
        	String url = "jdbc:mysql://localhost/WebTechDb?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
            	String sql = "SELECT Pass, UserType FROM Users Where Login =?";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, request.getParameter("login"));
                    
                    ResultSet resultSet = preparedStatement.executeQuery();
                    
                    if(resultSet.next()){
                    	 
                    	passDb = resultSet.getString(1);
                        UserType = resultSet.getInt(2);                
                    }
                }
            }       	
            
            if(pass.equals(passDb))
            {
            	
            	if(UserType == 0)
            	{
            		            	
                	getServletContext().getRequestDispatcher("/AdminPage").forward(request, response);
                	
                	
            	}
            	
            }
            
        }
        catch(Exception ex){
            writer.println("Connection failed...");
            writer.println(ex);
        }
        finally {
            writer.close();  
        }
		
		
	}

}
