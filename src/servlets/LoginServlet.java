package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entitys.Staff;

import services.LoginImp;
import services.RegisterDaoImp;
import utils.CommonUtil;

/**
 * login接口
 * 返回 true/false
 * url: http://localhost:8080/Xing/LoginServlet?number=201310644&password=112233
 * @author WangJinXing
 *
 */
public class LoginServlet extends HttpServlet {

	private LoginImp loginDao = null;
	private List<Staff> lists = null;
	private String mpassword = null;
	private Boolean flag;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String number = request.getParameter("number");
		String password = request.getParameter("password");
		Map<String, Object> data = new HashMap<String, Object>();
		loginDao = new LoginImp();
		String sql = "select password from login_staff where number=?";
		lists = loginDao.getAll(sql, number);
		System.out.println(lists);
		if(lists.size()==0){
			//没有该账号  创建注册
			RegisterDaoImp registerDaoImp = new RegisterDaoImp();
			registerDaoImp.Savedata(number, password);
			data.put("result", "注册成功");
		}else{
			//账号存在  验证密码
			for(int i=0;i<lists.size();i++){
				String mpassword = lists.get(i).getPassword();
				if(password.equals(mpassword)){
					data.put("result", "登录成功");
					break;
				}else{
					data.put("result", "登录失败");
				}
			}
		}
		System.out.println(data);
		CommonUtil.renderJson(response, data);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
