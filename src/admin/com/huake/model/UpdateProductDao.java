package admin.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import admin.com.huake.pojo.Goods;
import utils.JdbcUtil;

public class UpdateProductDao {

	public int updateGoods(Goods goods){
		
		try {

			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "UPDATE `tgou`.`goods` SET `title` = ?, `oldMoney` = ?,`money` = ? , `goodNumber` = ? , `goodIntroduction` = ? , `sellNumber` =? , `images` = ? WHERE `id` = ? ";

			PreparedStatement ps = conn.prepareStatement(sql);
			
			System.out.println(goods);
			if (goods.getTitle()!=null) {
				ps.setString(1, goods.getTitle());
			}
			if (goods.getOldMoney()!=null) {
				ps.setString(2, goods.getOldMoney());
			}
			
			ps.setString(3, goods.getMoney());
			ps.setString(4, goods.getGoodNumber());
			ps.setString(5, goods.getGoodIntroduction());
			ps.setString(6, goods.getSellNumber());
			ps.setString(7, goods.getImages());
			ps.setInt(8, goods.getId());
			
			int res = ps.executeUpdate();
			return res;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
