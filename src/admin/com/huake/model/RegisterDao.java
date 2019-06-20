package admin.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import admin.com.huake.pojo.Admin;
import utils.JdbcUtil;

public class RegisterDao {

	public int insertAdmin(Admin admin) {

		try {

			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "INSERT INTO `tgou`.`admin` (`user_name`, `account`, `user_password`, `user_phone`) VALUES (?, ?, ?, ?)";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, admin.getUser_name());
			ps.setString(2, admin.getAccount());
			ps.setString(3, admin.getUser_password());
			ps.setString(4, admin.getUser_phone());

			int res = ps.executeUpdate();
			return res;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
