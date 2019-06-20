package admin.com.huake.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import admin.com.huake.model.AdminDao;
import admin.com.huake.pojo.Admin;

@WebServlet("/adminserlvet")
public class AdminSerlver1 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Admin admin = accept(request);
		AdminDao dao = new AdminDao();
		boolean result = dao.SelectAdmin(admin);

		JSONObject jo = new JSONObject();
		if (result) {
			jo.put("flag", "success");
		}
		
		response.getWriter().print(jo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public Admin accept(HttpServletRequest request) {

		String name = request.getParameter("name");
		String password = request.getParameter("password");

		Admin admin = new Admin();
		admin.setUser_name(name);
		admin.setUser_password(password);

		return admin;
	}
}
