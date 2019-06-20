package admin.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.com.huake.pojo.Goods;
import utils.JdbcUtil;

public class EditProductDao {

	public Goods selectProduct(String pid) {

		try {

			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "SELECT * FROM `tgou`.`goods` where id =?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, pid);

			ResultSet res = ps.executeQuery();
			
			if (res.next()) {

				int id = res.getInt("id");
				String title = res.getString("title");
				String oldMoney = res.getString("oldMoney");
				String money = res.getString("money");
				String goodNumber = res.getString("goodNumber");
				String goodIntroduction = res.getString("goodIntroduction");
				String sellNumber = res.getString("sellNumber");
				String images = res.getString("images");

				Goods goods = new Goods();
				
				goods.setId(id);
				goods.setTitle(title);
				goods.setOldMoney(oldMoney);
				goods.setMoney(money);
				goods.setGoodNumber(goodNumber);
				goods.setGoodIntroduction(goodIntroduction);
				goods.setSellNumber(sellNumber);
				goods.setImages(images);

				return goods;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
