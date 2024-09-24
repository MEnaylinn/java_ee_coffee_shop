<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.html" />
<meta charset="ISO-8859-1">
<title>Sing UP Form</title>
</head>
<body>
	<div class="container-sm">
		<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
			<c:import url="common/nav.jsp" />
		</c:if>
		<h2>Sign Up Here</h2>
		<form action="user" method="post">
			<input type="hidden" name="mode" value="REGISTER">

			<div class="mb-3 ">
				<label for="name" class="form-label ">Name</label> <input
					type="text" name="name" class="form-control" id="name"
					required="required">
			</div>

			<div class="mb-3">
				<label for="phone" class="form-label">Phone</label> <input
					type="phone" name="phone" class="form-control" id="phone"
					required="required">
			</div>

			<div class="mb-3">
				<label for="email" class="form-label">Email</label> <input
					type="email" name="email" class="form-control" id="email"
					aria-describedby="email">
			</div>
			<div class="mb-3">
				<label for="address" class="form-label">Address</label> <input
					type="text" name="address" class="form-control" id="address"
					aria-describedby="address">
			</div>

			<div class="mb-3">
				<label for="password" class="form-label">Passwrod</label> <input
					type="password" name="password" class="form-control" id="password"
					aria-describedby="password">
			</div>


			<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
				<div class="mb-3">
					<input type="checkbox" name="active" value="true"
						class="form-check-input" id="active"> <label
						class="form-check-label" for="active">Active</label>
				</div>

				<div class="mb-3">
					<input type="checkbox" name="admin" value="true"
						class="form-check-input" id="admin"> <label
						class="form-check-label" for="admin">Admin</label>
				</div>
				<div class="mb-3">
					<input type="checkbox" name="cashier" value="true"
						class="form-check-input" id="cashier"> <label
						class="form-check-label" for="cashier">Cashier</label>
				</div>
			</c:if>



			<button type="submit" class="btn btn-primary">Register</button>
		</form>
		<c:if test="${!fn:contains(user.role,'ROLE_ADMIN')}">
		Already have an account? <a href="login">Login here</a>
		</c:if>
	</div>


	<c:import url="common/footer.html" />
</body>
</html>