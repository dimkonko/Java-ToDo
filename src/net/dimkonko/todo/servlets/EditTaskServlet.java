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

/**
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/editTask")
public class EditTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTaskServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("task_id");
		String title = request.getParameter("task_title");
		String isDone = request.getParameter("isDone");
		
		System.out.println(isDone);
		
		if (isDone.equals("on")) {
			isDone = "1";
		} else {
			isDone = "0";
		}
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Todo";
		String user = "root";
		String dbpsw = "123456";
		
		String sql = String.format("UPDATE `tasks` SET `title`='%s', `isDone`=%s WHERE id=%s",
				title, isDone, id);
		System.out.println(sql);

		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, dbpsw);
			st = con.createStatement();
			
			int status = st.executeUpdate(sql);
			if (status > 0) {
				response.sendRedirect("/TomcatJDBCExample");
				return;
			} else {
				response.sendError(500);
				return;
			}
		} catch(SQLException sqe) {
			System.err.println(sqe);
		} catch (ClassNotFoundException e) {
			System.err.println(e);
		}
		response.sendRedirect("/TomcatJDBCExample");
	}
}
