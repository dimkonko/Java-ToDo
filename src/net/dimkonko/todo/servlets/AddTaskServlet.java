package net.dimkonko.todo.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dimkonko.todo.etc.DatabaseAPI;
import net.dimkonko.todo.objects.Task;

/**
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/addTask")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Todo";
		String user = "root";
		String dbpsw = "123456";
		
		String sql = String.format("INSERT INTO `tasks`(`title`, `isDone`) VALUES('%s', %s)",
				title, 0);
		System.out.println(sql);

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, dbpsw);
			st = con.createStatement();
			
			int status = st.executeUpdate(sql);
			if (status > 0) {
				response.sendRedirect("/TomcatJDBCExample");
			} else {
				response.sendError(500);
			}
		} catch(SQLException sqe) {
			System.err.println(sqe);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
	}
}
