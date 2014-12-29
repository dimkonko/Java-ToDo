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
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
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
			<td class="task_id"><%=task.getID() %></td>
			<td class="task_title"><%=task.getTitle() %></td>
			<td><input type="Checkbox" <% if (task.isDone() > 0) {%>checked<% }%>/></td>
			<td><input class="edit_task" type="button" value="Edit" /></td>
		</tr>
		<%} %>
	</table>
	<div id="edit_div" hidden>
		Edit:
		<form  action="editTask" method="POST">
			<input id="editId" type="text" name="id" />
			<br><input id="editTitle" type="text" name="title" />
			<br><input id="editIsDone" type="checkbox" name="isDone"/>
			<br><input type="submit" value="Edit"/>
		</form>
	</div>
	
	<script type="text/javascript">
	(function() {
		$(".edit_task").on("click", function(event) {
			var tr = $(this).closest("tr");
			var id = tr.find(".task_id").html();
			var title = tr.find(".task_title").html();
			var isDone = tr.find("input[type=checkbox]").is(':checked');
		
			$("#editId").val(id);
			$("#editTitle").val(title);
			$("#editIsDone").prop('checked', true);
			
			$("#edit_div").show();
		})
	})();
	</script>
</body>
</html>