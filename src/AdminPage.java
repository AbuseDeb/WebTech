

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminPage
 */
@WebServlet("/AdminPage")
public class AdminPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminPage() {
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
			
			
        	String url = "jdbc:mysql://localhost/WebTechDb?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "123456";
            
            //String login = request.getParameter("login");
			ArrayList<String> bets = new ArrayList<String>();
			
			try (Connection conn = DriverManager.getConnection(url, username, password)){
	            
	        	String sql = "SELECT NameOfBet, Coefficient FROM Bets";
	        	
	        	Statement statement = conn.createStatement();
	        	ResultSet resultSet = statement.executeQuery(sql);
	                
	            while(resultSet.next()){
	            	
	            	String name = resultSet.getString(1);
	            	int Coeff = resultSet.getInt(2);
                    
                    String res = name + String.valueOf(Coeff);
                    
	            	bets.add(res);   
	            }
	            
	            //request.setAttribute("login", login);
        		request.setAttribute("bets", bets);
        		getServletContext().getRequestDispatcher("/AdminPage.jsp").forward(request, response);
			}
		}
		catch(Exception ex){			
			writer.println(ex);
        }
        finally {
        	writer.close();  
        }
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}
}


