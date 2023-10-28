package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Reservation;
import model.User;
import model.Vehicle;
import service.LoginService;
import service.LoginServiceImpl;
import service.ReservasionService;
import service.ReservasionServiceImpl;
import service.VehicleService;
import service.VehicleServiceImpl;

/**
 * Servlet implementation class ReservasionController
 */
@WebServlet("/ReservasionController")
@MultipartConfig
public class ReservasionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ReservasionService reservasionService = null;
    VehicleService vehicleService = null;
    LoginService loginService = null;
    VehicleController vController;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservasionController() {
        super();
        // TODO Auto-generated constructor stub
        reservasionService = new ReservasionServiceImpl();
        vehicleService = new VehicleServiceImpl();
        loginService = new LoginServiceImpl();
        vController= new VehicleController();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("action");
		switch (value) {
		case "edit": 
			redirectUpdate(request,response);
			break;
		case "delete":
			deleteAdd(request,response);
			break;
		default:
			int user_id =Integer.parseInt(request.getParameter("user_id"));
			User user = loginService.getUserById(user_id);
			request.setAttribute("user", user);
			System.out.println("came to all reservations");
			List<Reservation> list = reservasionService.getAllReservation();
			request.setAttribute("reservations", list);
			if(list.isEmpty()) {
				request.setAttribute("message", "Reservations not available");
			}else {
				request.setAttribute("message", "success");
			}
			System.out.println(list.size());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/allReservation.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void deleteAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(request.getParameter("id"));
		if(reservasionService.deleteReservation(id)) {
			request.setAttribute("message", "success");
			request.setAttribute("data", "Deleted Successfully!");
			vController.redirectIndex(request, response);
		}else {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Delete Failed!");
			vController.redirectIndex(request, response);
		}
	}

	private void redirectUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(request.getParameter("id"));
		int user_id =Integer.parseInt(request.getParameter("user_id"));
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		Reservation reservation = reservasionService.getReservationById(id);
		Vehicle vehicle = vehicleService.getVehicleById(reservation.getAdd_id());
		request.setAttribute("vehicle", vehicle);
		request.setAttribute("reservation", reservation);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/updateReservation.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("action value "+request.getParameter("action"));
		System.out.println("userr id value "+request.getParameter("user_id"));
		System.out.println("add id value "+request.getParameter("add_id"));
		System.out.println("address value "+request.getParameter("address"));
		String value = request.getParameter("action");
		switch (value) {
		case "Request":
			addReservation(request,response);
			break;
		case "Update":
			updateReservation(request,response);
			break;
		}
	}

	private void updateReservation(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub

		int id = Integer.parseInt(request.getParameter("id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int add_id = Integer.parseInt(request.getParameter("add_id"));
		String address = request.getParameter("address");
		int duration = Integer.parseInt(request.getParameter("duration"));
		String rent_date = request.getParameter("rent_date");

		//validation
		if(duration < 1) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid Duration!");
			redirectUpdate(request, response);
		}else {
			Reservation reservation = new Reservation(user_id, address, duration, rent_date, add_id, false);
			reservation.setId(id);
			if (reservasionService.updateReservation(reservation)) {
				request.setAttribute("message", "success");
				request.setAttribute("data", "Updated Successfully!");
				vController.redirectIndex(request, response);
			}else {
				request.setAttribute("message", "failed");
				request.setAttribute("data", "Update Failed!");
				vController.redirectIndex(request, response);
			}
		}

		
	}

	private void addReservation(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		int add_id = Integer.parseInt(request.getParameter("add_id"));
		String address = request.getParameter("address");
		int duration = Integer.parseInt(request.getParameter("duration"));
		String rent_date = request.getParameter("rent_date");
		
		//validation
		if(duration < 1) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid Duration!");
			vController.redirectRent(request, response);
		}else {
			Reservation reservation = new Reservation(user_id, address, duration, rent_date, add_id, false);
			if (reservasionService.addReservation(reservation)) {
				request.setAttribute("message", "success");
				request.setAttribute("data", "Inserted Successfully!");
				vController.redirectIndex(request, response);
			}else {
				request.setAttribute("message", "failed");
				request.setAttribute("data", "Insert Failed!");
				vController.redirectIndex(request, response);
			}
		}

		
	}

}
