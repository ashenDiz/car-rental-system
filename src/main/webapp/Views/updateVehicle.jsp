<%@ page import = "model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
<style type="text/css">
.login_img {
	background: url("${pageContext.request.contextPath}/Images/${vehicle.img_path}");
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px;
	overflow: hidden;
}
</style>
</head>
<body style="background-color: #00285F;" class="container">
<%
	User user = (User)request.getAttribute("user");
%>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="#"><img alt="" src="${pageContext.request.contextPath}/Images/Design/logo.png" style="width: 40px;height: 40px;margin-left: 10px"></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		  <span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		  <div class="navbar-nav">
				<a class="nav-item nav-link active" href="VehicleController?action=logged&user_id=<%=user.getId()%>">Home</a>
		  </div>
		</div>
	  </nav>
	  <section class="vh-100" >
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<c:if test="${message == 'failed'}">
						<div class="alert alert-danger" role="alert">${data}</div>
					</c:if>
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-5 d-none d-md-block login_img">
							</div>
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">
									<h1 style="text-align: center;">Update Vehicle</h1>
									<form action="${pageContext.request.contextPath}/VehicleController" method="post"
										enctype="multipart/form-data">
										<input type="hidden" value="<%=user.getId()%>" name="user_id">
										<input type="hidden" value="${vehicle.id}" name="id">
										<input type="hidden" value="${vehicle.img_path}" name="img_path">
										<div class="form-group">
											<label for="img">Select Image</label> <br>
											<input type="file" name="img" class="form-control-file">
										</div>
										<div class="form-group">
											<label for="model">Enter Model </label> 
											<input type="text"
												name="model" value="${vehicle.model}" class="form-control" required="required">
										</div>
										<div class="form-group">
											<label for="type">Select Vehicle type</label> 
											<select name="type" class="form-control">
												<option value="${vehicle.vehicle_type}">${vehicle.vehicle_type}</option>
												<option value="car">Car</option>
												<option value="motor cycle">Motor Cycle</option>
												<option value="van">Van</option>
												<option value="bus">Bus</option>
												<option value="lorry">Lorry</option>
											</select>
										</div>
										<div class="form-group">
											<label for="m_year">Select manufacture year </label> 
											<input type="number" name="m_year" value="${vehicle.m_year}" class="form-control" required="required"> 
										</div>
										<div class="form-group">
											<label for="reg_year">Select register year </label> 
											<input type="year" name="reg_year" value="${vehicle.reg_year}" class="form-control" required="required"> 
										</div>
										<div class="form-group">
											<label
											for="fuel_type">Select Fuel type </label> 
										<select name="fuel_type" class="form-control">
											<option value="${vehicle.fuel_type}">${vehicle.fuel_type}</option>
											<option value="petrol">Petrol</option>
											<option value="diesel">Diesel</option>
											<option value="electric">Electric</option>
											<option value="hybrid">hybrid</option>
										</select> 
										</div>
										<div class="form-group">
											<label for="capacity">Select Capacity </label> 
											<input type="text"
												name="capacity" class="form-control" value="${vehicle.capacity}" required="required">
										</div>
										<div class="form-group">
											<label for="mileage">Select Mileage
											</label> 
											<input type="number" name="mileage" class="form-control" value="${vehicle.mileage}" required="required">
										</div>
										<div class="form-group">
											<label for="price">Enter
												price </label> <input type="number" name="price" class="form-control"  value="${vehicle.price}" required="required"> 
										</div> <br>
										<div class="form-group">
											<center><input
												type="submit" value="Update" name="action" class="btn btn-primary mb-2"></center>
										</div>				
									</form>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>