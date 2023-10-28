<%@ page import = "model.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
	crossorigin="anonymous">
	<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3"
	crossorigin="anonymous"></script>

<Style>
.content_card{
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

<%if (user.getType().contains("admin")){ %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#"> <img alt="" src="${pageContext.request.contextPath}/Images/Design/logo.png" style="width: 40px;height: 40px;margin-left: 10px"> </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="VehicleController?action=logged&user_id=<%=user.getId()%>">Home</a>
    </div>
  </div>
</nav>
  <div class="row_box" style="background-color: aliceblue">
    <div class="card_box">
    	<c:if test="${message == 'success'}">
    		<div class="alert alert-success" role="alert">
  				${data}
			</div>
    	</c:if>
    	<c:if test="${message == 'failed'}">
    		<div class="alert alert-danger" role="alert">
  				${data}
			</div>
    	</c:if>
      <c:forEach items="${vehicles}" var="vehicle">
        <div class="row">
          <div class="col-sm-12">
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-8 content_card" style="background: url('${pageContext.request.contextPath}/Images/${vehicle.img_path}');background-size:cover;background-repeat: no-repeat;">
                    
                  </div>
                  <div class="col-4">
                    <h5 class="card-title">${vehicle.model}</h5>
                    <p class="card-text">Type: ${vehicle.vehicle_type}</p>
                    <p class="card-text">Manufacture Year: ${vehicle.m_year}</p>
                    <p class="card-text">Registered Year: ${vehicle.reg_year}</p>
                    <p class="card-text">Fuel Type: ${vehicle.fuel_type}</p>
                    <p class="card-text">Capacity: ${vehicle.capacity}</p>
                    <p class="card-text">Mileage: ${vehicle.mileage}</p>
                    <p class="card-text">Price /day: ${vehicle.price}</p>
                    <form action="${pageContext.request.contextPath}/VehicleController" method="get">
                      <input type="hidden" value="${vehicle.id}" name="id">
                      <input type="hidden" value="<%=user.getId()%>" name="user_id">
                      <button type="submit" class="btn btn-danger" name="action" value="delete">Delete</button>
                      <button type="submit" class="btn btn-warning" name="action" value="edit">Edit</button>
                    </form>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
    <div class="card_box" style="overflow: hidden;">
      <form action="${pageContext.request.contextPath}/VehicleController">
      	<input type="hidden" value="<%=user.getId()%>" name="user_id">
        <center> <button type="submit" class="btn btn-primary btn-lg btn-block" name="action" value="add" style="width: 90%;margin: 10px;">Add New Vehicle</button></center>
      </form>
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
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_1.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_2.jpg" alt="Second slide">
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
<%}else{ %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#"><img alt="" src="${pageContext.request.contextPath}/Images/Design/logo.png" style="width: 40px;height: 40px;margin-left: 10px"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    <div class="navbar-nav">
      <a class="nav-item nav-link active" href="VehicleController?action=logged&user_id=<%=user.getId()%>">Home</a>
      <a class="nav-item nav-link" href="ReservasionController?action=all&user_id=<%=user.getId()%>" >Reservations</a>
      <a class="nav-item nav-link" href="PaymentController?action=all&user_id=<%=user.getId()%>">Payments</a>
    </div>
  </div>
</nav>

  <div class="row_box" style="background-color: aliceblue">
    <div class="card_box">
    <c:if test="${message == 'success'}">
    		<div class="alert alert-success" role="alert">
  				${data}
			</div>
    	</c:if>
    	<c:if test="${message == 'failed'}">
    		<div class="alert alert-danger" role="alert">
  				${data}
			</div>
    	</c:if>
      <c:forEach items="${vehicles}" var="vehicle">
        <div class="row">
          <div class="col-sm-12">
            <div class="card">
              <div class="card-body">
                <div class="row">
                  <div class="col-8 content_card" style="background: url('${pageContext.request.contextPath}/Images/${vehicle.img_path}');background-size:cover;background-repeat: no-repeat;">
                    
                  </div>
                  <div class="col-4">
                    <h5 class="card-title">${vehicle.model}</h5>
                    <p class="card-text">Type: ${vehicle.vehicle_type}</p>
                    <p class="card-text">Manufacture Year: ${vehicle.m_year}</p>
                    <p class="card-text">Registered Year: ${vehicle.reg_year}</p>
                    <p class="card-text">Fuel Type: ${vehicle.fuel_type}</p>
                    <p class="card-text">Capacity: ${vehicle.capacity}</p>
                    <p class="card-text">Mileage: ${vehicle.mileage}</p>
                    <p class="card-text">Price /day: ${vehicle.price}</p>
                    <form action="${pageContext.request.contextPath}/VehicleController" method="get">
                      <input type="hidden" value="${vehicle.id}" name="add_id">
                      <input type="hidden" value=<%=user.getId()%> name="user_id">
                      <button type="submit" class="btn btn-success" name="action" value="rent">Rent</button>
                    </form>
                  </div>
                </div>
                
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
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
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_1.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
              <img class="d-block w-100" src="${pageContext.request.contextPath}/Images/Design/slider_2.jpg" alt="Second slide">
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
<%} %>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.min.js" integrity="sha384-IDwe1+LCz02ROU9k972gdyvl+AESN10+x7tBKgc9I5HFtuNz0wWnPclzo6p9vxnk" crossorigin="anonymous"></script>

</html>