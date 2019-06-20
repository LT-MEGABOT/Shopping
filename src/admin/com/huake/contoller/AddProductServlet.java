package admin.com.huake.contoller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import admin.com.huake.model.AddProductDao;
import admin.com.huake.pojo.Goods;

@WebServlet("/addpro")
public class AddProductServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 得到 网页中 用户 输入的 所有数据
		Goods goods = uploadImage(request, response);

		AddProductDao dao = new AddProductDao();

		int res = dao.addProduct(goods);

		request.getRequestDispatcher("showpro").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// 用来接收 网页中的 文件 数据 与 普通信息
	// 如果 使用在 from 表单中 使用 multipart/form-data 上传文件的时候， 后台 无法使用
	// request.getParameter("title") 得到普通信息
	// javaweb 提供了 三种 方法 来 获取 文件 数据 ， Io写法， 在这个写法中 可以 获取 普通 数据

	// 步骤一： 判定 这个 from 是否 是 上传 文件 类型
	// 步骤二： from提交到 request 中 的数据， 把这个 request 变为 一个 文件 类型的 容器 List<FileItem>
	// 步骤三： 通过 循环 获取 这个 list 集合中的数据 ， 比如 ， from 中提交了 8 个 数据 ， 那么 这个 list 中 就 存储了
	// 8 个数据
	// 步骤四： 吗每一次 循环 都要 判定 这个 数据 是 普通数据 ， 还是 文件 数据 // String name ="=tilte" //
	// name.equals("oldMoney") 相当于name=="oldMoney"
	// 步骤五:如果是普通数据，那么每一次循环，就可以得到对应的name与value,可以通过if(name.eques("tilte"))
	// 来判定网页中传过来的值具体是什么
	// 步骤 六 ： 如果在 循环的时候 判定这个 数据 是 文件 类型， 那么 就 获取 这个 文件的 名字，
	// 步骤七： 把 这个 文件 的 后缀 获取 ， 拼接一个 新的文件 名 ，
	// 步骤 八 ： 就可以 通过 IO流 的方式 把这个图片 写入到 项目的某一 文件中，
	// 步骤九 ： 在数据 库 中 存储 ， 需要拼接这个 项目 路径 与 文件 路径 ， 因为 这个图片是在 项目中的文件存储
	// 这个项目 又在 tomcat 服务器中， 所以 拼接的时候http://localhost:8080/tgou/goods/ + 文件名

	private Goods uploadImage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Goods goods = new Goods();

		// 判断上传表单是否为multipart/form-data类型
		if (ServletFileUpload.isMultipartContent(request)) {
			try {

				// 1. 创建DiskFileItemFactory对象，设置缓冲区大小和临时文件目录
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// 2. 创建ServletFileUpload对象，并设置上传文件的大小限制。
				ServletFileUpload sfu = new ServletFileUpload(factory);
				sfu.setSizeMax(10 * 1024 * 1024);// 以byte为单位 不能超过10M 1024byte =
													// 1kb 1024kb=1M 1024M = 1G
				sfu.setHeaderEncoding("utf-8");

				// 3.调用ServletFileUpload.parseRequest方法解析request对象，得到一个保存了所有上传内容的List对象。
				List<FileItem> fileItemList = sfu.parseRequest(request);

				Iterator<FileItem> fileItems = fileItemList.iterator();

				// 4. 遍历list，每迭代一个FileItem对象，调用其isFormField方法判断是否是上传文件
				while (fileItems.hasNext()) {
					FileItem fileItem = fileItems.next();
					// 7 普通表单元素
					if (fileItem.isFormField()) {

						String name = fileItem.getFieldName();// name属性值
						String value = fileItem.getString("utf-8");// name对应的value值
						// 6 、 通过 name 与 网页中的 name值 对比，
						if (name.equals("title")) {
							goods.setTitle(value);
						}
						if (name.equals("oldMoney")) {
							goods.setOldMoney(value);
						}
						if (name.equals("money")) {
							goods.setMoney(value);
						}
						if (name.equals("goodNumber")) {
							goods.setGoodNumber(value);
						}
						if (name.equals("goodIntroduction")) {
							goods.setGoodIntroduction(value);
						}
						if (name.equals("sellNumber")) {
							goods.setSellNumber(value);
						}

					}
					// 8 判定文件 类型
					else {

						System.out.println("eee" + fileItems.hasNext());
						String fileName = fileItem.getName();// 文件名称
						System.out.println("原文件名：" + fileName);// Koala.jpg

						String suffix = fileName.substring(fileName.lastIndexOf('.'));
						System.out.println("扩展名：" + suffix);// .jpg

						// 新文件名（唯一）
						String newFileName = new Date().getTime() + suffix;
						System.out.println("新文件名：" + newFileName);// image\1478509873038.jpg

						// 5. 调用FileItem的write()方法，写入文件
						File file = new File(request.getServletContext().getRealPath("/goods") + "\\" + newFileName);
						fileItem.write(file);
						// 9 / 拼接 图片 路径 保存到 数据库中
						String url = "http://localhost:8080/shopping/goods/" + newFileName;
						goods.setImages(url);
					}
				}
				return goods;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
