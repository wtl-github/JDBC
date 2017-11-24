package leo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelp {
	
	public Connection con;
	public PreparedStatement ps;
	public ResultSet rs;
	
	String driver = "com.mysql.jdbc.Driver";
	/*String url = "jdbc:mysql://192.168.11.13:3306/aischool";
	String user = "nc";
	String passwd = "123";*/
	String url = "jdbc:mysql://localhost:3306/museum";
	String user = "root";
	String passwd = "123";
	
	public void close(){
		try {
			if(rs != null) rs.close();
			if(ps != null) ps.close();
			if(con != null) con.close();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
	}
	
	
	//增，删，改
	public boolean updateSql(String sql, Object []paras){
		boolean b = true;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			ps = con.prepareStatement(sql);				
			for(int i=0; i<paras.length; i++ ){
				//ps.setString(i+1, paras[i]);
				ps.setObject(i+1, paras[i]);
			}
			
			if(ps.executeUpdate() == -1){
				b = false;
			}	
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}		
		return b;
	}
	//查
	public ResultSet querySql(String sql,Object []paras){
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, passwd);
			ps = con.prepareStatement(sql);
			for(int i=0; i<paras.length; i++){
				//ps.setString(i+1, paras[i]);
				ps.setObject(i+1, paras[i]);
			}
			rs = ps.executeQuery();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	
}
