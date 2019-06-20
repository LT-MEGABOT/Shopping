package admin.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import utils.JdbcUtil;

public class DeleteProductDao {

	public int deleteProduct(String id) {

		try {
			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "DELETE FROM `tgou`.`goods` WHERE `id` =?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			int res = ps.executeUpdate();
			return res;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
