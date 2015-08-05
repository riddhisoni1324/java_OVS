package com.election;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.io.*;
public class CandidateInfo extends JFrame {
String cname,cqal,cdes,cname1,cqal1,pname,cons_name;
	int cid,conid,pid;
        final String card1Text = "Card 1";
        final String card2Text = "Card 2";
        final String card3Text = "Card 3";
        final JPanel cards; //a panel that uses CardLayout
        // button commands
        final String FIRST = "FIRST";
        final String NEXT = "NEXT";
        final String PREVIOUS = "PREVIOUS";
        final String LAST = "LAST";
		int counter=0;
		Connection con;

 public CandidateInfo()
 {
 		
        super("Candidate Information");
					this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });

		cards = new JPanel(new CardLayout());
    //    setSize(900,900);
		setAlwaysOnTop(true);
		setResizable(false);
	final JLabel l = new JLabel("Candidate Information");

    Font myFont = new Font("Serif", Font.ITALIC, 15);
    Font newFont = myFont.deriveFont(50F);

    l.setFont(newFont);
	l.setBounds(60,10,500,50);
    add(l);
	
 		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	try
	{
	Class.forName("oracle.jdbc.driver.OracleDriver");  
	con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
	if(!con.isClosed()){ System.out.println("Connection Successful"); }
	else{ System.out.println("Connection is Closed"); }
					try{
					String sqlsize ="select * from candidate";
    				PreparedStatement preStatementsize = con.prepareStatement(sqlsize);
				
					ResultSet resultsize = preStatementsize.executeQuery();
					while(resultsize.next()){
					counter++;
					}
					System.out.println("count is= "+counter);

					}
					
					catch(Exception e5)
					{
					e5.printStackTrace();
					}
	
	
		
		JPanel card[]=new JPanel[counter];
		for (int i=0;i<counter;i++)
				{				try{ 
				
					int j=i+1;
					String sql ="select * from candidate c,party p,constituency co where c.pid=p.pid and c.cons_id=co.cons_id and  c_id="+j;
					
    				PreparedStatement preStatement = con.prepareStatement(sql);
				
					ResultSet result = preStatement.executeQuery();
					
								while(result.next()){
								
									cid=result.getInt("c_id");
									conid=result.getInt("cons_id");
									cname=result.getString("c_name");
									cqal=result.getString("c_qualification");
									cdes=result.getString("c_description");
									pid=result.getInt("pid");
									pname=result.getString("pname");
									Blob b=result.getBlob(8);//6 means 6 th column data  
									Blob b1=result.getBlob(16);//party symbol  
									cons_name=result.getString("cons_name");
									byte barr[]=b.getBytes(1,(int)b.length());//1 means first image  
									String filepathforFileOutput="C:\\java1\\jdk1.8.0_05\\ElectionVoting\\canimg\\get\\"+(new Integer(cid).toString()) + ".png";
									FileOutputStream fout=new FileOutputStream(filepathforFileOutput);  
									fout.write(barr);  
									fout.close();  
									byte barr1[]=b1.getBytes(1,(int)b1.length());//1 means first image  
									String filepathforFileOutput1="C:\\java1\\jdk1.8.0_05\\ElectionVoting\\canimg\\get\\"+(new Integer(pid+"100").toString()) + ".png";
									FileOutputStream fout1=new FileOutputStream(filepathforFileOutput1);  
									fout1.write(barr1);  
									fout1.close();  
									
									
									JPanel c=new JPanel();
									c.setLayout(null);
									JLabel jl=new JLabel("Constituency id:"+String.valueOf(conid)+"  "+cons_name);
									JLabel jl1=new JLabel("Candidate id: "+String.valueOf(cid));
									JLabel jl2=new JLabel("Candidate name: "+cname);
									JLabel jl3=new JLabel("Candidate qal: "+cqal);
									JLabel jl4=new JLabel("Candidate desc: "+cdes);
									JLabel jl5=new JLabel("Profile image");
									JLabel jl6=new JLabel("Party id: "+pid);
									JLabel jl7=new JLabel("Party Name: "+pname);
									JLabel jl8=new JLabel("Party Symbol");
									ImageIcon i2=new ImageIcon(filepathforFileOutput);
									ImageIcon i21=new ImageIcon(filepathforFileOutput1);
									
									jl5.setIcon(i2);
									jl8.setIcon(i21);
									jl.setBounds(100,100,300,30);
									jl1.setBounds(100,150,300,30);
									jl2.setBounds(100,200,300,30);
									jl3.setBounds(100,250,300,30);
									jl4.setBounds(100,300,300,30);
									jl5.setBounds(400,150,128,128);
									jl6.setBounds(100,350,300,30);
									jl7.setBounds(100,400,300,30);
									jl8.setBounds(400,350,64,64);
									
									c.add(jl);
									c.add(jl1);
									c.add(jl2);
									c.add(jl3);
									c.add(jl4);
									c.add(jl5);
									c.add(jl6);
									c.add(jl7);
									c.add(jl8);
									card[i]=c;
									cards.add(card[i],cname);
									
								}
					
					}
					catch(Exception e2) { e2.printStackTrace(); }
					
		
				}
		}
	catch(Exception e){}
		
		
		
	
        class ControlActionListenter implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                String cmd = e.getActionCommand();
                if (cmd.equals(FIRST)) {
                    cl.first(cards);
                } else if (cmd.equals(NEXT)) {
                    cl.next(cards);
                } else if (cmd.equals(PREVIOUS)) {
                    cl.previous(cards);
                } else if (cmd.equals(LAST)) {
                    cl.last(cards);
                }
            }
        }
        ControlActionListenter cal = new ControlActionListenter();
 
        JButton btn1 = new JButton("First");
        btn1.setActionCommand(FIRST);
        btn1.addActionListener(cal);
 
        JButton btn2 = new JButton("Next");
        btn2.setActionCommand(NEXT);
        btn2.addActionListener(cal);
 
        JButton btn3 = new JButton("Previous");
        btn3.setActionCommand(PREVIOUS);
        btn3.addActionListener(cal);
 
        JButton btn4 = new JButton("Last");
        btn4.setActionCommand(LAST);
        btn4.addActionListener(cal);
 
        JPanel controlButtons = new JPanel();
        controlButtons.add(btn1);
        controlButtons.add(btn2);
        controlButtons.add(btn3);
        controlButtons.add(btn4);
 
        Container pane = getContentPane();
        pane.add(cards, BorderLayout.CENTER);
        pane.add(controlButtons, BorderLayout.PAGE_END);
 
        setBounds(225,20,600, 600);
        setVisible(true);
 }
    public static void main(String[] args) {
	CandidateInfo ci=new CandidateInfo();
	
    }
}