package net.dimkonko.todo.etc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.dimkonko.todo.objects.Task;

public class DatabaseAPI {
	
	public static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/Todo";
	public static final String USER = "root";
	public static final String DBPSW = "123456";
	
	public static boolean addTask(String title) throws ClassNotFoundException, SQLException {
		Connection con = null;
		Statement st = null;
		
		String sql = String.format("INSERT INTO `tasks`(`title`, `isDone`) VALUES('%s', %s)",
				title, 0);

		Class.forName(DRIVER_NAME);
		con = DriverManager.getConnection(URL, USER, DBPSW);
		st = con.createStatement();
		
		int status = st.executeUpdate(sql);
		if (status > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean editTask(Task task) throws ClassNotFoundException, SQLException {		
		Connection con = null;
		Statement st = null;
		
		String sql = String.format("UPDATE `tasks` SET `title`='%s', `isDone`=%s WHERE id=%s",
				task.getTitle(), task.isDone(), task.getID());

		Class.forName(DRIVER_NAME);
		con = DriverManager.getConnection(URL, USER, DBPSW);
		st = con.createStatement();
		
		int status = st.executeUpdate(sql);
		
		if (status > 0)
			return true;
		else
			return false;
	}
	
	public static ResultSet selectAllTasks() throws SQLException, ClassNotFoundException {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM tasks";

		Class.forName(DRIVER_NAME);
		con = DriverManager.getConnection(URL, USER, DBPSW);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return rs;
	}
}
