<%@page import="org.apache.jasper.tagplugins.jstl.core.When"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container" method="post">
		<a class="navbar-brand" href="#"><img id="logo"
			src="assets/logo.jpg" alt="logo" /> Classic Coffee</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ms-auto mb-2 mb-lg-0">

				<c:url var="cartURL" value="cart">
					<c:param name="mode" value="LOAD"></c:param>
				</c:url>
				<c:url var="orderURL" value="order">
					<c:param name="mode" value="ORDER_LIST"></c:param>
				</c:url>
					<a class="navbar-brand " href="${cartURL }"><img id="logo"
					src="assets/cart.png" alt="logo" /> 
						<c:if test="${cartList.size() ne null }">
							<btn class="btn btn-primary">${cartList.size()}</btn> 
						</c:if>
						<c:if test="${cartList.size() eq null }">
							<btn class="btn btn-primary">0</btn> 
						</c:if>
					</a>
				
				
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
					role="button" data-bs-toggle="dropdown" aria-expanded="false">${user.name }</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="navbarDropdown">

						<li><a class="dropdown-item" href="item">Home</a></li>

						<li><a class="dropdown-item" href="${orderURL }">Order</a></li>
						<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
							<li><a class="dropdown-item" href="signup.jsp">Create
									Account</a></li>

						</c:if>

						<li><a class="dropdown-item" href="login?mode=LOGOUT">Logout</a></li>

					</ul></li>
			</ul>
		</div>
	</div>
</nav>