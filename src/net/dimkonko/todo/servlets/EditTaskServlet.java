package net.dimkonko.todo.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dimkonko.todo.etc.DatabaseAPI;
import net.dimkonko.todo.objects.Task;

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
		String isDone = request.getParameter("task_isDone");
		
		if(isDone != null) {
			isDone = "1";
		} else {
			isDone = "0";
		}		
		
		try {
			boolean status = DatabaseAPI.editTask(new Task(Integer.parseInt(id), title, Byte.parseByte(isDone)));
			if (status) {
				response.sendRedirect("/TomcatJDBCExample");
				return;
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendError(500);
		
		response.sendRedirect("/TomcatJDBCExample");
	}
}
