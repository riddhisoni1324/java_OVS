package com.election.voter;
import com.election.voter.*;
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



public class CastVote extends JFrame implements ActionListener{
int cid,pid,getconsid;
long voterid;
String CandidateChoosen;
String cname;
	JButton[] bt;
	ArrayList <JButton> al=new ArrayList<JButton>();
	HashMap<Integer,Blob> partyS=new HashMap<Integer,Blob>();
	private JPanel contentPane;
	Boolean b=false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CastVote frame = new CastVote(10);
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
	public CastVote(long id) {
	setAlwaysOnTop(true);
		setResizable(false);
				this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });

					voterid=id;
					try{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con1.isClosed()){ System.out.println("Connection Successful"); }
					String sqlforconsid1 ="select vid from checkvote where vid="+voterid;
					PreparedStatement psconsid1 = con1.prepareStatement(sqlforconsid1);
					ResultSet retconsid1=psconsid1.executeQuery();
					if(retconsid1.next())
					{
					JOptionPane.showMessageDialog(CastVote.this,
                            "Hi You have Already done voting....",
                            "Message",
                            JOptionPane.INFORMATION_MESSAGE);
					
					b=true;
					}
					
					}
					catch(Exception r4){}
					
					
	
		if(!b)
		{
		setVisible(true);
		voterid=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(0, 0, 900, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); 
					
					
					String sqlforconsid ="select cons_id from voterinfo where vid="+voterid;
					PreparedStatement psconsid = con.prepareStatement(sqlforconsid);
					ResultSet retconsid=psconsid.executeQuery();
					while(retconsid.next())
					{
						getconsid=retconsid.getInt("cons_id");	
					}
					
					System.out.println("con id retrive is = "+getconsid+" "+voterid);
					
					
					String sql ="select c_id,c_name,cons_id,pid from candidate where cons_id="+getconsid;
					int c=1;
					String retriveParty="select pid,psymbol from party where pid in(select pid from candidate where cons_id="+getconsid+")";
					PreparedStatement psRetrieveParty = con.prepareStatement(retriveParty);
					ResultSet retrivedParties=psRetrieveParty.executeQuery();
					
					while(retrivedParties.next())
						{
						int partyId=retrivedParties.getInt("pid");
						Blob partySymbol=retrivedParties.getBlob(2);
						
						partyS.put(new Integer(partyId),partySymbol);
						}
					final JLabel l = new JLabel("Candidate for Your Constituency");

    Font myFont = new Font("Serif", Font.ITALIC, 8);
    Font newFont = myFont.deriveFont(50F);

    l.setFont(newFont);
	l.setBounds(60,10,750,50);
    add(l);
	PreparedStatement preStatement = con.prepareStatement(sql);
				
					ResultSet result = preStatement.executeQuery();
					int i=0;
					while(result.next()){
								cid=result.getInt("c_id");
								cname=result.getString("c_name");
								pid=result.getInt("pid");
								System.out.println("Current Data from Oracle : "+i+"cid is= " +  cid +"cname is= "+cname +"pid is= "+pid);
									
								Blob b=partyS.get(new Integer(pid));
								byte barr[]=b.getBytes(1,(int)b.length());
				
								String filepathforFileOutput="C:\\java1\\jdk1.8.0_05\\OVS\\res\\partysymbol\\"+(new Integer(pid).toString())+".png";
								FileOutputStream fout=new FileOutputStream(filepathforFileOutput);  
								fout.write(barr);  
											  
								fout.close();  
								ImageIcon i2=new ImageIcon(filepathforFileOutput);
										
								JButton jb=new JButton(String.valueOf(cid),i2);
										jb.setBounds(300,100*(i+1),64,64);
										al.add(jb);
										add(jb);
										
										String t=(new Integer(cid).toString())+"     "+cname;
										JLabel jl=new JLabel(t);
										
										jl.setBounds(100,100*(i+1),100,50);
										add(jl);
																
										
										bt = new JButton[al.size()];
										al.toArray(bt);
																			
										bt[i].addActionListener(this);							
								i++;
								}
													
					}
					else{ System.out.println("Connection is Closed"); }
										
					}
					catch(Exception e2) { e2.printStackTrace(); }
					
					}
	
			
	}
	
	public void actionPerformed(ActionEvent e)  
				{
				int j;
				
					System.out.println("candidate bt.length-"+bt.length);
					for(j=0;j<bt.length;j++)
					{
					
					if(e.getSource()==bt[j])
					{
						CandidateChoosen=bt[j].getText();
						bt[j].removeActionListener(this);
					
					
					}
					
					bt[j].setEnabled(false);
					

					}
					System.out.println("candidate choosen"+CandidateChoosen);
					System.out.println("befor enter "+j+"......"+voterid+" "+Integer.parseInt(CandidateChoosen));
					VotedDB vdb=new VotedDB(voterid,Integer.parseInt(CandidateChoosen));
					System.out.println("end of cast vote");

					
				}
					
					


}
