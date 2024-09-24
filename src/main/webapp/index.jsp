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
	<!-- Responsive navbar-->
	<c:import url="common/nav.jsp" />
	<!-- Page content-->
	<div class="container">

		<%-- <%
		List<Integer> counts = new ArrayList();
		for (int i = 1; i <= 20; i++) {
			counts.add(i);
		}
		pageContext.setAttribute("counts", counts);
		%> --%>
		<div class="card-header my-3">
			<a href="item?mode=SHOW_ITEM" class="btn btn-primary"> All Products</a> 
			<a href="item?mode=SHOW_DRINK" class="btn btn-primary">Drinks</a>
			<a href="item?mode=SHOW_FOOD" class="btn btn-primary">Foods</a>
			
			<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
				<a href="productform.jsp" class="btn btn-primary">Add Product</a>
			</c:if>

		</div>
		<div class="row">
			<c:forEach var="item" items="${allItem}">
				<c:url var="addcartURL" value="cart">
					<c:param name="mode" value="ADD_TO_CART"></c:param>
					<c:param name="id" value="${item.id}"></c:param>
				</c:url>

				<c:url var="edititemURL" value="item">
					<c:param name="mode" value="EDIT_ITEM"></c:param>
					<c:param name="id" value="${item.id}"></c:param>
				</c:url>
				<div class="col-md-3">
					<div class="card w-100" style="width: 18rem;">
						<div>
							<c:out value="${fn:toLowerCase(Americano)}"></c:out>
						</div>

						<!--  <p>Lowercase version : ${fn:toLowerCase(item.name)}</p>-->
						<img class="card-img-top"
							src="assets/${fn:toLowerCase(item.name)}.jpg"
							alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title">${item.name}</h5>
							<h5 class="price">${item.price }MMK</h5>
							<a href="${addcartURL }" class="btn btn-primary">Add to Cart</a>
							<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
								<a href="${edititemURL}" class="btn btn-primary">Edit</a>

							</c:if>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>





	</div>
	<c:import url="common/footer.html" />

	<script>
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
</body>
</html>


