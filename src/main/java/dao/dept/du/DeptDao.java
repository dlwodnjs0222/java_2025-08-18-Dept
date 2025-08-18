package dao.dept.du;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.dept.du.Dept;

public class DeptDao {
	private Connection getConnection() throws ClassNotFoundException, SQLException {  // DBMS 접속
		Class.forName("oracle.jdbc.driver.OracleDriver");
	    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:xe", "scott", "tiger");
		return conn;
	}
	
	public List<Dept> selectList(){
		List<Dept> list = new ArrayList<Dept>();
		Dept dept = null;
		String sql = "select deptno, dname, loc from dept";
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
				while(rs.next()) {
					int deptno = rs.getInt("deptno");
					String dname = rs.getString("dname");
					String loc = rs.getString("loc");
					dept = new Dept(deptno, dname, loc);
					list.add(dept);
				}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
			}
		}
		return list;
	}
	
	
	public Dept selectOne(int deptnos){
		Dept dept = null;
		String sql = "select deptno, dname, loc from dept where deptno = " + deptnos;
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
				if(rs.next()) {
					int deptno = rs.getInt("deptno");
					String dname = rs.getString("dname");
					String loc = rs.getString("loc");
					dept = new Dept(deptno, dname, loc);
				}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
			}
		}
		return dept;
	}
	
	public void insert(Dept dept) {
		String sql = String.format("insert into dept(deptno, dname, loc) values (%d, '%s', '%s')", 
				dept.getDeptno(), dept.getDname(), dept.getLoc());
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
			}
		}
	}
	
	public void delete(String deptno) {
		String sql = String.format("delete from dept where deptno = " + deptno);
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
			}
		}
		
	}
	
	public void update(Dept dept) {
		String sql = String.format("update dept set dname = '%s', loc = '%s' where deptno = %d",
				dept.getDname(), dept.getLoc(), dept.getDeptno());
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				System.out.println("리소스를 닫는 중 오류 발생: " + e.getMessage());
			}
		}
	}
}
