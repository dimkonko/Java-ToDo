<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.*" %>
<%@page import="net.dimkonko.todo.Task" %>
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
		String title = rs.getString("title");
		System.out.println("Ttle: " + title);
		tasks.add(new Task(title, Boolean.parseBoolean("isDone")));
	}
} catch(SQLException sqe) {
	out.println(sqe);
}
%>
	<a href="addTask">Add Task</a>
	<table>
		<tr>
			<td>Title</td>
			<td>isDone</td>
		</tr>
		<% for (Task task : tasks) {%>
		<tr>
			<td> <%=task.getTitle() %> </td>
			<td> <%=task.isDone()%> </td>
		</tr>
		<%} %>
	</table>
</body>
</html>