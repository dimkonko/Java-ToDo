<%@page import="net.dimkonko.todo.etc.DatabaseAPI"%>
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
	<a href="addTask.jsp">Add Task</a>
	<table>
		<tr>
			<td>Title</td>
			<td>isDone</td>
			<td>Edit</td>
		</tr>
		<%
		List<Task> tasks = (ArrayList<Task>) request.getAttribute("servletName");
		if (tasks == null) {
			response.sendError(500);
			return;
		}
		for (Task task : tasks) {%>
		<tr>
			<td class="task_id"><%=task.getID() %></td>
			<td class="task_title"><%=task.getTitle() %></td>
			<td><input type="Checkbox" <% if (task.isDone() > 0) {%>checked<% }%> readonly/></td>
			<td><input class="edit_task" type="button" value="Edit" /></td>
		</tr>
		<%} %>
	</table>
	<div id="edit_div" hidden>
		Edit:
		<form  action="editTask" method="POST">
			<input id="editId" type="text" name="task_id" hidden/>
			<input id="editTitle" type="text" name="task_title" />
			<br><input id="editIsDone" type="checkbox" name="task_isDone"/>
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
		
			console.log(isDone);
			
			$("#editId").val(id);
			$("#editTitle").val(title);
			$("#editIsDone").prop('checked', isDone);
			
			$("#edit_div").show();
		})
	})();
	</script>
</body>
</html>