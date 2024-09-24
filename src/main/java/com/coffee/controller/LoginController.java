package com.coffee.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.sql.DataSource;

import com.coffee.model.UserDAO;
import com.hostmdy.crypto.PasswordEncoder;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
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
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mode= request.getParameter("mode");
		if(mode==null) {
			mode="LOGIN_FORM";
		}
		switch (mode) {
		case "LOGIN_FORM":
			
			loginForm(request, response);
			break;
		case "LOGIN": {
			try {
				login(request,response);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		case "LOGOUT": 
			try {
				logout(request,response);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
	
		default:
			loginForm(request, response);
			break;
		}
	}
	private void logout(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		session.invalidate();
		
		loginForm(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		// TODO Auto-generated method stub
		String phone=request.getParameter("phone");
		String password=request.getParameter("password");
		
		boolean valid=userDAO.isUserValid(phone, password);
		PrintWriter out=response.getWriter();
		
		if(valid) {
			HttpSession session=request.getSession();
			session.setAttribute("user", userDAO.getUserByPhone(phone));
			response.sendRedirect("item");
		}else {
			response.sendRedirect("login");
		}
	}

	private void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher=request.getRequestDispatcher("signin.jsp");
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
