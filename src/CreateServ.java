

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
 * Servlet implementation class CreateServ
 */
@WebServlet("/create")
public class CreateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/Create.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String url = "jdbc:mysql://localhost/WebTechDb?serverTimezone=Europe/Moscow&useSSL=false";
		    String username = "root";
		    String password = "123456";
			
			
            String name = request.getParameter("name");
            int price = Integer.parseInt(request.getParameter("price"));
            
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                  
            	String sql = "INSERT INTO Bets (NameOfBet, Coefficient) Values (?, ?)";
                try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
                    preparedStatement.setString(1, name);
                    preparedStatement.setInt(2, price);
                    
                    int q = preparedStatement.executeUpdate();
                }
            }       	                  
            
            response.sendRedirect(request.getContextPath()+"/AdminPage");
        }
        catch(Exception ex) {
             
            getServletContext().getRequestDispatcher("/Create.jsp").forward(request, response); 
        }
	}

}





