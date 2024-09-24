<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@page import="com.coffee.model.Cart"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Set"%>
<%@page import="java.awt.PageAttributes"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<!DOCTYPE html>
<html lang="en">
<head>
<c:import url="common/header.html" />
<title>Classic Coffee</title>

<!-- CDN-->
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/school.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/styles.css" rel="stylesheet" />
<link href="css/custom.css" rel="stylesheet" />
</head>

<body>
	<c:import url="common/nav.jsp"></c:import>


	<%-- <c:forEach var="cart" items="${cartList}">
		<h1>${cartList }</h1>
	</c:forEach>   --%>

	<%
	/* 	ArrayList<Cart> cartList = (ArrayList<Cart>) session.getAttribute("cartList");
	 */
	%>
	<%-- <c:if test="${cartList == null }">
		<h1>cartList is null</h1>
		<h1>current id is "${id }</h1>
	</c:if> --%>

	<c:url var="cancelOrder" value="cart">
		<c:param name="mode" value="ORDER_CANCEL"></c:param>
	</c:url>
	<c:url var="paidOrder" value="order">
		<c:param name="mode" value="PAID_ORDER"></c:param>
		<c:param name="userid" value="${user.id }"></c:param>
		
	</c:url>

	<div class="container-md">
		<div class="card-header my-3">
			<div class="d-flex py-3">
				<h3>Total : ${netTotal} MMK</h3>
			</div>
			<div class="container-md">
				<a href="${paidOrder }" class="btn btn-primary"> Paid </a> 
				<a href="${cancelOrder }" class="btn btn-primary bg-danger">Cancel
					Order</a>
			</div>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<td>No.</td>
					<td>Name</td>
					<td>Category</td>
					<td>Quality</td>
					<td>Price</td>
					<td>Action</td>
				</tr>
			</thead>
			<tbody>
				<c:set var="serial" value="0" scope="page"></c:set>
				<c:forEach var="cart" items="${cartList}">
					<c:set var="serial" value="${serial+1}" scope="page"></c:set>
					<c:url var="ordercancelURL" value="cart">
						<c:param name="mode" value="ITEM_CANCEL"></c:param>
						<c:param name="id" value="${cart.id}"></c:param>
					</c:url>
					<c:url var="plusItem" value="cart">
						<c:param name="mode" value="PLUS_ITEM"></c:param>
						<c:param name="id" value="${cart.id}"></c:param>
					</c:url>
					<c:url var="minusItem" value="cart">
						<c:param name="mode" value="MINUS_ITEM"></c:param>
						<c:param name="id" value="${cart.id}"></c:param>
					</c:url>
					<tr>
						<td>${serial}</td>
						<td>${cart.name}</td>
						<td>${cart.category}</td>
						<td width="200px" space="10px">
							<form action="" method="post" class="form-inline">
								<input type="hidden" name="id" value="1" class="form-input">
								<div class="from-group d-flex justify-content-between">
									<a class="btn btn-primary" href="${minusItem}">-</a> <input
										type="text" name="quantity" class="form-control"
										value="${cart.orderqt }" readonly> <a
										class="btn btn-primary" href="${plusItem }">+</a>
								</div>
							</form>
						</td>
						<td>${cart.price*cart.orderqt}</td>
						<td><a class="btn btn-danger" href="${ordercancelURL}">Cancel</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<c:import url="common/footer.html" />

	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>
</html>


