package com.coffee.controller;


import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import com.coffee.model.Cart;
import com.coffee.model.Item;
import com.coffee.model.ItemDAO;
import com.mysql.cj.Session;

/**
 * Servlet implementation class CartController
 */
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/coffeeshop")
	private DataSource dataSource;

	private ItemDAO itemDAO;

	@Override
	public void init() throws ServletException {
		itemDAO = new ItemDAO(dataSource);

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
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
		String mode = request.getParameter("mode");
		System.out.println(mode);
		if (mode == null) {
			response.sendRedirect("item");
		}
		switch (mode) {
		case "LOAD": {
			showCart(request, response);
			break;
		}
		case "ADD_TO_CART": {
			addToCart(request, response);
			break;
		}
		case "ITEM_CANCEL": {
			itemCancel(request, response);
			showCart(request, response);
			break;
		}
		case "PLUS_ITEM": {
			plusItem(request, response);
			showCart(request, response);
			break;
		}
		case "MINUS_ITEM": {
			minusItem(request, response);
			showCart(request, response);
			break;
		}
		case "ORDER_CANCEL": {
			cancelOrder(request, response);
			showCart(request, response);
			break;
		}
		default:
			showCart(request, response);
			break;
		}
	}

	private void cancelOrder(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
//		request.setAttribute("mode","SHOW_ITEM");
		session.removeAttribute("cartList");
		RequestDispatcher dispatcher = request.getRequestDispatcher("item");
		dispatcher.forward(request, response);
	}

	private void minusItem(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		HttpSession session = request.getSession();
		System.out.println(id);
		List<Cart> carts = (List<Cart>) session.getAttribute("cartList");
		List<Cart> afterminus = new ArrayList<>();

		for (Cart cart : carts) {
			if (cart.getId() == id && cart.getOrderqt() > 1) {
				cart.setOrderqt(cart.getOrderqt() - 1);
				afterminus.add(cart);
			} else {
				afterminus.add(cart);
			}
		}

		session.removeAttribute("cartList");
		session.setAttribute("cartList", afterminus);
	}

	private void plusItem(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		HttpSession session = request.getSession();
		System.out.println(id);
		List<Cart> carts = (List<Cart>) session.getAttribute("cartList");
		List<Cart> afterPlus = new ArrayList<>();

		for (Cart cart : carts) {
			if (cart.getId() == id) {
				cart.setOrderqt(cart.getOrderqt() + 1);
				afterPlus.add(cart);
			} else {
				afterPlus.add(cart);
			}
		}

		session.removeAttribute("cartList");
		session.setAttribute("cartList", afterPlus);
	}

	private void itemCancel(HttpServletRequest request, HttpServletResponse response) {
		Long id = Long.parseLong(request.getParameter("id"));
		HttpSession session = request.getSession();
		System.out.println(id);
		List<Cart> carts = (List<Cart>) session.getAttribute("cartList");
		List<Cart> afterCancel = new ArrayList<>();

		for (Cart cart : carts) {
			if (cart.getId() != id) {
				System.out.println("remain :" + cart);

				afterCancel.add(cart);
			}
		}

		session.removeAttribute("cartList");
		session.setAttribute("cartList", afterCancel);
	}

	private void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String mode = request.getParameter("mode");

		Item item = itemDAO.getbyID(id);

		System.out.println(item);
		Cart cart = new Cart();
		cart.setId(item.getId());
		cart.setName(item.getName());
		cart.setCategory(item.getCategory());
		cart.setPrice(item.getPrice());
		cart.setStatus(item.getStatus());
		cart.setOrderqt(1);

		System.out.println("cart is : " + cart);

		HttpSession session = request.getSession();
		ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
		ArrayList<Cart> carts = new ArrayList<>();
		PrintWriter out = response.getWriter();

//		if (cartList == null) {
//			carts.add(cart);
//			session.setAttribute("cartList", carts);
//			response.sendRedirect("item");
//		} else {
//			boolean cond = true;
//			carts.addAll(cartList);
//			for (Cart c : cartList) {
//				if (c.getId() == id) {
//					int qty = c.getOrderqt();
//					qty++;
//					cart.setOrderqt(qty);
//					carts.remove(cartList.indexOf(c));
//					carts.add(cart);
//					cond = false;
//					break;
//				}
//			}
//			if (cond) {
//				carts.add(cart);
//			}
//			session.setAttribute("cartList", carts);
//			response.sendRedirect("item");
//		
		if (cartList == null) {
			carts.add(cart);
			session.setAttribute("cartList", carts);
			response.sendRedirect("item");
		} else {
			boolean cond = true;
			for (Cart c : cartList) {
				if (c.getId() == id) {
					int qty = c.getOrderqt();
					qty++;
					cart.setOrderqt(qty);
					cartList.set(cartList.indexOf(c), cart);
					cond = false;
					break;
				}
			}
			if (cond) {
				cartList.add(cart);
			}
			session.setAttribute("cartList", cartList);
			response.sendRedirect("item?mode="+mode);
		}
//	RequestDispatcher dispatcher=request.getRequestDispatcher("sample.jsp");
//	dispatcher.forward(request, response);

	}

	public void showCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<Cart> carts = (List<Cart>) session.getAttribute("cartList");

		Double netTotal = 0.0;
		if (carts==null){
			response.sendRedirect("item");
		}else {
			for (Cart cart : carts) {
				System.out.println("showcart is:" + cart);
				netTotal = netTotal + (cart.getPrice() * cart.getOrderqt());

			}
			session.setAttribute("cartList", carts);
			request.setAttribute("netTotal", netTotal);

			RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
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

}
