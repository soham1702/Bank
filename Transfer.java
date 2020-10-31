import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Transfer {
	int acc_no;
	String pass;
	Connection con;
	Statement st3,st;
	PreparedStatement st2;
	ResultSet rs,rs2;
	Map<Integer,String> acc_pass;
	Map<Integer,Double> acc_bal;
	
	public Transfer() {
		super();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String up="update account_bal set balance=? where accountno=?";
			con=DriverManager.getConnection("jdbc:mysql://localhost/Bank","root","soham");
			st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st2=con.prepareStatement(up);
			st3=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			rs2=st.executeQuery("select * from account_bal");
			rs=st3.executeQuery("select * from users");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	void login(int acc_no,String pass)
	{
		this.acc_no=acc_no;
		this.pass=pass;
	}
	
	Boolean verify()
	{
		try {
			while(rs.next())
			{
				if(rs.getInt(1)==acc_no)
				{
					System.out.println("\n Account no verified!!!!");
					if(rs.getString(2).equals(pass))
					{
						System.out.println("\n Pass verified");
						return true;
					}
				}
			}
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	boolean credit(float amt)
	{
		
		try {
			while(rs2.next())
			{
				if(rs2.getInt(1)==acc_no)
				{
					rs2.updateFloat(2, rs2.getFloat(2)+amt);
					rs2.updateRow();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	boolean debit(Double amt)
	{
		try {
			rs2.beforeFirst();
			while(rs2.next())
			{
				if(rs2.getInt(1)==acc_no)
				{
					if(amt>rs2.getFloat(2))
					{
						System.out.println("\n Not enough balance!!!!!!!!1");
						return false;
					}
					else
					{
						rs2.updateFloat(2, (float) (rs2.getFloat(2)-amt));
						rs2.updateRow();
						return true;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
			
	}
	
	void showstatus()
	{
		System.out.println("\n ***********ACCOUNT STATUS*********");
		try {
			rs2.beforeFirst();
			//System.out.println("\n rs moved to first");
			while(rs2.next())
			{
				//System.out.println("\n in while");
				if(rs2.getInt(1)==acc_no)
				{
					System.out.println("\n Account Number :- "+acc_no+"\t Balance :- "+rs2.getFloat(2));
				}
//				else
//					System.out.println("--");
			}
			//System.out.println("\n exited loop");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	boolean send(Double amt,int acc_no2)
	{
		try {
			rs2.beforeFirst();
			while(rs2.next())
			{
				if(rs2.getInt(1)==acc_no)
				{
					if(debit(amt))
					{
						try {
							rs2.beforeFirst();
							while(rs2.next())
							{
								if(rs2.getInt(1)==acc_no2)
								{
									rs2.updateFloat(2, (float) (rs2.getFloat(2)+amt));
									rs2.updateRow();
									return true;
								}
							}
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
}
