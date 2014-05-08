package mvcdemo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import oracle.jdbc.proxy.annotation.Pre;

public class LoginBean implements Serializable {
	private String username;
	private String lastname;
	private String roles;

	public LoginBean() {
		// TODO Auto-generated constructor stub
	}

	public LoginBean(String username, String lastname, String roles) {
		super();
		this.username = username;
		this.lastname = lastname;
		this.roles = roles;
	}

	public boolean checkLogin(String username, String password) {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/poolDS");
			
			Connection cn = ds.getConnection();

			String sql = "Select * from registration where username =? and password =?";
			PreparedStatement pstmt = cn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			boolean result = rs.next();
			rs.close();
			pstmt.close();
			cn.close();
			if (result) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean insert(String username, String password, String lastname,
			String roles) {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/poolDS");

			Connection cn = ds.getConnection();
			String sql = "insert into Registration(username,password,lastname,isadmin) values( ?,?,?,?)";
			PreparedStatement pre = cn.prepareStatement(sql);

			pre.setString(1, username);
			pre.setString(2, password);
			pre.setString(3, lastname);
			pre.setString(4, roles);

			int result = pre.executeUpdate();

			pre.close();
			cn.close();
			if (result > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean delete() {
		try {
			Context ctx = new InitialContext();

			DataSource ds = (DataSource) ctx.lookup("java:/poolDS");

			Connection cn = ds.getConnection();
			String sql = "delete from REGISTRATION where username = ?";
			PreparedStatement pre = cn.prepareStatement(sql);
			pre.setString(1, username);

			int result = pre.executeUpdate();

			pre.close();
			cn.close();
			if (result > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean update() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/poolDS");

			Connection cn = ds.getConnection();
			String sql = "update REGISTRATION set lastname = ? , isadmin = ? where username = ?";
			PreparedStatement pre = cn.prepareStatement(sql);

			pre.setString(1, lastname);
			pre.setString(2, roles);
			pre.setString(3, username);
			int result = pre.executeUpdate();

			pre.close();
			cn.close();
			if (result > 0) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public LoginBean[] searchLikeLastName(String name) {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/poolDS");

			Connection cn = ds.getConnection();
			String sql = "select * from registration where lastname like ?";
			PreparedStatement pre = cn.prepareStatement(sql);
			pre.setString(1, "%" + name + "%");
			ResultSet rs = pre.executeQuery();
			ArrayList list = new ArrayList();
			while (rs.next()) {
				String user = rs.getString("username");
				String lastname = rs.getString("lastname");
				String role = rs.getString("isadmin");
				LoginBean tmp = new LoginBean(user, lastname, role);
				list.add(tmp);
			}
			LoginBean[] result = new LoginBean[list.size()];
			list.toArray(result);

			pre.close();
			rs.close();
			cn.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
