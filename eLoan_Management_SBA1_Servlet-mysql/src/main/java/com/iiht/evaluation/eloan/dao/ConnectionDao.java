package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.dto.UserInfo;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;


public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection = null;
	
	public static final String LOANINFO_Insert_query =
			"INSERT INTO eloan.loan_details(APPLICANT_NAME,PURPOSE,REQ_AMT,APPL_DT,BUSS_STR,BUS_IND,TAX_IND,ADDRESS,EMAIL,CONTACTNUM,APPL_STATUS) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	
	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public  Connection connect() throws SQLException {
		//if ( jdbcConnection.isClosed() || jdbcConnection == null ) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
		//}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		//if (!jdbcConnection.isClosed()) {//jdbcConnection != null && 
			jdbcConnection.close();
		//}
	}
	

	/*------SQL queries used-----*/
	
	
	
	/*-----------------------------*/
	
	
	// put the relevant DAO methods here..
	
	public boolean addLoanDetails(LoanInfo loanInfo) {
		PreparedStatement pst = null;
		
		boolean successFlag = false;
		
		if(loanInfo!=null) {
			try {
				jdbcConnection = this.connect();
				
				pst = jdbcConnection.prepareStatement(LOANINFO_Insert_query);
				//purpose
				pst.setString(1, loanInfo.getUserName());
				pst.setString(2, loanInfo.getPurpose());
				pst.setString(3, loanInfo.getAmtrequest());
				pst.setString(4, loanInfo.getDoa());
				pst.setString(5, loanInfo.getBstructure());
				pst.setString(6,loanInfo.getBindicator());
				pst.setString(7,loanInfo.gettindicator());
				pst.setString(8, loanInfo.getAddress());
				pst.setString(9, loanInfo.getEmail());
				pst.setString(10, loanInfo.getMobile());
				pst.setString(11, "Submitted");
				System.out.println(pst);
				int updatedRows = pst.executeUpdate();
				
				successFlag = updatedRows==1;
				
			}catch(SQLException exp) {
				System.out.println(exp.getMessage());
			}
			finally {
				try {
					pst.close();
					this.disconnect();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		return successFlag;
	}
	
	public HashMap<String, String> getUserCredData()
	{
		Statement stmt = null;
		HashMap<String, String> cred_map = new HashMap<String, String>();
		
		try
		{
			jdbcConnection = this.connect();
			stmt = jdbcConnection.createStatement();
			String FETCH_USER_CRED = "select USER_NAME, USER_PASSWORD from eloan.user_cred;";
			
			ResultSet rs = stmt.executeQuery(FETCH_USER_CRED);

			while(rs.next())
			{
				cred_map.put(rs.getString("user_name"), rs.getString("user_password"));
			}
			return cred_map;
		} 
		catch(Exception e)
		{
			System.out.println("Unable to fetch User credential data");
			System.out.println("Error : "+e.getMessage());
			return cred_map;
		}
		finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
	}
		
		
	public UserInfo getUserInfo(String userName) 
	{
			Statement stmt = null;
			UserInfo userInfo = null;

			try
			{
				jdbcConnection = this.connect();
				stmt = jdbcConnection.createStatement();
				
				String FETCH_USER_INFO = "select * from eloan.user_details where APPLI_FIRSTNAME='%s'";
				
				ResultSet rs = stmt.executeQuery(String.format(FETCH_USER_INFO,userName));
				while(rs.next())
				{
					System.out.println(rs.getString("APPLI_FIRSTNAME")+"-"+rs.getString("APPLI_LASTNAME"));
					
					userInfo = new UserInfo(rs.getString("APPLI_NAME"), rs.getString("APPLI_FIRSTNAME"), 
							rs.getString("APPLI_LASTNAME"), rs.getString("APPL_DOB"), rs.getString("CONTACT"), rs.getString("EMAIL"), 
							rs.getString("CITY"), rs.getString("STATE"), rs.getString("COUNTRY"), 
							rs.getString("PINCODE"), rs.getString("APPLI_ADDRESS"));
					
				}
				return userInfo;
			} 
			catch(Exception e)
			{
				System.out.println("Unable to fetch User Info data");
				System.out.println("Error : "+e.getMessage());
				return userInfo;
			}
			finally 
			{
				try 
				{
					stmt.close();
					this.disconnect();
				} 
				catch (SQLException e) 
				{
					System.out.println("Error : "+ e.getMessage());
				}
			}
	}

	public LoanInfo getLoanData(String mobile) {
		
		
		Statement stmt = null;
		LoanInfo loanInfo = null;

		try
		{
			jdbcConnection = this.connect();
			stmt = jdbcConnection.createStatement();
			String FETCH_LOAN_INFO_Query = "select * from eloan.loan_details where CONTACTNUM ='%s'";
			ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN_INFO_Query,mobile));
			while(rs.next())
			{
				loanInfo = new LoanInfo(rs.getString("APPLICANT_NAME"),rs.getString("APPTN_ID"), rs.getString("PURPOSE"), rs.getString("REQ_AMT"), 
						rs.getString("APPL_DT"), rs.getString("BUSS_STR"), rs.getString("BUS_IND"),rs.getString("TAX_IND"), rs.getString("ADDRESS"), 
						rs.getString("EMAIL"), rs.getString("CONTACTNUM"), rs.getString("APPL_STATUS"));
			}
			return loanInfo;
		} 
		catch(Exception e)
		{
			System.out.println("Unable to fetch Loan Info data");
			System.out.println("Error : "+e.getMessage());
			return loanInfo;
		}
		finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
		
		
		
	}
	
	public String getLoanById(String applno) {
		
		Statement stmt = null;
		String status = "";
		try
		{
			jdbcConnection = this.connect();
			stmt = jdbcConnection.createStatement();
			String FETCH_LOAN_BY_ID = "select APPL_STATUS from eloan.loan_details where APPTN_ID ='%s'";
			
			ResultSet rs = stmt.executeQuery(String.format(FETCH_LOAN_BY_ID,applno));
			System.out.println(stmt);
			while(rs.next())
			{
				status = rs.getString("APPL_STATUS");
				System.out.println(status);
			}
			return status;
		} 
		catch(Exception e)
		{
			System.out.println("Unable to fetch Status data");
			System.out.println("Error : "+e.getMessage());
			return status;
		}
		finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
		
		
		
	}
	
	public LoanInfo getLoanInfoByAppNumber(String applNo) {
		
		
		Statement stmt = null;
		LoanInfo loanInfo = null;
		String FETCH_LOANINFO_ID = "select * from eloan.loan_details where APPTN_ID=%s";
		try
		{
			jdbcConnection = this.connect();
			stmt = jdbcConnection.createStatement();
			ResultSet rs = stmt.executeQuery(String.format(FETCH_LOANINFO_ID,applNo));
			while(rs.next())
			{
				

				loanInfo = new LoanInfo(rs.getString("APPLICANT_NAME"),rs.getString("APPTN_ID"), rs.getString("PURPOSE"), rs.getString("REQ_AMT"), 
						rs.getString("APPL_DT"), rs.getString("BUSS_STR"), rs.getString("BUS_IND"),rs.getString("TAX_IND"), rs.getString("ADDRESS"), 
						rs.getString("EMAIL"), rs.getString("CONTACTNUM"), rs.getString("APPL_STATUS"));
			}
			return loanInfo;
		} 
		catch(Exception e)
		{
			System.out.println("Unable to fetch Loan Info data");
			System.out.println("Error : "+e.getMessage());
			return loanInfo;
		}
		finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
		
		
		
	}
	
public ArrayList<LoanInfo> getLoanInfolistall() {
		
		
		Statement stmt = null;
		LoanInfo loanInfo = null;
		ArrayList<LoanInfo> list_loans =new ArrayList<LoanInfo>();

		try
		{
			jdbcConnection = this.connect();
			stmt = jdbcConnection.createStatement();
			String FETCH_ALL_LOAN_INFO = "select * from eloan.loan_details";
			ResultSet rs = stmt.executeQuery(String.format(FETCH_ALL_LOAN_INFO));
			while(rs.next())
			{
				loanInfo = new LoanInfo(rs.getString("APPLICANT_NAME"), rs.getString("APPTN_ID"), rs.getString("PURPOSE"), rs.getString("REQ_AMT"), 
						rs.getString("APPL_DT"), rs.getString("BUSS_STR"), rs.getString("BUS_IND"), rs.getString("TAX_IND"), rs.getString("ADDRESS"),
						rs.getString("EMAIL"), rs.getString("CONTACTNUM"), rs.getString("APPL_STATUS"));
				list_loans.add(loanInfo);
			}
			
			return list_loans;
		} 
		catch(Exception e)
		{
			System.out.println("Unable to fetch Loan Info data");
			System.out.println("Error : "+e.getMessage());
			return list_loans;
		}
		finally 
		{
			try 
			{
				stmt.close();
				this.disconnect();
			} 
			catch (SQLException e) 
			{
				System.out.println("Error : "+ e.getMessage());
			}
		}
	}

public void  UpdateLoanInfo(LoanInfo LoanData, String applno) {
	
	jdbcConnection = null;
	//LoanDetailsDto LoanDetails = null;
	//ResultSet rs1;
		//String LoanSQL = "Select * from eLoan.loan_details where loan_app_num =? and loan_stat =?";
	

		try {
			jdbcConnection = this.connect();
			
			PreparedStatement prepstmt = jdbcConnection.prepareStatement("UPDATE eLoan.loan_details SET REQ_AMT = ?, BUSS_STR =?,BUS_IND =?,TAX_IND =?,APPLICANT_NAME =?,PURPOSE = ?,CONTACTNUM =?,ADDRESS =?,EMAIL =? where APPTN_ID =?");
			
			prepstmt.setString(1,LoanData.getAmtrequest());
			prepstmt.setString(2,LoanData.getBstructure());
			prepstmt.setString(3,LoanData.getBindicator());
			prepstmt.setString(4,LoanData.gettindicator());
			prepstmt.setString(5,LoanData.getUserName());
			prepstmt.setString(6,LoanData.getPurpose());
			prepstmt.setString(7,LoanData.getMobile());
			prepstmt.setString(8,LoanData.getAddress());
			prepstmt.setString(9,LoanData.getEmail());
			prepstmt.setString(10,applno);
			System.out.println(prepstmt);
			 int recupdated = prepstmt.executeUpdate();
			 
				
			 System.out.println("num of rec updated "+recupdated);
				/* Log.info("Record updated succesfully"); */
			
                        
            System.out.println("Successfully Updated"); 
            return; 
		
		 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
	}



public boolean registerNewUserInfo(UserInfo userInfo) {
	
	PreparedStatement pst = null;
	boolean successFlag = false;

	try {
			jdbcConnection = this.connect();
			
			String INS_USERINFO_QRY="INSERT INTO eloan.user_details(APPLI_NAME ,APPLI_FIRSTNAME ,APPLI_LASTNAME ,CONTACT ,EMAIL,CITY,STATE,COUNTRY,PINCODE ,APPLI_ADDRESS,APPL_DOB) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			
			pst = jdbcConnection.prepareStatement(INS_USERINFO_QRY);

			pst.setString(1, userInfo.getUsername());
			pst.setString(2, userInfo.getFirstName());
			pst.setString(3, userInfo.getLastName());
			pst.setString(4, userInfo.getMobile());
			pst.setString(5, userInfo.getEmail());
			pst.setString(6, userInfo.getCity());
			pst.setString(7, userInfo.getState());
			pst.setString(8, userInfo.getCountry());
			pst.setString(9, userInfo.getPincode());
			pst.setString(10, userInfo.getAddress());
			pst.setString(11, userInfo.getDob());
			System.out.println(pst);
			int updatedRows = pst.executeUpdate();
			
			successFlag = updatedRows==1;
			
		}catch(SQLException exp) {
			System.out.println(exp.getMessage());
		}
		finally {
			try {
				pst.close();
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	return successFlag;
}

public LoanInfo getLoanAppInfo(String applno) {
	

	jdbcConnection = null;
	LoanInfo LoanDetails = null;
	ResultSet rs;
		
	//String LoanFetchSQL = "Select * from eLoan.loan_details where loan_app_num =?";
	//System.out.println(LoanFetchSQL);	
	try
	{
		jdbcConnection = this.connect();
		//stmt = jdbcConnection.createStatement();
		PreparedStatement prepstmt = jdbcConnection.prepareStatement("Select * from eLoan.loan_details where APPTN_ID =?");
		prepstmt.setString(1, applno);
		System.out.println(prepstmt);
		rs = prepstmt.executeQuery();
		
		
		while(rs.next())
		{
			String loanstatus = rs.getString("APPL_STATUS");
			System.out.println(rs.getString("APPLICANT_NAME"));
			if (loanstatus !="APPROVED") {
			LoanDetails =new LoanInfo(rs.getString("APPLICANT_NAME"), rs.getString("APPTN_ID"),rs.getString("PURPOSE"), rs.getString("REQ_AMT"), rs.getString("APPL_DT"), rs.getString("BUSS_STR"), 
					rs.getString("BUS_IND"), rs.getString("TAX_IND"), rs.getString("ADDRESS"), rs.getString("EMAIL"), 
					rs.getString("CONTACTNUM"), rs.getString("APPL_STATUS"));
		}
			
		}
		
	

	//System.out.println(LoanDetails);

 
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return LoanDetails;

}



public boolean registerNewUser(User user) {
	
	PreparedStatement pst = null;
	boolean successFlag = false;
	
	try {
			jdbcConnection = this.connect();
			
			String INS_USERCRED_QRY = "INSERT INTO eloan.user_cred(USER_NAME,USER_PASSWORD) VALUES(?,?)";
			
			pst = jdbcConnection.prepareStatement(INS_USERCRED_QRY);

			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			
			int updatedRows = pst.executeUpdate();
			
			successFlag = updatedRows==1;
			
		}catch(SQLException exp) {
			System.out.println(exp.getMessage());
		}
		finally {
			try {
				pst.close();
				this.disconnect();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	return successFlag;
}


public ResultSet EditLoanDetails(LoanInfo LoanInfo) throws Exception {
	// TODO Auto-generated method stub
	ResultSet rs;
	

	String EditLoanSQL = "select * from eLoan.LoanDetails where applno=?";
	PreparedStatement pstmt = jdbcConnection.prepareStatement(EditLoanSQL);
	pstmt.setString(1, LoanInfo.getApplno());
	rs = pstmt.executeQuery();
	// System.out.println(rs.getString("status"));
	// List<LoanInfo> LoanInfolist = new ArrayList<LoanInfo>();
	while (rs.next()) {
		String status = rs.getString(13);
		// System.out.println(rs.getString(5));
		if (status.equals("Pending") ||status.equals("OnHold")) {
			/*
			 * System.out.println(rs.getString(13)); LoanInfo Loaninfo = new
			 * LoanInfo(rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6),
			 * rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
			 * rs.getString(11), rs.getLong(12)); System.out.println(Loaninfo);
			 */

			return rs;
		}
		else {
			throw new Exception("Cannot Edit Approved Loans");
	}
	}
	/*
	 * else { System.out.println("Application is not in pending status");
	 * 
	 * }
	 */
	return rs;

	

}




public void ApproveLoanDetails(ApprovedLoan ApprovedLoan) throws SQLException {
	// TODO Auto-generated method stub
	String SQL = "UPDATE eLoan.loan_details  SET AMT_SANCD=?,TERM=?,PSD=?,LCD=?,EMI=?,termpaymentamount=?,ROI=?,APPL_STATUS=?"
			 + " where APPTN_ID=?";
	
	PreparedStatement pstmt = jdbcConnection.prepareStatement(SQL);
	
	pstmt.setInt(1, ApprovedLoan.getAmotsanctioned());
	pstmt.setInt(2, ApprovedLoan.getLoanterm());
	pstmt.setString(3, ApprovedLoan.getPsd());
	pstmt.setString(4, ApprovedLoan.getLcd());
	pstmt.setLong(5, ApprovedLoan.getEmi());
	pstmt.setLong(6, ApprovedLoan.getTermpaymentamount());	
	pstmt.setFloat(7, ApprovedLoan.getInterestRate());		
	pstmt.setString(8, ApprovedLoan.getStatus());
	pstmt.setString(9, ApprovedLoan.getApplno());	
	System.out.println(pstmt);
	pstmt.executeUpdate();
	
	
}

}

