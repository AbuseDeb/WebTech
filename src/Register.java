

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Register
 */
@WebServlet("/RegisterServlet")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        
        String login = request.getParameter("login");
        String pass = request.getParameter("password");   
        
		try {
        	String url = "jdbc:mysql://localhost/WebTechDb?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "123456";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
            	String sql = "INSERT INTO Users (Login, Pass, UserType) Values (?, ?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, request.getParameter("login"));
                    preparedStatement.setString(2, request.getParameter("password"));
                    preparedStatement.setInt(3, 1);
                    
                    int q = preparedStatement.executeUpdate();
                }
            }       	
            HttpSession session = request.getSession();
            session.removeAttribute("err");
            
            request.setAttribute("login", login);
        	getServletContext().getRequestDispatcher("/jsp/UserPage.jsp").forward(request, response);
        }
        catch(Exception ex){
            
        	String path = "<a href=\"" + request.getContextPath() + "/Register.jsp\">Register</a>";
        	 writer.println("A user with the same name already exists - ");
        	 writer.println(path);
        }
        finally {
            writer.close();  
        }
		
		
	}

}
