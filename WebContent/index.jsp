<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*" %>
<%@page import="net.dimkonko.todo.objects.Task" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Main</title>
</head>
<body>
<%
Connection con= null;
PreparedStatement ps = null;
ResultSet rs = null;

List<Task> tasks = new ArrayList<Task>();

String driverName = "com.mysql.jdbc.Driver";
String url = "jdbc:mysql://localhost:3306/Todo";
String user = "root";
String dbpsw = "123456";

String sql = "SELECT * FROM tasks";

try {
	Class.forName(driverName);
	con = DriverManager.getConnection(url, user, dbpsw);
	ps = con.prepareStatement(sql);
	rs = ps.executeQuery();
	while(rs.next()) {
		int id = Integer.parseInt(rs.getString("id"));
		String title = rs.getString("title");
		Byte isDone = Byte.parseByte(rs.getString("isDone"));
		tasks.add(new Task(id, title, isDone));
	}
} catch(SQLException sqe) {
	out.println(sqe);
}
%>
	<a href="addTask.jsp">Add Task</a>
	<table>
		<tr>
			<td>Title</td>
			<td>isDone</td>
			<td>Edit</td>
		</tr>
		<% for (Task task : tasks) {%>
		<tr>
			<td> <%=task.getTitle() %> </td>
			<td> <input type="Checkbox" <% if (task.isDone() > 0) {%>checked<% }%>/> </td>
			<td>
				<form action="editTask" method="POST">
					<input type="text" name="editId" value="<%=task.getID() %>" hidden/>
					<input type="submit" value="Edit"/>
				</form>
			</td>
		</tr>
		<%} %>
	</table>
</body>
</html>