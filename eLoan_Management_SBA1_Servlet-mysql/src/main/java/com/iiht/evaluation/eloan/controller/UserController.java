package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ConnectionDao;
import com.iiht.evaluation.eloan.dto.UserInfo;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.Statement;





@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
private ConnectionDao connDao;
	
	public void setConnDao(ConnectionDao connDao) {
		this.connDao = connDao;
	}
	public void init(ServletConfig config) {
		String jdbcURL = config.getServletContext().getInitParameter("jdbcUrl");
		String jdbcUsername = config.getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = config.getServletContext().getInitParameter("jdbcPassword");
		System.out.println(jdbcURL + jdbcUsername + jdbcPassword);
		this.connDao = new ConnectionDao(jdbcURL, jdbcUsername, jdbcPassword);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		String viewName = "";
		try {
			switch (action) {
			case "registernewuser":
				viewName=registernewuser(request,response);
				break;
			case "validate":
				viewName=validate(request,response);
				break;
			case "placeloan":
				viewName=placeloan(request,response);
				break;
			case "application1":
				viewName=application1(request,response);
				break;
			case "editLoanProcess"  :
				viewName=editLoanProcess(request,response);
				break;
			case "registeruser":
				viewName=registerUser(request,response);
				break;
			case "register":
				viewName = register(request, response);
				break;
			case "application":
				viewName = application(request, response);
				break;
			case "trackloan":
				viewName = trackloan(request, response);
				break;
			case "editloan":
				viewName = editloan(request, response);
				break;	
			case  "displaystatus" :
				viewName=displaystatus(request,response);
				break;
			default : viewName = "notfound.jsp"; break;	
			}
		} catch (Exception ex) {
			
			throw new ServletException(ex.getMessage());
		}
			RequestDispatcher dispatch = 
					request.getRequestDispatcher(viewName);
			dispatch.forward(request, response);
	}
	
	
	private String validate(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		/* write the code to validate the user */

		HttpSession session = request.getSession();
		String AdminUserName = request.getParameter("adminID");
		String AdminPassword = request.getParameter("adminpassword");
		System.out.println(AdminUserName);
	 if(request.getParameter("type").toString().equalsIgnoreCase("customer"))
		{
			String userID = request.getParameter("loginID");
			String password = request.getParameter("password");
			
			HashMap<String, String> cred_map = connDao.getUserCredData();
			UserInfo userInfo = connDao.getUserInfo(userID);
			
			if(cred_map.keySet().contains(userID) && cred_map.get(userID).equals(password)) 
			{
				session.setAttribute("username", userID);
				session.setAttribute("userInfo", userInfo);

				return "userhome1.jsp";
			}
			
			else 
			{
				return	"notfound.jsp"; 
			}
		}
			
		else { 
			boolean val = AdminUserName.equalsIgnoreCase("admin");
			System.out.println(val);
				if (AdminUserName != null && AdminPassword != null  && AdminUserName.equalsIgnoreCase("admin")  && AdminPassword.equalsIgnoreCase("admin")){
				
					return "adminhome1.jsp";
				}
				else 
				{
					return	"notfound.jsp"; 
				}
			}
		
		} 
		
	
	

	
	private String placeloan(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	/* write the code to place the loan information */
		
		LoanInfo loanInfo = new LoanInfo();
		
		loanInfo.setUserName(request.getParameter("Fullname"));
		loanInfo.setPurpose(request.getParameter("loanname"));
		loanInfo.setAmtrequest(request.getParameter("loanamount"));
		loanInfo.setDoa(request.getParameter("loanapplicationdate"));
		loanInfo.setBstructure(request.getParameter("BusinessStruture"));
		loanInfo.setBindicator(request.getParameter("BillingIndicator"));
		loanInfo.settindicator(request.getParameter("TaxIndicator"));
		loanInfo.setAddress(request.getParameter("address"));
		loanInfo.setMobile(request.getParameter("mobile"));
		loanInfo.setEmail(request.getParameter("email"));
		
				
		String view ="";
		
		boolean resultFlag = connDao.addLoanDetails(loanInfo);
		
		LoanInfo loanInfoEntered = connDao.getLoanData(loanInfo.getMobile());
		request.setAttribute("loanDetails", loanInfoEntered);
		
		if(resultFlag)
		{
			request.setAttribute("state", "submitted");
		}
		else
		{
			request.setAttribute("state", "notSubmitted");
		}
		
		
		view = "application.jsp";
		return view;
	}
	
	private String application1(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write the code to display the loan application page */

		return null;
	}
	private String editLoanProcess(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		/* write the code to edit the loan info */
			
		HttpSession session= request.getSession();
		
		
		
		String applno= request.getParameter("Appnum");
		LoanInfo LoanData = new LoanInfo();
		
		
		LoanData.setAmtrequest(request.getParameter("loanamount"));
		LoanData.setApplno(request.getParameter("applicationNum"));
		LoanData.setUserName(request.getParameter("Fullname"));
		LoanData.setBstructure(request.getParameter("BusinessStruture"));
		LoanData.setBindicator(request.getParameter("BillingIndicator"));
		LoanData.settindicator(request.getParameter("TaxIndicator"));
		LoanData.setAddress(request.getParameter("address"));
		LoanData.setMobile(request.getParameter("mobile"));
		LoanData.setEmail(request.getParameter("email"));
		LoanData.setStatus(request.getParameter("LoanStatus"));
		LoanData.setPurpose(request.getParameter("loanname"));
		LoanData.setDoa(request.getParameter("loanapplicationdate"));
					
		
		
		request.setAttribute("LoanInfo", LoanData);
		
		connDao.UpdateLoanInfo(LoanData,applno);
		
	
		return "editloan.jsp";
	}
	private String registerUser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to redirect page to read the user details */
		request.setAttribute("newUser", "newUser");
		return "newuserui.jsp";
	}
	private String registernewuser(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code to create the new user account read from user 
		   and return to index page */
		
		HttpSession session= request.getSession();
		UserInfo newUserInfo = new UserInfo();
		newUserInfo.setUsername(request.getParameter("UserName"));
		newUserInfo.setFirstName(request.getParameter("FirstName"));
		newUserInfo.setLastName(request.getParameter("LastName"));
		newUserInfo.setDob(request.getParameter("DateofBirth"));
		newUserInfo.setMobile(request.getParameter("Mobile"));
		newUserInfo.setEmail(request.getParameter("Email"));
		newUserInfo.setCity(request.getParameter("City"));
		newUserInfo.setState(request.getParameter("State"));
		newUserInfo.setCountry(request.getParameter("Country"));
		newUserInfo.setPincode(request.getParameter("Pincode"));
		newUserInfo.setAddress(request.getParameter("address"));
		
		User newUser = new User();
		newUser.setUsername(request.getParameter("UserName"));
		newUser.setPassword(request.getParameter("Password"));
		
		
		boolean userInfoFlag = connDao.registerNewUserInfo(newUserInfo);
		boolean userFlag = connDao.registerNewUser(newUser);
		
		request.removeAttribute("newUser");
		
		if(userFlag && userInfoFlag)
		{
			request.setAttribute("newUserName", request.getParameter("username"));
			request.setAttribute("newPassword", request.getParameter("password"));
			request.setAttribute("userCreation", "success");
		}
		else
		{
			request.setAttribute("userCreation", "failed");
		}
		
		
		return "newuserui.jsp";
	}
	
	private String register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		/* write the code to redirect to register page */
		
		return null;
	}
	private String displaystatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		/* write the code the display the loan status based on the given application
		   number 
		*/
		
		return null;
	}

	private String editloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to editloan page */
        
		HttpSession session = request.getSession();
		
		//String LoanID = request.getParameter("LoanID");
			//System.out.println(session.getAttribute("LoanID"));
			String applno= request.getParameter("APPLID");
			LoanInfo LoanDetails = connDao.getLoanAppInfo(applno);
			
			//System.out.println(LoanDetails);
			request.setAttribute("LoanInfo", LoanDetails);
			

			//RequestDispatcher dispatcher = request.getRequestDispatcher("editloanui.jsp");
			
			String view = "editloanui.jsp";
		
		    return view;
	}

	private String trackloan(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write a code to return to trackloan page */
	
		
		String status = connDao.getLoanById(request.getParameter("applicationnumber"));
		request.setAttribute("status", status);
		
		
		String view = "displayStatus.jsp";
		
		return view;
	}

	private String application(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		request.setAttribute("state", request.getParameter("state"));
		return "application.jsp";
	}
}