package leo;

import java.sql.ResultSet;
import java.util.Date;

public class SysReadAttachMain {
	public static void main(String[] args) {
		SqlHelp sqlHelp = new SqlHelp();
		try {
			int rid = 94;
			String type = "2";
			int userId = 2;
			String del_flag = "0";
			String uploadkey = "WnALqwatV11solR68vimaphTCjYu8yoDjBxdB2AyUIM";
			
			StringBuilder sqlLength = new StringBuilder();
			sqlLength.append(" SELECT GROUP_CONCAT(DISTINCT t.id) AS 'ids' ");
			sqlLength.append(" FROM attachment t ");
			sqlLength.append(" WHERE 1=1 ");
			sqlLength.append(" and t.uploadkey = ? ");
			sqlLength.append(" and t.del_flag = '0' ");
			Object []paras ={uploadkey};
			ResultSet rs = sqlHelp.querySql(sqlLength.toString(),paras);
			String ids = "";
			while(rs.next()){
				ids = rs.getString("ids");
			}
			String[] id = ids.split(",");
			for (int i = 0; i < id.length; i++) {
				int aid = Integer.parseInt(id[i]);
				StringBuilder sql = new StringBuilder();
				sql.append(" insert into sys_read_attach ");
				sql.append(" values (NULL,?,?,?,?,?,?,?,?,null,null)");
				Object[] params = {type,rid,aid,userId,new Date(),userId,new Date(),del_flag};
				sqlHelp.updateSql(sql.toString(), params);
			}
			System.out.println(id.length);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			sqlHelp.close();
		}
	}
}
