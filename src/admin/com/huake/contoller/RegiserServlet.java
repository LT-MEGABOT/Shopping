package admin.com.huake.contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import admin.com.huake.model.RegisterDao;
import admin.com.huake.pojo.Admin;

@WebServlet("/regiser")
public class RegiserServlet extends HttpServlet {

	public RegiserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Admin admin = accept(request);
		RegisterDao  dao = new RegisterDao();
		int res = dao.insertAdmin(admin);
		
		JSONObject  jo = new JSONObject() ; 
		jo.put("success", res);
		
		response.getWriter().print(jo);
	}

	public Admin accept(HttpServletRequest request) {

		String name = request.getParameter("userName");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");

		Admin admin = new Admin();
		admin.setUser_name(name);
		admin.setUser_password(password);
		admin.setAccount(account);
		admin.setUser_phone(phone);
		
		return admin;
	}
}
