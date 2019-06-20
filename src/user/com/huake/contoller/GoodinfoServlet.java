package user.com.huake.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import admin.com.huake.model.ShowProductDao;
import admin.com.huake.pojo.Goods;
import user.com.huake.model.GoodinfoDao;

@WebServlet("/GoodinfoServlet")
public class GoodinfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		GoodinfoDao dao = new GoodinfoDao();
		List<Goods> selectGoodinfo = dao.selectGoodinfo() ; 
		//System.out.println(selectGoodinfo);
		//request.setAttribute("producetList", selectGoodinfo);
		
		JSONObject jo = new JSONObject();
		jo.put("data", selectGoodinfo);
		response.getWriter().print(jo);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

