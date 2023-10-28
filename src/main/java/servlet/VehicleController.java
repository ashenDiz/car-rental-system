package servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.User;
import model.Vehicle;
import service.LoginService;
import service.LoginServiceImpl;
import service.VehicleService;
import service.VehicleServiceImpl;

/**
 * Servlet implementation class VehicleController
 */
@WebServlet("/VehicleController")
@MultipartConfig
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	VehicleService vehicleService = null;
	LoginService loginService = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VehicleController() {
		super();
		// TODO Auto-generated constructor stub
		vehicleService = new VehicleServiceImpl();
		loginService = new LoginServiceImpl();
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
		case "rent":
			redirectRent(request,response);
			break;
		case "delete":
			deleteAdd(request,response);
			break;
		case "add":
			redirectAdd(request,response);
			break;
		case "logged":
			redirectIndex(request,response);
			break;
		default:
			String user_name = request.getParameter("user_name");
			String password = request.getParameter("password");
			String user_type = request.getParameter("user_type");
			User user = loginService.validate(user_name, password, user_type);
			if (user.isLogged()) {
				request.setAttribute("user", user);
				List<Vehicle> list = vehicleService.getAllVehicle();
				request.setAttribute("vehicles", list);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}else {
				request.setAttribute("message", "failed");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/login.jsp");
				dispatcher.forward(request, response);
			}
		}
	}

	public void redirectIndex(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id =Integer.parseInt(request.getParameter("user_id"));
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		List<Vehicle> list = vehicleService.getAllVehicle();
		request.setAttribute("vehicles", list);
		if(list.isEmpty()) {
			request.setAttribute("available", "Vehicles not available");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
	}

	private void redirectAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/addVehicle.jsp");
		dispatcher.forward(request, response);
		
	}

	private void deleteAdd(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(request.getParameter("id"));
		
		if(vehicleService.deleteVehicle(id)) {
			request.setAttribute("message", "success");
			request.setAttribute("data", "Deleted Successfully!");
			redirectIndex(request, response);
		}else {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Delete Failed!");
			redirectIndex(request, response);
		}
	}
		

	public void redirectRent(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(request.getParameter("add_id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		Vehicle vehicle = vehicleService.getVehicleById(id);
		request.setAttribute("vehicle", vehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/addReservation.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("action");
		switch (value) {
		case "Post": {
			addVehicle(request,response);
			break;
		}
		case "Update":
			updateVeicle(request,response);
			break;
		case "all":
			doGet(request, response);
			break;
		}
	}
	private void updateVeicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String vehicle_type = request.getParameter("type").trim();
		String m_year = request.getParameter("m_year").trim();
		String reg_year = request.getParameter("reg_year").trim();
		String fuel_type = request.getParameter("fuel_type").trim();
		String capacity = request.getParameter("capacity").trim();
		Double mileage =Double.valueOf(request.getParameter("mileage").trim());
		Double price = Double.valueOf(request.getParameter("price").trim());
		Part file = request.getPart("img"); 
		String imgFileName = file.getSubmittedFileName();
		int id =Integer.parseInt(request.getParameter("id"));
		String img_path = request.getParameter("img_path");
		String model = request.getParameter("model");
		//validation
		if(mileage < 0) { //validate mileage
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid Mileage!");
			redirectUpdate(request, response);
			
		}else if(price < 0) { //validate price
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid Price!");
			redirectUpdate(request, response);
		}else {
			Vehicle vehicle = null;
			if(imgFileName.isEmpty()) {
				vehicle = new Vehicle(vehicle_type, m_year, reg_year, fuel_type, capacity, mileage, price, img_path,model);
				vehicle.setId(id);
			}else {
				String uploadPath ="C:/Users/Danuja Wijerathna/eclipse-workspace/rent_a_car/src/main/webapp/Images/"+imgFileName; //define image storage path

				vehicle = new Vehicle(vehicle_type, m_year, reg_year, fuel_type, capacity, mileage, price, imgFileName,model);
				vehicle.setId(id);
				
				try {
					FileOutputStream fos = new FileOutputStream(uploadPath); 
					InputStream is = file.getInputStream();

					byte[] data=new byte[is.available()];
					is.read(data);
					fos.write(data);
					fos.close();

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if(vehicleService.updateVehicle(vehicle)) {
				request.setAttribute("message", "success");
				request.setAttribute("data", "Update Successfully!");
				redirectIndex(request, response);
			}else {
				request.setAttribute("message", "failed");
				request.setAttribute("data", "Update Failed!");
				redirectIndex(request, response);
			}
		}

	}

	private void addVehicle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		int user_id =Integer.parseInt(request.getParameter("user_id"));

		String vehicle_type = request.getParameter("type").trim();
		String m_year = request.getParameter("m_year").trim();
		String reg_year = request.getParameter("reg_year").trim();
		String fuel_type = request.getParameter("fuel_type").trim();
		String capacity = request.getParameter("capacity").trim();
		Double mileage =Double.valueOf(request.getParameter("mileage").trim());
		Double price = Double.valueOf(request.getParameter("price").trim());
		Part file = request.getPart("img"); 
		String imgFileName = file.getSubmittedFileName();
		String model = request.getParameter("model");
		//validation
		if(mileage < 0) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid Mileage!");
			redirectAdd(request, response);
			
		}else if(price < 0) {
			request.setAttribute("message", "failed");
			request.setAttribute("data", "Invalid Price!");
			redirectAdd(request, response);
		}else {
			String uploadPath ="C:/Users/Danuja Wijerathna/eclipse-workspace/rent_a_car/src/main/webapp/Images/"+imgFileName;
			
			Vehicle vehicle = new Vehicle(vehicle_type, m_year, reg_year, fuel_type, capacity, mileage, price, imgFileName,model);
			
			try {
				FileOutputStream fos = new FileOutputStream(uploadPath); 
				InputStream is = file.getInputStream();
				
				byte[] data=new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			request.setAttribute("action", "logged");
			if(vehicleService.addVehicle(vehicle)) {
				request.setAttribute("message", "success");
				request.setAttribute("data", "Inserted Successfully.");
				redirectIndex(request, response);
				redirectIndex(request, response);
			}else {
				request.setAttribute("message", "failed");
				request.setAttribute("data", "Insert Failed.");
				redirectIndex(request, response);
				redirectIndex(request, response);
			}
		}
	}

	private void redirectUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		User user = loginService.getUserById(user_id);
		request.setAttribute("user", user);
		Vehicle vehicle = vehicleService.getVehicleById(id);
		request.setAttribute("vehicle", vehicle);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Views/updateVehicle.jsp");
		dispatcher.forward(request, response);
	}
}

