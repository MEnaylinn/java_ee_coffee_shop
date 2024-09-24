<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="jakarta.tags.functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<c:import url="common/header.html" />
<meta charset="ISO-8859-1">
<title>Edit Products</title>
</head>
<body>
	<div class="container-sm">
		<c:if test="${fn:contains(user.role,'ROLE_ADMIN')}">
			<c:import url="common/nav.jsp" />
		</c:if>
		<h2>Product update</h2>
		<form action="item" method="post">
			<input type="hidden" name="mode" value="UPDATE_ITEM">
			<input type="hidden" name="id" value="${item.id}">

			<div class="mb-3 ">
				<label for="name" class="form-label ">Name</label> 
				<input type="text" name="name" value="${item.name}" class="form-control" id="name"
					required="required">
			</div>

			<div class="mb-3">
				<label for="category" class="form-label">Category</label> <input
					type=text name="category" value="${item.category}" class="form-control" id="category"
					required="required">
			</div>

			<div class="mb-3">
				<label for="price" class="form-label">Price</label> <input
					type="number" name="price" value="${item.price}" class="form-control" id="price"
					aria-describedby="price">
			</div>
			

				<c:choose>
    <c:when test="${item.status eq true}">
        <div class="mb-3">
            <input type="checkbox" name="active" value="true"
                class="form-check-input" id="active"> 
            <label class="form-check-label" for="active">Active</label>
        </div>
    </c:when>
    <c:otherwise>
        <div class="mb-3">
            <input type="checkbox" name="active" value="false" 
                class="form-check-input" id="active"> 
            <label class="form-check-label" for="active">Active</label>
        </div>
    </c:otherwise>
</c:choose>
				
			<button type="submit" class="btn btn-primary">Save</button>
		</form>
		
	</div>


	<c:import url="common/footer.html" />
</body>
</html>