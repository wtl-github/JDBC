package leo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		SqlHelp sqlHelp = new SqlHelp();
		try {
			
			String start = "2017-11-18";
			String end = "2018-12-31";
			String sqlLength = "SELECT TIMESTAMPDIFF(DAY,'"+start+"','"+end+"') AS 'length' ";
			String []paras ={};
			ResultSet rs = sqlHelp.querySql(sqlLength,paras);
			int length = 0;
			while(rs.next()){
				length = rs.getInt("length");
			}
			System.out.println(length+1);
			
			List<String> periods = new ArrayList<String>();
			periods.add("09:00,09:30");periods.add("09:30,10:00");
			periods.add("10:00,10:30");periods.add("10:30,11:00");
			periods.add("11:00,11:30");periods.add("11:30,12:00");
			periods.add("12:00,12:30");periods.add("12:30,13:00");
			periods.add("13:00,13:30");periods.add("13:30,14:00");
			periods.add("14:00,14:30");periods.add("14:30,15:00");
			periods.add("15:00,15:30");periods.add("15:30,16:00");
			periods.add("16:00,16:30");periods.add("16:30,17:00");
			periods.add("17:00,17:30");periods.add("17:30,18:00");
			
			for (int i = 0; i <= length; i++) {
				for (int j = 0; periods != null && j < periods.size(); j++) {
					String period = periods.get(j);
					StringBuilder sql = new StringBuilder();
					sql.append(" insert into school_course_timecode ");
					sql.append(" values (");
					sql.append(" NULL,(SELECT MAX(t.timecode) FROM school_course_timecode t)+1");
					sql.append(" ,?,?,NULL");
					sql.append(" ,DATE_ADD('"+start+"', INTERVAL "+i+" DAY) ");
					sql.append(" )");
					String[] params = period.split(",");
					sqlHelp.updateSql(sql.toString(), params);
					int total = (length+1)*periods.size();
					int record = i*periods.size()+j+1;
					System.out.println("====>总记录："+total+"==当前记录："+record);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			sqlHelp.close();
		}
	}
}
