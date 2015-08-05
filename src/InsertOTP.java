package com.election.voter;
import com.election.voter.*;
import java.sql.*;
public class InsertOTP
{

long id;
String Otp;
InsertOTP(long uid)
{
id=uid;
Otp=OTPAadhar.generatePIN();
}
void insert()
{
try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver"); 
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					PreparedStatement ps = con.prepareStatement("insert into OTPVoter (vid,Otp) values (?, ?)");
					ps.setLong(1,id);
					ps.setString(2,Otp);
					int i=ps.executeUpdate();  
							
					
					
					
					
					 
					
					}
					catch(Exception r5)
					{
					r5.printStackTrace();
					}
					}
					
}