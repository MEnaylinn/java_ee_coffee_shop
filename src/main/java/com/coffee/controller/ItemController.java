package com.coffee.controller;

import jakarta.annotation.Resource;
import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
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
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.coffee.model.Cart;
import com.coffee.model.Item;
import com.coffee.model.ItemDAO;
import com.coffee.model.User;
import com.hostmdy.crypto.PasswordEncoder;
import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

/**
 * Servlet implementation class ItemController
 */
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	@Resource(name = "jdbc/coffeeshop")
	private DataSource dataSource;

	private ItemDAO itemDAO;

	@Override
	public void init() throws ServletException {
		itemDAO = new ItemDAO(dataSource);

	}

	public ItemController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("signin.jsp");
		}else {
			String mode = request.getParameter("mode");
			if (mode == null) { 
				mode="SHOW_ITEM";
			}
			
			switch (mode) {
			case "SHOW_ITEM": {
				showItem(request, response);
				break;
			}case "SHOW_FOOD": {
				showFood(request, response);
				break;
			}case "SHOW_DRINK": {
				showDrink(request, response);
				break;
			}case "ADD_ITEM": {
				addItem(request, response);
				break;
			}case "EDIT_ITEM": {
				editItem(request, response);
				break;
			}
			case "LOAD": {
				showCart(request, response);
				break;
			}
			case "UPDATE_ITEM": {
				updateItem(request, response);
				break;
			}
			default:
				showItem(request, response);
				break;
			}
		}

		
	}

	private void editItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String mode = request.getParameter("mode");

		Item item = itemDAO.getbyID(id);
		
		HttpSession session=request.getSession();
		System.out.print("item is "+item);
		session.setAttribute("item", item);
		response.sendRedirect("editproduct.jsp");

	}

	private void addItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		Double price=Double.parseDouble(request.getParameter("price"));
		Boolean status=Boolean.parseBoolean(request.getParameter("active"));
		
		Item item=new Item(name, category, price, status);
		int rowEffected=itemDAO.addItem(item);
		
		if(rowEffected>0) {
//			out.print("<h1>Registered </h1>");
			response.sendRedirect("index.jsp");
			
		}		
	}private void updateItem(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Long id = Long.parseLong(request.getParameter("id"));
		String mode = request.getParameter("mode");

		Item item = itemDAO.getbyID(id);
		
		String name=request.getParameter("name");
		String category=request.getParameter("category");
		Double price=Double.parseDouble(request.getParameter("price"));
		Boolean status=Boolean.parseBoolean(request.getParameter("active"));
		
		Item updateItem=new Item(item.getId(), name, category, price, status);
		int rowEffected=itemDAO.updateItem(updateItem);
		
		if(rowEffected>0) {
			showItem(request, response);
			
		}		
	}

	private void showDrink(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Item> allItem=itemDAO.getDrinkItem();
		
		
		
		request.setAttribute("allItem", allItem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);		
	}

	private void showFood(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Item> allItem=itemDAO.getFoodItem();
		
		
		
		request.setAttribute("allItem", allItem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);		
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));

		Item cart = new Item();
		cart.setId(id);
//		cart.setOrderqt(1);

		System.out.println("cart is : " + cart);
//		System.out.println("cart is : " + cart.getOrderqt());

		HttpSession session = request.getSession();
		ArrayList<Item> cart_list = (ArrayList<Item>) session.getAttribute("cart-list");
		System.out.println("cartList is: " + cart_list);
		ArrayList<Item> carts = new ArrayList<>();

		PrintWriter out = response.getWriter();
		if (cart_list== null) {
			carts.add(cart);
			request.setAttribute("cart-list", carts);
			out.println("created");
//			response.sendRedirect("index.jsp");
		} else {
//		cartList.add(cart);
//		request.setAttribute("cartList", cartList);
			out.println("exited");

//			response.sendRedirect("index.jsp");


		}
//	RequestDispatcher dispatcher=request.getRequestDispatcher("sample.jsp");
//	dispatcher.forward(request, response);

	}

//	private void addToCart(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//		
//		Long id = Long.parseLong(request.getParameter("id"));
////		String name=request.getParameter("name");
////		String category=request.getParameter("category");
////		Double price=Double.parseDouble(request.getParameter("price"));
////		Boolean status=Boolean.parseBoolean(request.getParameter("status"));
////		Integer orderqt =1;
//
//		Cart cart=new Cart();
//		cart.setId(id);
//		cart.setOrderqt(1);
//		
//		
//		HttpSession session=request.getSession();
//		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
//		System.out.println("session cartList Is :"+cartList);
//		ArrayList<Cart> carts = new ArrayList<>();
//
//		PrintWriter out = response.getWriter();
//		System.out.println("added to cart is "+cart);
//		
//		if(cartList==null) {
//		carts.add(cart);
//		}else {
//			cartList=carts;
//			carts.add(cart);
//		}
//		
//		System.out.println(carts);
//		request.setAttribute("cartList", carts);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("sample.jsp");
//		dispatcher.forward(request, response);
//		
//	}

	public void showCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Cart> carts = (List<Cart>) request.getAttribute("cartList");
		System.out.println("CartList is : " + carts);
		List<Item> items = itemDAO.getAllItems();
		

		request.setAttribute("cartList", items);
		int itemQty=0;

		if(!carts.isEmpty()) {
			for(Cart c:carts) {
				itemQty=itemQty+c.getOrderqt();
			}
			request.setAttribute("quantity", itemQty);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
			dispatcher.forward(request, response);

		}else {
			request.setAttribute("quantity", itemQty);
			RequestDispatcher dispatcher = request.getRequestDispatcher("item");
			dispatcher.forward(request, response);

		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void showItem(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Item> allItem=itemDAO.getAllItems();
		
		
		
		request.setAttribute("allItem", allItem);

		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}


}
