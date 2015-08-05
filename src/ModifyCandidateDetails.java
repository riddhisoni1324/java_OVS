package com.election.admin;
import java.awt.*;
import com.election.admin.*;import com.election.*;

import java.sql.*;
import java.sql.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
import java.sql.*;
import javax.swing.filechooser.FileFilter;
import java.text.*;
import java.awt.event.*;



public class ModifyCandidateDetails extends AddCandidate implements ActionListener,ItemListener
{
int cid,conid,mid,pid;
String cname,cemail,cqal,cdes,cadd,cgender;
long cphno;
ImageIcon i2;public java.sql.Date bdate1; String dateString;
 SimpleDateFormat sdfr;RegGetSet rgs;FileInputStream fin;


	ModifyCandidateDetails(int id)
	{
		super();
		mid=id;
		setAlwaysOnTop(true);
		setResizable(false);
				b1date.setEnabled(false);tdate.setEnabled(false);bFemale.setEnabled(false);bMale.setEnabled(false);
				jc.setEnabled(false);jc1.setEnabled(false);egender.setVisible(false);





	
					try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					String sql ="select * from candidate where c_id="+mid;
    				PreparedStatement preStatement = con.prepareStatement(sql);
				
					ResultSet result = preStatement.executeQuery();
					
								while(result.next()){
								
									cid=result.getInt("c_id");
									conid=result.getInt("cons_id");
									cname=result.getString("c_name");
									cqal=result.getString("c_qualification");
									cdes=result.getString("c_description");
									pid=result.getInt("pid");
									cphno=result.getLong("phone_no");
									cemail=result.getString("email");
									cadd=result.getString("ADDRESS");
									cgender=result.getString("gender");
									bdate1=result.getDate("birthdate");
									
									Blob b=result.getBlob(8);//6 means 6 th column data  
									byte barr[]=b.getBytes(1,(int)b.length());//1 means first image  
									String filepathforFileOutput="C:\\java1\\jdk1.8.0_05\\OVS\\res\\canimg\\get\\"+(new Integer(cid).toString()) + ".png";
									FileOutputStream fout=new FileOutputStream(filepathforFileOutput);  
									fout.write(barr);  
									fout.close();
									i2=new ImageIcon(filepathforFileOutput);
															
				
									
									System.out.println("Current Data from Oracle : " +  cid +" "+conid +" "+cname +"  "+cqal+" "+cdes);
								}
					label.setText(String.valueOf(cid));
					tcanName.setText(cname);
					tcanQua.setText(cqal);
					textArea.setText(cadd);
					tconId.setText(String.valueOf(conid));
					tphno.setText(String.valueOf(cphno));
					temail.setText(cemail);
					jl.setIcon(i2);
					jc.setSelectedIndex(conid-1);
					jc1.setSelectedIndex(pid-1);
					textArea.setText(cadd);
					tdate.setText(convertStringToDate(bdate1));
					System.out.println("gender is..................."+cgender);
					if(cgender.equals("Female"))
					{bFemale.setSelected(true);}
					if(cgender.equals("Male"))
					{bMale.setSelected(true);}
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
					
	
	
	
							public void storeImage() throws Exception
			{

			JFileChooser chooser=new JFileChooser(new File("R:\\"));
			  
			chooser.setMultiSelectionEnabled(false);
			chooser.setVisible(true);

			chooser.showOpenDialog(this);

			File file=chooser.getSelectedFile();
			if(file!=null){filePath=file.getPath();}
			if(filePath!=null){
			filePath=filePath.replace("\\","\\\\");
			tcanPic.setText(filePath);
			
			filepath2=filePath.replace("\\","/");

			image=ImageIO.read(new File(filepath2));
			ImageIcon ic=new ImageIcon(scaledImage(image,128,128));
			temp=scaledImage(image,128,128);
			btemp=(BufferedImage)temp;
			f=new File("../canimg/ot3.png");
			ImageIO.write(btemp,"png",f);		
				 jl.setIcon(ic);

				 

			}
			}
			
			
			public Image scaledImage(Image img,int w,int h)
			{
			BufferedImage resizeImage=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2=resizeImage.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g2.drawImage(img,0,0,w,h,null);
			g2.dispose();
			
			return resizeImage;

			}
public void actionPerformed(ActionEvent e)  
	{
		if(e.getSource()==browse)
		{
		try{storeImage();}
		catch(Exception e3) {e3.printStackTrace();}
		}
		if(e.getSource()==addCan)
		{
				ephno.setText(" ");
				email.setText(" ");
				
				
				cname=tcanName.getText();
				cqal=tcanQua.getText();
				CanGetSet cg=new CanGetSet();
				cemail1=temail.getText();
				cdes=tdes.getText();
				tarea=textArea.getText();
				try{
				tphno1=Long.parseLong(tphno.getText());}catch(Exception t){ephno.setText("enter valid ph no");}
				
							
						//---emaill validation
					if(cemail1==null)
					{email.setText("Please Enter valid Email");}
					else{
					rgs=new RegGetSet(tphno1,cemail1);
					rgs.setemail1(cemail1);
							if(!(rgs.authenticateemail1()))
					{email.setText("Please Enter valid Email");}
		
					}
						//---phone number validation
					if(tphno1==0){ephno.setText("enter valid ph no");}
					else{
					i1=rgs.numdigits(tphno1); 
					if(!(rgs.authenticatephno1(i1)))
					{ephno.setText("Please Enter valid Phone no");}}
					if(b==null)
					{egender.setText("Please choose gender");}
			
			
				
				
				if(rgs.authenticatephno1(i1) && rgs.authenticateemail1())
				{
				
					try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
						try{fin=new FileInputStream("C:\\java1\\jdk1.8.0_05\\ElectionVoting\\canimg\\ot3.png");}
						catch(Exception er){{System.out.println("image not found");}}
				
					PreparedStatement ps = con.prepareStatement("update candidate set c_name=?,c_qualification=?,email=?,phone_no=?,PROFILE_PIC=?,C_DESCRIPTION=? where c_id="+mid);
					ps.setString(1,cname);ps.setString(2,cqal);ps.setString(3,cemail1);ps.setLong(4,tphno1);ps.setString(6,cdes);
					ps.setBinaryStream(5,fin,fin.available());
					int i=ps.executeUpdate();  
					System.out.println(i+" records affected");  
					dispose();
			
					}
					catch(Exception e2) { e2.printStackTrace(); }
				}
				else{System.out.println("please enter valid info");}
				
			
		}
	}
	
	public static void main(String []args)
	{
	ModifyCandidateDetails mcd=new ModifyCandidateDetails(10);
	}

}