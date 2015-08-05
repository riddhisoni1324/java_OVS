package com.election.voter;
import com.election.*;
import com.election.admin.*;
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

public class ModifiyVoterDetails extends NewRegistration
{
private long vid;
String gender;
public java.sql.Date bdate1;
 String dateString;
 SimpleDateFormat sdfr;
public ModifiyVoterDetails(long id)
	{
	super();
	vid=id;
	setAlwaysOnTop(true);
		setResizable(false);
				this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });
  
	final JLabel l = new JLabel("Modify Voter Details");

    Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 1);
    Font newFont = myFont.deriveFont(30F);
	l.setBounds(250,10,500,30);
		
    l.setFont(newFont);
	add(l);

						bdate.setEnabled(false);
						tuid.setEditable(false);
			
						bFemale.setEnabled(false);
						bMale.setEnabled(false);
						chngepass.setVisible(true);
						lpass.setEnabled(false);
						lpass1.setEnabled(false);
						tpass.setEnabled(false);
						tpass1.setEnabled(false);
					//	remove(lpass);remove(lpass1);remove(tpass);remove(tpass1);
			
			
			
	
	
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
				 jc.setSelectedIndex(acons);
					
				 tconsid.setText(String.valueOf(acons));
				 tdate.setText(convertStringToDate(bdate1));
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
	{    if(e.getSource()==chngepass)
			{
				System.out.println("change password cliked");
						lpass.setEnabled(true);
						lpass1.setEnabled(true);
						tpass.setEnabled(true);
						tpass1.setEnabled(true);
						
						
			}

		if(e.getSource()==bsubmit)
		{
				 name=tname.getText();
				 email=temail.getText();
				 try{  phno=Long.parseLong(tphno.getText()); uid=Long.parseLong(tuid.getText()); }
				 catch(NumberFormatException nf) { lerr1.setText("Enter valid number"); }
				 add=taddress.getText(); 
				  pass=tpass.getText();
				 pass1=tpass1.getText();
				 cons=Integer.parseInt(tconsid.getText());
				 RegGetSet rgs=new RegGetSet(phno,email);
				  try{
					  getpass=rgs.setpass2(pass);
					 System.out.println("encrepted pas"+getpass);
					 String decpass=rgs.getpass2(getpass);
				System.out.println("dec pass "+decpass);
					
					 }
					 catch(Exception r2)
					 {
					 r2.printStackTrace();
					 }
				
				 rgs.setname1(name);
				 System.out.println("name from get set "+rgs.getname1());
				 rgs.setname1(name); rgs.setemail1(email); rgs.setphno1(phno); rgs.setuid1(uid); rgs.setadd1(add); 
					rgs.setcons1(cons);
				 	
					 
					 
					 System.out.println("\n\n Information before entering into database throgh get and set "+rgs.getname1()+" "+rgs.getemail1()+ " "+rgs.getphno1()+" "+rgs.getuid1()+" "+rgs.getadd1()+" "+rgs.getcons1() );
					 
						 System.out.println("ph no is="+rgs.getphno1()); long p1=rgs.getphno1(); System.out.println("ph no is long="+p1);
						int i1=rgs.numdigits(p1);System.out.println("Mobile no digit is"+i1);String s1=rgs.getemail1();
						System.out.println("get mail iss"+s1);boolean b1=rgs.authenticateemail1();System.out.println("email l"+b1);
							
			if(rgs.authenticatephno1(i1) && rgs.authenticateemail1() && rgs.checkPass(pass,pass1))
				{
		 
					try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					PreparedStatement ps = con.prepareStatement("insert into MODIFYVOTERINFO (vname, vemailid, vphno, vid,vaddress,cons_id,vpassword) values ( ?, ?, ?,?,?,?,?)");
					
					//--setting all values in database
					ps.setString(1,name);ps.setString(2,email);ps.setLong(3,phno);ps.setLong(4,uid);ps.setString(5,add);ps.setInt(6,cons);ps.setString(7,getpass);

					int i=ps.executeUpdate();  
					System.out.println(i+" records affected");  
					
							PreparedStatement ps1 = con.prepareStatement("update voterinfo set vpassword=? where vid="+vid);
					
					ps1.setString(1,getpass);
					
					int i12=ps1.executeUpdate();  
					System.out.println(i12+" records affected");  
			
					//--getting value from database
					
						
					}
					catch(Exception e2) { e2.printStackTrace(); }
						
	
				}
			else 
			{System.out.println("Please enter valid info");}
			
			}
		
		
	}
	


public static void main(String []args)
{
ModifiyVoterDetails md=new ModifiyVoterDetails(5895);

}

}
