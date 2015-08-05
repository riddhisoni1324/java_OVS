package com.election.admin;
import com.election.admin.*;
import com.election.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.sql.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.border.EmptyBorder;
import java.util.*;



public class PreModify extends JFrame implements ActionListener{

	ArrayList<Integer> al=new ArrayList<Integer>();//creating arraylistprivate JPanel contentPane;
	Boolean b=false;
	JLabel lid;
	JButton bcaninfo,bmodify;
	JTextField tid;
	
	int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PreModify frame = new PreModify();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PreModify() {
					
					
	
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 400, 300);
		setLayout(null);
		setAlwaysOnTop(true);
		
		setResizable(false);
						this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });


		
	 lid=new JLabel("Candidate id");
	 bcaninfo=new JButton("Candidate Info");
	 tid=new JTextField();
	 bmodify=new JButton("Modify Info");
	 lid.setBounds(50,100,100,30);
	 tid.setBounds(200,100,100,30);
	 bcaninfo.setBounds(50,200,150,30);
	 bmodify.setBounds(210,200,150,30);
	 bcaninfo.addActionListener(this);bmodify.addActionListener(this);
	
		add(lid);	add(tid);	add(bcaninfo);	add(bmodify);
	
			
	}
	
	public void actionPerformed(ActionEvent e)  
				{
				if(e.getSource()==bcaninfo)
				{CandidateInfo ci1=new CandidateInfo();}
				
				if(e.getSource()==bmodify)
				{
				try{
				id=Integer.parseInt(tid.getText());
				}catch(Exception e1)
				{					   System.out.println("invalid input...do enter proper value");
				}
				
				
				try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){  
					
					
					
					
						String retriveParty="select c_id from candidate";
						PreparedStatement psRetrieveParty = con.prepareStatement(retriveParty);
						ResultSet retrivedParties=psRetrieveParty.executeQuery();
						
						while(retrivedParties.next())
							{
							int mid=retrivedParties.getInt("c_id");
							
							al.add(new Integer(mid));
							}
							Iterator itr=al.iterator();//getting Iterator from arraylist to traverse elements  
							  while(itr.hasNext()){  
							   System.out.println(itr.next());  
							  }  
								

									boolean retval = al.contains(id); 
	  
									   if (retval == true) {
									   System.out.println("element  is contained in the list");
									   ModifyCandidateDetails mcd=new ModifyCandidateDetails(id);
									   }
									   else {
									   System.out.println("element ..... is not contained in the list");
									   }
							
						
										
					
													
					}
					else{ System.out.println("Connection is Closed"); }
										
					}
					catch(Exception e2) { e2.printStackTrace(); }
					
					}
		
				
}
}
