package net.dimkonko.todo.etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.dimkonko.todo.objects.Task;

public class DatabaseAPI {
	
	public static boolean addTask(String title) throws ClassNotFoundException, SQLException {	
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Todo";
		String user = "root";
		String dbpsw = "123456";

		Connection con = null;
		Statement st = null;
		
		String sql = String.format("INSERT INTO `tasks`(`title`, `isDone`) VALUES('%s', %s)",
				title, 0);

		Class.forName(driverName);
		con = DriverManager.getConnection(url, user, dbpsw);
		st = con.createStatement();
		
		int status = st.executeUpdate(sql);
		if (status > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean editTask(Task task) throws ClassNotFoundException, SQLException {
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/Todo";
		String user = "root";
		String dbpsw = "123456";
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		String sql = String.format("UPDATE `tasks` SET `title`='%s', `isDone`=%s WHERE id=%s",
				task.getTitle(), task.isDone(), task.getID());

		Class.forName(driverName);
		con = DriverManager.getConnection(url, user, dbpsw);
		st = con.createStatement();
		
		int status = st.executeUpdate(sql);
		
		if (status > 0)
			return true;
		else
			return false;
	}
}
