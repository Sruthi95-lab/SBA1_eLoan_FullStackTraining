package com.iiht.evaluation.eloan.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;


@WebServlet("/admin")
public class AdminController extends HttpServlet {
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
		String action =  request.getParameter("action");
		System.out.println(action);
		String viewName = "";
		try {
			switch (action) {
			case "listall" : 
				viewName = listall(request, response);
				break;
			case "process":
				viewName=process(request,response);
				break;
			case "calemi":
				viewName=calemi(request,response);
				break;
			case "updatestatus":
				viewName=updatestatus(request,response);
				break;
			case "logout":
				viewName = adminLogout(request, response);
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

	private String updatestatus(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub	
		/* write the code for updatestatus of loan and return to admin home page */
		return null;
		
	}
	private String calemi(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* write the code to calculate emi for given applno and display the details */
		
		String applno = request.getParameter("Appnum");	
		System.out.println(request.getParameter("AmountSanction"));
		int amotsanctioned = Integer.parseInt(request.getParameter("AmountSanction"));
		int termperiod = Integer.parseInt(request.getParameter("Term"));
		//int emi = Integer.parseInt(request.getParameter("emi"));
		String PSD = request.getParameter("PayementStartDate");
		String LCD = request.getParameter("LoanClosureDate");
		String status= request.getParameter("LoanStatus");
		//int termpaymentamount = Integer.parseInt(request.getParameter("termpaymentamount"));
		float interestRate = Float.parseFloat(request.getParameter("IntrestRate"));
		int termpaymentamount=(int)(amotsanctioned*Math.pow(((1+interestRate)/100),termperiod));
		int emi = termpaymentamount/termperiod;
		ApprovedLoan ApprovedLoan1=new ApprovedLoan(applno,amotsanctioned, termperiod, PSD, LCD, emi, termpaymentamount,interestRate,status);
		System.out.println(ApprovedLoan1);
		request.setAttribute("ApprovedLoan", ApprovedLoan1);
		connDao.ApproveLoanDetails(ApprovedLoan1);
		
		return "adminhome1.jsp";
		
	}
	private String process(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
	/* return to process page */
		HttpSession session = request.getSession();
		
		//String LoanID = request.getParameter("LoanID");
			//System.out.println(session.getAttribute("LoanID"));
			String applno= request.getParameter("APPLID");
			LoanInfo LoanDetails = connDao.getLoanAppInfo(applno);
			
			//System.out.println(LoanDetails);
			request.setAttribute("LoanInfo", LoanDetails);
			

			//RequestDispatcher dispatcher = request.getRequestDispatcher("editloanui.jsp");
			
			String view = "calemi.jsp";
		
		    return view;
	}
		
		
	private String adminLogout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
	/* write code to return index page */
		return null;
	}

	private String listall(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	/* write the code to display all the loans */
		HttpSession session = request.getSession();
		
		ArrayList<LoanInfo> getallthedetails = connDao.getLoanInfolistall();
		
		request.setAttribute("ListLoan", getallthedetails);
		
		return "listall.jsp";
	}

	
}