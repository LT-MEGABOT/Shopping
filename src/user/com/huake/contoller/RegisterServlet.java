package user.com.huake.contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import user.com.huake.model.RegisterDao;
import user.com.huake.pojo.User;
    
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		User user = accept(request);
		RegisterDao dao = 	new RegisterDao();
		int res = dao.insertUser(user);

		JSONObject  jo = new JSONObject();
		if (res==1) {
			jo.put("resultCode", 1);
			jo.put("message", "注冊成功");
		}else{
			jo.put("resultCode", 0);
			jo.put("message", "注冊失敗");
		}
	
		response.getWriter().print(jo);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public User accept(HttpServletRequest request) {

		String email = request.getParameter("email");
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		User user = new User();
		user.setAccount(account);
		user.setEmail(email);
		user.setPassword(password);

		return user;
	}
}
