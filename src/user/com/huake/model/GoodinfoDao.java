package user.com.huake.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import admin.com.huake.pojo.Goods;
import user.com.huake.pojo.User;
import utils.JdbcUtil;

public class GoodinfoDao {

	public List<Goods> selectGoodinfo() {

		try {

			Connection conn = JdbcUtil.getInstace().getConn();
			String sql = "select * from goods";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet res = ps.executeQuery();

			List<Goods> list = new ArrayList<Goods>();
			
				while (res.next()) {
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

					list.add(goods);
				}
				return list;
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

