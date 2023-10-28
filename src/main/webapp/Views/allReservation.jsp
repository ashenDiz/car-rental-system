<%@ page import = "model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Reservations</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<Style>
.content_card{
	background-size: 100%;
	background-position: center;
	background-repeat: no-repeat;
	border-radius: 10px;
}
.card_box{
    background-color: rgb(255, 255, 255);
    border-radius: 10px;
    height: 900px;
}
.card_box:first-child{
	width: 70%;
	overflow-y:auto; 
	overflow-x:hidden; 
}
.card_box:last-child{
	width: 29.6%;
	
}

.row_box{
	width:100%;
	display: flex;
	justify-content: space-between;
	margin-top: 5px;
	margin-left: 1px;
	margin-right: 1px;
}
</Style>

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


  <div class="row_box" style="background-color: aliceblue">
    <div class="card_box">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th>Id</th>
					<th>user ID</th>
					<th>address</th>
					<th>duration</th>
					<th>rent date</th>
					<th>vehicle id</th>
					<th>payment status</th>
					<th>Delete</th>
					<th>Update</th>
	
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reservations}" var="reservation">
					<tr>
						<td>${reservation.id}</td>
						<td>${reservation.user_id}</td>
						<td>${reservation.address}</td>
						<td>${reservation.duration}</td>
						<td>${reservation.rent_date}</td>
						<td>${reservation.add_id}</td>
						<c:if test="${reservation.payment_completed == 'false'}"><td>
							<form action="${pageContext.request.contextPath}/PaymentController" method="get">
								<input type="hidden" value="${reservation.id}" name="res_id">
								<input type="hidden" value="<%=user.getId()%>" name="user_id">
								<input type="submit" class="btn btn-danger" value="Pay" name="action" style="width: 100px;">
							</form>
						</td></c:if>
						<c:if test="${reservation.payment_completed == 'true'}"><td><input type="text" class="btn btn-outline-success" style="width: 100px;" value="Success" disabled></td></c:if>
						<form action="${pageContext.request.contextPath}/ReservasionController" method="get">
							<input type="hidden" value="${reservation.id}" name="id">
							<input type="hidden" value="<%=user.getId()%>" name="user_id">
							<td><button type="submit" name="action" class="btn btn-secondary" value="delete">Delete</button></td>
							<td> <button type="submit" name="action" class="btn btn-warning"  value="edit">Edit</button></td>
						</form>
	
					</tr>
	
				</c:forEach>
			</tbody>
		</table>
    </div>
    <div class="card_box" style="overflow: hidden;">
      <div class="card" style="width: 95%;margin: 10px;border-radius: 10px;">
        <div class="card-body">
          <h5 class="card-title"><%=user.getName()%></h5>
          <p class="card-text">Role :<%=user.getType()%></p>
          <p class="card-text">ID No :<%=user.getId_no()%>V</p>
          <a href="#" class="btn btn-primary">Visit Profile</a>
                    <a href="${pageContext.request.contextPath}/Views/login.jsp" class="btn btn-secondary" style="float: right;">Logout</a>
        </div>
      </div>
      <div class="slider" style="width: 95%;margin: 10px;">
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
          <div class="carousel-inner">
            <div class="carousel-item active">
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_2.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_1.jpg" alt="Second slide">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_3.jpg" alt="Third slide">
            </div>
          </div>
          <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
          </a>
          <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
          </a>
        </div>
      </div>
    </div>

  </div>
</body>
</html>