<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.html" />
<meta charset="ISO-8859-1">
<title>Sing in Form</title>
</head>
<body>

	<section class="vh-100 bg-image"
		style="background-image: url('assets/cafetheme.jpg');" >

		<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
			<c:import url="common/nav.jsp" />
		</c:if>
		<div class="mask d-flex align-items-center h-100 gradient-custom-3">
			<div class="container h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-9 col-lg-7 col-xl-6">
						<div class="card" style="border-radius: 15px;">
							<div class="card-body p-5">
								<h2 class="text-uppercase text-center mb-5">Create an
									account</h2>

								<form action="user" method="post">
									<input type="hidden" name="mode" value="REGISTER">
									<div class="form-outline mb-4">
										<input type="text" id="name" name="name"
											class="form-control form-control-lg" /> <label
											class="form-label" for="name">Your Name</label>
									</div>

									<div class="form-outline mb-4">
										<input type="text" id="phone" name="phone"
											class="form-control form-control-lg" /> <label
											class="form-label" for="phone">Phone</label>
									</div>

									<div class="form-outline mb-4">
										<input type="email" id="email" name="email"
											class="form-control form-control-lg" /> <label
											class="form-label" for="email">Your Email</label>
									</div>

									<div class="form-outline mb-4">
										<input type="text" id="address" name="address"
											class="form-control form-control-lg" /> <label
											class="form-label" for="address">Address</label>
									</div>

									<div class="form-outline mb-4">
										<input type="password" id="password" name="password"
											class="form-control form-control-lg" /> <label
											class="form-label" for="password">Password</label>
									</div>

									<!--  <div class="form-outline mb-4">
                  <input type="password" id="repassword" name="repassword" class="form-control form-control-lg" />
                  <label class="form-label" for="repassword">Repeat your password</label>
                </div> -->

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

									<div class="d-flex justify-content-center">
										<button type="submit" class="btn btn-primary">Register</button>
									</div>

									<p class="text-center text-muted mt-5 mb-0">
										Have already an account? <a href="signin.jsp"
											class="fw-bold text-body"><u>Login here</u></a>
									</p>

								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<c:import url="common/footer.html" />
</body>
</html>