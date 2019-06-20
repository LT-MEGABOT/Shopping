package admin.com.huake.contoller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import admin.com.huake.model.DeleteProductDao;

@WebServlet("/deletepro")
public class DeleteProductServlet extends HttpServlet { 
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String id = request.getParameter("projectId");
		DeleteProductDao dao = new DeleteProductDao();
		int res = dao.deleteProduct(id);
		
		JSONObject  jo = new JSONObject();
		jo.put("success", res);
		response.getWriter().print(jo);
		
	}
}
