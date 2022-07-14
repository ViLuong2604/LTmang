package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	Connection connection;

	public Dao() {
		try {
			// load driver
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			// connect
			String url = "jdbc:ucanaccess://onthiLTM.accdb";
			connection = DriverManager.getConnection(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean findUsername(String userName) {
		String sql = "select * from user where username = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	
	public User login(String userName,String password) {
		User result = null;
		String sql = "select * from user where username = ? and password = ? ";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				result = new User(Integer.parseInt(rs.getString("id")), rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		Dao dao = new Dao();
		System.out.println(dao.login("viluong","11111").getId());
	}
}
