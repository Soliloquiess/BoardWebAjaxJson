package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import board.dto.UserDto;
import board.service.LoginService;
import board.service.LoginServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginService service = LoginServiceImpl.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String userEmail = request.getParameter("userEmail");
		String userPassword = request.getParameter("userPassword");

		UserDto userDto = service.login(userEmail, userPassword);
		
		if( userDto != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("userDto", userDto);

			response.setContentType("text/html; charset=utf-8");
			
			// Before Learn JSON
			//response.getWriter().write("success");
			
			
			// After Learn JSON
			Gson gson = new Gson();
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "success");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
			System.out.println("LoginServlet - login success");
		}else {
			response.setContentType("text/html; charset=utf-8");
			
			// Before Learn JSON
			// response.getWriter().write("fail");
			
			// After Learn JSON
			Gson gson = new Gson();
			
			JsonObject jsonObject = new JsonObject();
			jsonObject.addProperty("result", "fail");
			
			String jsonStr = gson.toJson(jsonObject);
			response.getWriter().write(jsonStr);
			
			System.out.println("LoginServlet - login failed");
		}
	}
}
