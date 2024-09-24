<%@page import="com.coffee.model.UserDAO"%>
<%@page import="com.coffee.model.ItemDAO"%>
<%@page import="com.coffee.model.OrderDAO"%>
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

	<div class="container mt-5">

		<table id="example" class="table table-striped" style="width: 100%">
			<thead>
				<tr>
					<td>No.</td>
					<td>Date</td>
					<td>Name</td>
					<td>Category</td>
					<td>Quality</td>
					<td>Price</td>
				</tr>
			</thead>
			<tbody>
				<c:set var="serial" value="0" scope="page"></c:set>
				<c:set var="total" value="0" scope="page"></c:set>
				<c:forEach var="order" items="${orders}">

					<c:forEach var="item" items="${allItem}">
						<c:if test="${item.id eq order.itemid }">
							<c:set var="name" value="${item.name }"></c:set>
							<c:set var="category" value="${item.category }"></c:set>
						</c:if>
					</c:forEach>
					<c:set var="serial" value="${serial+1}" scope="page"></c:set>
					<c:set var="total" value="${total+(order.price*order.quantity)}"
						scope="page"></c:set>
					<tr>
						<td>${serial}</td>
						<td>${order.date}</td>
						<td>${name}</td>
						<td>${category }</td>
						<td>${order.quantity }</td>
						<td>${order.quantity*order.price}</td>
						
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="card-header my-3">
			<div class="d-flex py-3">
				<h3>Total : ${total} MMK</h3>
			</div>
		</div>
	</div>

	<c:import url="common/footer.html" />

	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="js/scripts.js"></script>

	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>
</html>


