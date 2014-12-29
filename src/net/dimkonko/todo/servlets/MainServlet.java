package net.dimkonko.todo.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dimkonko.todo.etc.DatabaseAPI;
import net.dimkonko.todo.objects.Task;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/index")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	ResultSet rs = null;
		try {
			rs = DatabaseAPI.selectAllTasks();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		if (rs == null) {
			response.sendError(500);
			return;
		}

		List<Task> tasks = new ArrayList<Task>();

		try {
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("id"));
				String title = rs.getString("title");
				Byte isDone = Byte.parseByte(rs.getString("isDone"));
				tasks.add(new Task(id, title, isDone));
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("servletName", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
    }
}
