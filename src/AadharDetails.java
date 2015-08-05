package com.election.voter;
//import com.election.admin.*;
import com.election.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.*;
import sun.misc.*;
import javax.crypto.spec.*;
import java.text.*;

public class AadharDetails extends NewRegistration
{
private long vid;
String gender;
public java.sql.Date bdate1;
String dateString;
SimpleDateFormat sdfr;
 java.text.SimpleDateFormat sfd,sfd1;    java.util.Date ud1,ud11;		long ms,ms1;  			java.sql.Date sqld,sqld1;int ageconfrm;
	  
	
					
public AadharDetails(long id)
	{
	super();
	vid=id;
	 setAlwaysOnTop(true);
	setResizable(false);
	
	final JLabel l = new JLabel("Aadhar  Card  Details");

    Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 1);
    Font newFont = myFont.deriveFont(30F);
	l.setBounds(250,10,500,30);
		
    l.setFont(newFont);
	add(l);

						tpass.setEnabled(false);
						tpass1.setEnabled(false);
					chngepass.setVisible(true);
				
	
	try{
	
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					String sql ="select * from voterInfo where vid="+vid;
    				PreparedStatement preStatement = con.prepareStatement(sql);
				
					ResultSet result = preStatement.executeQuery();
					RegGetSet args=new RegGetSet(phno,email);
				  
								while(result.next()){
									aname=result.getString("v_name");
									aemail=result.getString("vemailid");
									aadd=result.getString("vaddress");
									acons=result.getInt("cons_id");
									System.out.println("in while..."+acons);
									auid=result.getLong("vid");
									aphno=result.getLong("phone_no");
									gender=result.getString("gender");
									bdate1=result.getDate("birthdate");
									System.out.println("Current Data from Oracle : " +   aname+" "+aemail +" "+aadd +"  "+acons+" "+auid+" "+aphno    +" "+gender+" "+bdate);
								}
								
					System.out.println("\n\nAfter inserted record\n\n");			
					args.setname1(aname);args.setemail1(aemail);args.setphno1(aphno);args.setuid1(auid);args.setadd1(aadd);args.setcons1(acons);

					System.out.println("\n\nfetching value from database using get and set method \n\n"+args.getname1()+" "+args.getemail1()+ " "+args.getphno1()+" "+args.getuid1()+" "+args.getadd1()+" "+args.getcons1() );
			 
					tname.setText(aname);
					temail.setText(aemail);
					tphno.setText(String.valueOf(aphno)); 
					tuid.setText(String.valueOf(auid));
					taddress.setText(aadd);
						acons=acons-1;
						System.out.println("out while..."+acons);
					jc.setSelectedIndex(acons);
					tdate.setText(convertStringToDate(bdate1));
					tdate.setEditable(false);
					tname.setEditable(false);
					temail.setEditable(false);
					tphno.setEditable(false);
					taddress.setEditable(false);
					tconsid.setEditable(false);
					tuid.setEditable(false);
					bdate.setEnabled(false);
					
					bFemale.setEnabled(false);
					bMale.setEnabled(false);
				if(gender.equals("female"))
					{
					bFemale.setSelected(true);
					}
					if(gender.equals("male"))
					{
					bMale.setSelected(true);
					}

					
					}
					catch(Exception e2) { e2.printStackTrace(); }
					
	}
	public String convertStringToDate(Date indate)
								{
								   String dateString = null;
								   SimpleDateFormat sdfr = new SimpleDateFormat("dd/MM/yyyy");
								   /*you can also use DateFormat reference instead of SimpleDateFormat 
									* like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
									*/
								   try{
									dateString = sdfr.format( indate );
								   }catch (Exception ex ){
									System.out.println(ex);
								   }
								   return dateString;
								}

public void actionPerformed(ActionEvent e)  
	{	 if(e.getSource()==chngepass)
			{
				System.out.println("change password cliked");
						tpass.setEnabled(true);
						tpass1.setEnabled(true);
						
						
			}
			
	
		if(e.getSource()==bsubmit)
		{		pass=tpass.getText();
				pass1=tpass1.getText();
				System.out.println("password..."+pass+" "+pass1);
				sfd= new SimpleDateFormat("dd/MM/yyyy");
				  try{ud1= sfd.parse(tdate.getText());
					ms = ud1.getTime();
					sqld= new java.sql.Date(ms);
					
				
				Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					
					CallableStatement stmt=con.prepareCall("{?= call get_age_voter(?)}");  
				stmt.setDate(2,sqld);  
				stmt.registerOutParameter(1,Types.INTEGER);  
				stmt.execute();  
				  ageconfrm=stmt.getInt(1);
				System.out.println("age is "+ageconfrm);  
				if(ageconfrm<18){System.out.println("you are not eligible");}
				}catch(Exception t6){System.out.println("you are not eligible");}
					
				 
				 email=temail.getText();
				 try{  phno=Long.parseLong(tphno.getText()); uid=Long.parseLong(tuid.getText()); }
				 catch(NumberFormatException nf) { lerr1.setText("Enter valid number"); }
				 RegGetSet rgs=new RegGetSet();
				  try{
					  getpass=rgs.setpass2(pass);
					 System.out.println("encrepted pas"+getpass);
					 }
					 catch(Exception r2)
					 {
					 r2.printStackTrace();
					 }
					 

			
			if(rgs.checkPass(pass,pass1) && ageconfrm>18)
				{
					tpass.setEnabled(false);
					tpass1.setEnabled(false);
		
		 
					try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					PreparedStatement ps = con.prepareStatement("update voterInfo set vpassword=? where vid="+vid);
					
					//--setting all values in database
					ps.setString(1,getpass);

					int i=ps.executeUpdate();  
					System.out.println(i+" records affected");  
					//--getting value from database
					String sql ="select * from voterInfo where vid="+uid;
    				PreparedStatement preStatement = con.prepareStatement(sql);
				
					ResultSet result = preStatement.executeQuery();
					RegGetSet args=new RegGetSet(phno,email);
				  
								while(result.next()){
									aname=result.getString("v_name");
									aemail=result.getString("vemailid");
									aadd=result.getString("vaddress");
									acons=result.getInt("cons_id");
									auid=result.getLong("vid");
									aphno=result.getLong("phone_no");
									gender=result.getString("gender");
									System.out.println("Current Data from Oracle : " +   aname+" "+aemail +" "+aadd +"  "+acons+" "+auid+" "+aphno+" "+gender    );
								}
					System.out.println("\n\nAfter inserted record\n\n");			
					args.setname1(aname);args.setemail1(aemail);args.setphno1(aphno);args.setuid1(auid);args.setadd1(aadd);args.setcons1(acons);

					System.out.println("\n\nfetching value from database using get and set method \n\n"+args.getname1()+" "+args.getemail1()+ " "+args.getphno1()+" "+args.getuid1()+" "+args.getadd1()+" "+args.getcons1() );
					tpass.setEditable(false);
					tpass1.setEditable(false);
		
					VoterScreen vs=new VoterScreen(vid);
				
					}
					catch(Exception e2) { e2.printStackTrace(); }
						
	
				}
			else 
			{
			
					lpass2.setText("Please Enter valid password in aadhar details");
						System.out.println("Please enter valid info");}
			
			}
			
			
		
		
	}
	


public static void main(String []args)
{
AadharDetails md=new AadharDetails(5895);

}

}
