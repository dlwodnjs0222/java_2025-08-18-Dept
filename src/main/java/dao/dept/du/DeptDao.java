package dao.dept.du;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
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
		PreparedStatement stmt = null;
//		String sql = "select deptno, dname, loc from dept";
		Connection conn = null;
//		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
			stmt = conn.prepareStatement("select deptno, dname, loc from dept");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				int deptno = rs.getInt("deptno");
				String dname = rs.getString("dname");
				String loc = rs.getString("loc");
				dept = new Dept(deptno, dname, loc);
				list.add(dept);
			}
		} catch (SQLException e) {
			System.out.println("데이터베이스 오류: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
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
//		String sql = "select deptno, dname, loc from dept where deptno = " + deptnos;
		PreparedStatement stmt = null;
		Connection conn = null;
//		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
			stmt = conn.prepareStatement("select deptno, dname, loc from dept where deptno = ?");
			stmt.setInt(1, deptnos);
			rs = stmt.executeQuery();

				if(rs.next()) {
					int deptno = rs.getInt("deptno");
					String dname = rs.getString("dname");
					String loc = rs.getString("loc");
					dept = new Dept(deptno, dname, loc);
				}
		} catch (SQLException e) {
			System.out.println("데이터베이스 오류: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
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
//		String sql = String.format("insert into dept(deptno, dname, loc) values (%d, '%s', '%s')", 
//				dept.getDeptno(), dept.getDname(), dept.getLoc());
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
			stmt = conn.prepareStatement("insert into dept(deptno, dname, loc) values (?, ?, ?)");
			stmt.setInt(1, dept.getDeptno());
			stmt.setString(2, dept.getDname());
			stmt.setString(3, dept.getLoc());
			stmt.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println("데이터베이스 오류: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
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
//		String sql = String.format("delete from dept where deptno = " + deptno);
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
			
			stmt = conn.prepareStatement("delete from dept where deptno = ?");
			stmt.setString(1, deptno);
		    stmt.executeUpdate();
				
		} catch (SQLException e) {
			System.out.println("데이터베이스 오류: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
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
//		String sql = String.format("update dept set dname = '%s', loc = '%s' where deptno = %d",
//				dept.getDname(), dept.getLoc(), dept.getDeptno());
		Connection conn = null;
//		Statement stmt = null;
		PreparedStatement stmt = null;
		
		try {
			conn = getConnection();
//			stmt = conn.createStatement();
//			stmt.executeUpdate(sql);
			stmt = conn.prepareStatement("update dept set dname = ?, loc = ? where deptno = ?");
			stmt.setString(1, dept.getDname());
			stmt.setString(2, dept.getLoc());
			stmt.setInt(3, dept.getDeptno());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("데이터베이스 오류: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다: " + e.getMessage());
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
