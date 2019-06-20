package admin.com.huake.contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.com.huake.model.EditProductDao;
import admin.com.huake.model.ShowProductDao;
import admin.com.huake.pojo.Goods;

@WebServlet("/editpro")
public class EditProductServlet extends HttpServlet {
 
    public EditProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("projectId");
		EditProductDao dao = new EditProductDao();
		Goods selectProduct = dao.selectProduct(id) ; 
		request.setAttribute("producetList", selectProduct);
		request.getRequestDispatcher("admin/project_update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
