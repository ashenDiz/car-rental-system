<%@ page import = "model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Payment</title>
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
	background: url("${pageContext.request.contextPath}/Images/Design/payment_logo.jpg");
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
			<a class="nav-item nav-link" href="ReservasionController?action=all&user_id=<%=user.getId()%>" >Reservations</a>
			<a class="nav-item nav-link active" href="PaymentController?action=all&user_id=<%=user.getId()%>">Payments</a>
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
							<c:if test="${message == 'failed'}">
    							<div class="alert alert-danger" role="alert">
  									${data}
								</div>
    						</c:if>
							<div class="col-md-6 col-lg-5 d-none d-md-block login_img" style="background-color:black;border-radius:10px;">
								
							</div>
							<div class="col-md-6 col-lg-7 d-flex align-items-center">
								<div class="card-body p-4 p-lg-5 text-black">
									<h1 style="text-align: center;">Update Payment</h1>
									<form action="${pageContext.request.contextPath}/PaymentController" method="post">
										<input type="hidden" value="${payment.id}" name="id">
										<input type="hidden" value="${res_id}" name="res_id">
										<input type="hidden" value="<%=user.getId()%>" name="user_id">
										<div class="form-group">
											<label for="card_no">Card No</label> <br>
											<input type="text" name="card_no" value="${payment.card_no}" class="form-control" required="required">
										</div>
										<div class="form-group">
											<label for="card_name">Card Name</label> 
											<input type="text" value="${payment.card_name}" name="card_name" class="form-control" required="required"> 
										</div>
										<div class="form-group">
											<label for="exp_date">Exp Date </label> 
											<input type="date" name="exp_date" value="${payment.exp_date}" class="form-control" required="required">
										</div>
										<div class="form-group">
											<label for="cvv">CVV </label> 
											<input type="number" name="cvv" value="${payment.cvv}" class="form-control" required="required">
										</div>
										 <br>
										<div class="form-group">
											<center><input type="submit" value="Update" name="action" class="btn btn-success g-lg-3"></center>
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