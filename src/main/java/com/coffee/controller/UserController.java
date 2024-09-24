package com.coffee.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.sql.DataSource;

import com.coffee.model.ItemDAO;
import com.coffee.model.User;
import com.coffee.model.UserDAO;
import com.hostmdy.crypto.PasswordEncoder;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/coffeeshop")
	private DataSource dataSource;

	private UserDAO userDAO;

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);

	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode= request.getParameter("mode");
		if(mode==null) {
			mode="REGISTER_FORM";
		}
		
		switch (mode) {
		case "REGISTER_FORM": {
			registerForm(request,response);
			break;
		}
		case "REGISTER": {
			register(request,response);
			break;
		}
		
		default:
			registerForm(request,response);
			break;
		}
	}
	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String password=request.getParameter("password");
		
		Boolean admin=Boolean.parseBoolean(request.getParameter("admin"));
		Boolean cashier=Boolean.parseBoolean(request.getParameter("cashier"));

		String role="";
		if(admin) {
			role="ROLE_ADMIN";
		}else if(cashier) {
			role="ROLE_CASHIER";
		}else {
			role="ROLE_USER";
		}
		String encryptedPassword="";
		try {
			 encryptedPassword=PasswordEncoder.encode(password);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user=new User(name, address, phone, email, role, encryptedPassword, true);
		int rowEffected=userDAO.register(user);
		
		PrintWriter out=response.getWriter();
		if(rowEffected>0) {
//			out.print("<h1>Registered </h1>");
			response.sendRedirect("signin.jsp");
			
		}else {
			response.sendRedirect("signup.jsp");
		}
		
	}
	private void registerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher=request.getRequestDispatcher("signup.jsp");
		dispatcher.forward(request, response);
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
