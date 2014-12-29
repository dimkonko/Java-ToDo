import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.dimkonko.todo.etc.DatabaseAPI;
import net.dimkonko.todo.objects.Task;

@SuppressWarnings("serial")
public class TomcatServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // If the button that we have in our jsp page made a POST
        // then the servlet is activated and does whatever we programmed
        // it to do.
        if (request.getParameter("jdbc_query") != null) {
            try {                
                // Use this one without creating/using the context.xml file.
                QueryWithoutContext.query(out);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Here");
		
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
		
		System.out.println(tasks.size());
		
		request.setAttribute("Tasks", tasks);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}
}