package admin.com.huake.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.com.huake.model.ShowProductDao;
import admin.com.huake.pojo.Goods;

@WebServlet("/showpro")
public class ShowProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ShowProductDao dao = 	new ShowProductDao();
		List<Goods> selectProduct = dao.selectProduct() ; 
		System.out.println(selectProduct);
		request.setAttribute("producetList", selectProduct);
		request.getRequestDispatcher("admin/project_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
