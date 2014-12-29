package net.dimkonko.todo.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dimkonko.todo.etc.DatabaseAPI;

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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		try {
			boolean status = DatabaseAPI.addTask(title);
			
			if (status) {
				response.sendRedirect("/TomcatJDBCExample");
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		response.sendError(500);
	}
}
