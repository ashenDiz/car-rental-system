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
			<a class="nav-item nav-link" href="VehicleController?action=logged&user_id=<%=user.getId()%>">Home</a>
			<a class="nav-item nav-link active" href="ReservasionController?action=all&user_id=<%=user.getId()%>" >Reservations</a>
			<a class="nav-item nav-link" href="PaymentController?action=all&user_id=<%=user.getId()%>">Payments</a>
		  </div>
		</div>
	  </nav>
	  <section class="vh-100" >
		<div class="container py-5 h-100">
			<div
				class="row d-flex justify-content-center align-items-center h-100">
				<div class="col col-xl-10">
					<div class="card" style="border-radius: 1rem;">
						<div class="row g-0">
							<div class="col-md-6 col-lg-5 d-none d-md-block" style="background-color: aliceblue;border-radius: 10px;">
								<div class="login_img" style="width: 100%;height: 65%;">

								</div>
								<h5 class="card-title" style="text-align: center;">${vehicle.model}</h5>
								<div class="row" style="margin-left: 10px;">
									<div class="col-6">
										<p class="card-text">Type: ${vehicle.vehicle_type}</p>
										<p class="card-text">Manufacture Year: ${vehicle.m_year}</p>
										<p class="card-text">Registered Year: ${vehicle.reg_year}</p>
										
									</div>
									<div class="col-6">
										<p class="card-text">Capacity: ${vehicle.capacity}</p>
										<p class="card-text">Mileage: ${vehicle.mileage}</p>
										<p class="card-text">Fuel Type: ${vehicle.fuel_type}</p>
									</div>
									<p class="card-text" style="font-size:20px;color: orange;text-align: center;">Price /day: ${vehicle.price}</p>
									
									
								  </div>
							</div>
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">
									<c:if test="${message == 'failed'}">
										<div class="alert alert-danger" role="alert">${data}</div>
									</c:if>
									<h1 style="text-align: center;">Update Reservation</h1>
									<form
										action="${pageContext.request.contextPath}/ReservasionController"
										method="post" enctype="multipart/form-data">
										<input type="hidden" value="${reservation.id}" name="id">
										<div class="form-group">
											<label for="user_id">User ID</label> <br> <input
												type="text" name="user_id" value="${reservation.user_id}"
												class="form-control" readonly>
										</div>
										<div class="form-group">
											<label for="address">Input Address</label> <input type="text"
												value="${reservation.address}" name="address"
												class="form-control" required="required">
										</div>
										<div class="form-group">
											<label for="rent_date">Input Rental Date </label> <input
												type="date" name="rent_date"
												value="${reservation.rent_date}" class="form-control"
												required="required">
										</div>
										<div class="form-group">
											<label for="duration">Input Duration </label> <input
												type="number" name="duration"
												value="${reservation.duration}" class="form-control"
												required="required">
										</div>
										<div class="form-group">
											<label for="add_id">Vehicle ID </label> <input type="number"
												value="${reservation.add_id}" name="add_id"
												class="form-control" readonly>
										</div>
										<br>
										<div class="form-group">
											<center>
												<input type="submit" value="Update" name="action"
													class="btn btn-success g-lg-3">
											</center>
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