package servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Payment;
import model.User;
import service.LoginService;
import service.LoginServiceImpl;
import service.PaymentService;
import service.PaymentServiceImpl;
import service.ReservasionService;
import service.ReservasionServiceImpl;
import service.VehicleService;
import service.VehicleServiceImpl;

/**
 * Servlet implementation class PaymentController
 */
@WebServlet("/PaymentController")
@MultipartConfig
public class PaymentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      PaymentService paymentService = null;
      ReservasionService reservasionService = null;
      VehicleService vehicleService = null;
      LoginService loginService = null;
      VehicleController vController = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentController() {
        super();
        // TODO Auto-generated constructor stub
        paymentService = new PaymentServiceImpl();
        reservasionService = new ReservasionServiceImpl();
        vehicleService = new VehicleServiceImpl();
        loginService = new LoginServiceImpl();
        vController = new VehicleController();
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
			deletePayment(request,response);
			break;
		case "Pay":
			redirectAddPayment(request,response);
			break;
		default:
			int user_id =Integer.parseInt(request.getParameter("user_id"));
			User user = loginService.getUserById(user_id);
			request.setAttribute("user", user);
			List<Payment> list = paymentService.getAllPayment();
			request.setAttribute("Payments", list);
			if(list.isEmpty()) {
				request.setAttribute("message", "Reservations not available");
			}else {
				request.setAttribute("message", "success");
			}
			System.out.println("all payments sizes :"+list.size());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/allPayments.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void redirectAddPayment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int res_id =Integer.parseInt(request.getParameter("res_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		request.setAttribute("res_id", res_id);
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/addPayment.jsp");
		dispatcher.forward(request, response);
	}

	private void deletePayment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(request.getParameter("id"));
		if(paymentService.deletePayment(id)) {
			int user_id =Integer.parseInt(request.getParameter("user_id"));
			User user = loginService.getUserById(user_id);
			request.setAttribute("user", user);
			request.setAttribute("message", "success");
			request.setAttribute("data", "Deleted Successfully!");
			vController.redirectIndex(request, response);
		}else {
			int user_id =Integer.parseInt(request.getParameter("user_id"));
			User user = loginService.getUserById(user_id);
			request.setAttribute("user", user);
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Delete Failed!");
			vController.redirectIndex(request, response);
		}
		
	}

	private void redirectUpdate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int res_id =Integer.parseInt(request.getParameter("res_id"));
		int id =Integer.parseInt(request.getParameter("id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		Payment payment = paymentService.getPaymentById(id);
		request.setAttribute("payment", payment);
		request.setAttribute("res_id", res_id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/updatePayment.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("action");
		switch (value) {
		case "pay": 
			addPayment(request,response);
			break;
		case "Update":
			updatePayment(request,response);
			break;
		default:
			
		}
	}

	private void updatePayment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int res_id = Integer.parseInt(request.getParameter("res_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String card_no = request.getParameter("card_no");
		int id = Integer.parseInt(request.getParameter("id"));
		String card_name = request.getParameter("card_name");
		int cvv =Integer.parseInt(request.getParameter("cvv"));
		String exp_date = request.getParameter("exp_date");
		
		if(card_no.length() != 16) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid card number!");
			redirectUpdate(request, response);
		}else if(String.valueOf(cvv).length() != 3) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid CVV!");
			redirectUpdate(request, response);
		}else {
			
			Payment payment = new Payment(card_no, card_name, cvv, exp_date, res_id);
			payment.setId(id);

			if(paymentService.updatePayment(payment)) {
				User user = loginService.getUserById(user_id);
				request.setAttribute("user", user);
				request.setAttribute("message", "success");
				request.setAttribute("data", "Updated Successfully!");
				vController.redirectIndex(request, response);
			}else {
				User user = loginService.getUserById(user_id);
				request.setAttribute("user", user);
				request.setAttribute("message", "failed");
				request.setAttribute("data", "Update Failed!");
				vController.redirectIndex(request, response);
			}
		}

	}

	private void addPayment(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int res_id = Integer.parseInt(request.getParameter("res_id"));
		String card_no = request.getParameter("card_no");
		String card_name = request.getParameter("card_name");
		int cvv =Integer.parseInt(request.getParameter("cvv"));
		String exp_date = request.getParameter("exp_date");
		
		//validation
		
		if(card_no.length() != 16) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid card number!");
			redirectAddPayment(request, response);
		}else if(String.valueOf(cvv).length() != 3) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid CVV!");
			redirectAddPayment(request, response);
		}else {
			Payment payment = new Payment(card_no, card_name, cvv, exp_date, res_id);
			
			
			if(paymentService.addPayment(payment)) {
				if(reservasionService.updatePaymentStatus(res_id)) {
					int user_id =Integer.parseInt(request.getParameter("user_id"));
					User user = loginService.getUserById(user_id);
					request.setAttribute("user", user);
					request.setAttribute("message", "success");
					request.setAttribute("data", "Payment Successfully!");
					vController.redirectIndex(request, response);
				}else {
					int user_id =Integer.parseInt(request.getParameter("user_id"));
					User user = loginService.getUserById(user_id);
					request.setAttribute("user", user);
					request.setAttribute("message", "failed");
					request.setAttribute("data", "Payment Failed!");
					vController.redirectIndex(request, response);
				}
				
			}
		}

	}

}
