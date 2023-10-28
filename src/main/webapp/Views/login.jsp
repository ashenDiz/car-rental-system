<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
<style type="text/css">
.login_img {
	background: url("${pageContext.request.contextPath}/Images/Design/car_login.webp");
	background-size: 100%;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px;
	overflow: hidden;
}
</style>
</head>
<body style="background-color: #00285F;">
	<div class="container-fluid">
		<section class="vh-100" >
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col col-xl-10">
						<div class="card" style="border-radius: 1rem;">
							<div class="row g-0">
								<div class="col-md-6 col-lg-5 d-none d-md-block login_img">
								</div>
								<div class="col-md-6 col-lg-7 d-flex align-items-center">
									<div class="card-body p-4 p-lg-5 text-black">

										<form action="${pageContext.request.contextPath}/VehicleController" method="post">

											<div class="d-flex align-items-center mb-3 pb-1">
												<i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
												<span class="h1 fw-bold mb-0"><img alt=""
													src="${pageContext.request.contextPath}/Images/Design/logo.png"
													style="width: 150px; height: 150px"></span>
											</div>

											<h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign
												into your account</h5>
											<c:if test="${message == 'failed'}">
												<div class="alert alert-danger" role="alert">
  													Invalid User name or password!
												</div>
											</c:if>
											<div class="form-group mb-4">
												<label for="sel1">Select User Type:</label> <select
													class="form-control" id="sel1" name="user_type">
													<option value="admin">Admin</option>
													<option selected="selected" value="user">User</option>
												</select>
											</div>

											<div class="form-outline mb-4">
												<input type="email" id="form2Example17"
													class="form-control form-control-lg" name="user_name" /> <label
													class="form-label" for="form2Example17">Email
													address</label>
											</div>

											<div class="form-outline mb-4">
												<input type="password" id="form2Example27"
													class="form-control form-control-lg" name="password" /> <label
													class="form-label" for="form2Example27">Password</label>
											</div>

											<div class="pt-1 mb-4">
												<button class="btn btn-dark btn-lg btn-block" type="submit" name="action" value="all">Login</button>
											</div>

											<a class="small text-muted" href="#!">Forgot password?</a>
											<p class="mb-5 pb-lg-2" style="color: #393f81;">
												Don't have an account? <a href="#!" style="color: #393f81;">Register
													here</a>
											</p>
											<a href="#!" class="small text-muted">Terms of use.</a> <a
												href="#!" class="small text-muted">Privacy policy</a>
										</form>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</body>
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
</html>