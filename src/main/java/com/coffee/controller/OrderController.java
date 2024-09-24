package com.coffee.controller;

import jakarta.annotation.Resource;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.coffee.model.Cart;
import com.coffee.model.Item;
import com.coffee.model.ItemDAO;
import com.coffee.model.Order;
import com.coffee.model.OrderDAO;
import com.coffee.model.User;
import com.coffee.model.Voucher;
import com.hostmdy.crypto.PasswordEncoder;

/**
 * Servlet implementation class OrderController
 */
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/coffeeshop")
	private DataSource dataSource;
	private OrderDAO orderDAO;
	private ItemDAO itemDAO;
	
	@Override
	public void init() throws ServletException {
		orderDAO=new OrderDAO(dataSource);
		itemDAO=new ItemDAO(dataSource);
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String mode = request.getParameter("mode");
		System.out.println(mode);
		if (mode == null) {
			response.sendRedirect("cart.jsp");
		} else {
			switch (mode) {
			case "PAID_ORDER": {
				addOrder(request, response);
				break;
			}case "ORDER_LIST": {
				orderList(request, response);
				break;
			}

			default:
//				showCart(request, response);
				break;
			}
		}
	}

	private void orderList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		List<Item> allItem=itemDAO.getAllItems();


		User user = (User) session.getAttribute("user");	
		List<Order> orders=new ArrayList<>();
		if(user.getRole().equals("ROLE_ADMIN")) {
			orders=orderDAO.getAllOrder();
			System.out.println(orders.size());
		}else{
			orders=orderDAO.getOrderbyUser(user.getId());
			System.out.println(orders.size());

		}
		
		session.setAttribute("allItem", allItem);
		session.setAttribute("orders", orders);
		response.sendRedirect("order.jsp");
	}

	private void addOrder(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		List<Cart> carts = (List<Cart>) session.getAttribute("cartList");

		User user = (User) session.getAttribute("user");
		System.out.println("user :"+user);
		Long orderid = (long) 1;
		List<Voucher> voucher = orderDAO.getAllVoucher();
		if (voucher != null) {
			orderid = (orderid + voucher.size());
		}

		
		Double total=0.0;

		if (carts != null) {
			for (Cart order : carts) {
				total=(total+(order.getOrderqt()*order.getPrice()));
				Order od =new Order(
				orderid,
				user.getId(),
				order.getId(),
				order.getOrderqt(),
				order.getPrice(),
				true,
				LocalDate.now());
				orderDAO.addOrders(od);
			}
			Voucher vou = new Voucher(orderid, total, LocalDate.now());
			orderDAO.addVoucher(vou);

			session.removeAttribute("cartList");
			response.sendRedirect("item");
		} else {
			response.sendRedirect("cart");
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
