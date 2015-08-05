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
import java.util.*;
import javax.swing.*;
import java.text.*;

 
public class NewRegistration extends JFrame implements ActionListener,ItemListener
{	String name,email,add,pass,pass1;
	String getpass,gdate;
	int cons,conid1; long uid,phno;
	JButton chngepass;
	String aname,aemail,aadd,apass,apass1;
	String agetpass;
	int acons; long auid,aphno;
    JLabel lname, laddress, lemail,lphno,luid,lpass,lpass1,lconsid,lbdate,lgender,ldate,lpass2;
	JLabel lerr1,lerr2,ejc;
    JTextField tname,temail,tphno,tuid,tpass,tpass1,tconsid,tdate;
    JButton bsubmit,bdate;
    JTextArea taddress;
	ButtonGroup group=null;
	JRadioButton bFemale,bMale;
	JRadioButton radioButton;
		 java.text.SimpleDateFormat sfd;
                java.util.Date ud1;
				long ms;
				java.sql.Date sqld;
				JComboBox jc;	 
	

    NewRegistration()
    {	setAlwaysOnTop(true);
        setTitle("Form");
        setVisible(true);
        setBounds(0,0,900,800);
        setLayout(null);
		setResizable(false);
		
					this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });

	
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 
        lname = new JLabel("Name:");    lemail = new JLabel("Email:");   lphno = new JLabel("Phone No:");
		luid=new JLabel("Aadhar Id:");  	laddress=new JLabel("Address:");	lpass=new JLabel("Password:");
		lpass1=new JLabel("Conform Password:"); 	lconsid=new JLabel("Constituency Id:");
		lerr1=new JLabel("");
		lerr2=new JLabel("");
        bsubmit = new JButton("Submit");
		lbdate=new JLabel("Birth Date");
		lgender=new JLabel("Gender");
			lpass2=new JLabel(" ");
			ejc=new JLabel(" ");jc = new JComboBox(); 
    jc.addItem("1.Ahemdabad"); 
    jc.addItem("2.Baroda"); 
    jc.addItem("3.Rajkot"); 
    jc.addItem("4.Surendranagar"); 
	jc.addItem("5.Surat");
	jc.addItem("6.Junagadh");
	jc.addItem("7.Bharuch");
	jc.addItem("8.Ankleshwar");
	jc.addItem("9.Nadiad");
	jc.addItem("10.Vapi");
    jc.addItemListener(this); 
	jc.setSelectedItem("1.Ahemdabad");
	
	String s2 = "1.Ahemdabad"; 
	
			System.out.println("selected itemis "+s2);
				ejc.setText("Selected Consituency "+s2);
				conid1=Integer.parseInt(s2.substring(0,1));
				System.out.println("selected con is ...."+conid1);
				
                         
	

				
		tname = new JTextField();
		taddress=new JTextArea("Address",5,10);
		tdate=new JTextField();
		JScrollPane scroll = new JScrollPane (taddress);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
         scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		taddress.setEditable(true);
		taddress.setLineWrap(true);
		bMale = new JRadioButton("Male");
		bFemale = new JRadioButton("Female");
		group = new ButtonGroup();
		group.add(bFemale); group.add(bMale);
		temail=new JTextField();  tphno = new JTextField(); tuid = new JTextField(); tpass = new JPasswordField();
		tpass1 = new JPasswordField(); 	tconsid=new JTextField();
		bdate=new JButton("select date");
		lname.setBounds(100, 70, 200, 30); 
		laddress.setBounds(100, 110, 200, 30);
        lemail.setBounds(100, 200, 200, 30);
		lphno.setBounds(100, 240, 200, 30);
        luid.setBounds(100, 280, 200, 30);
		lpass.setBounds(100,440,200,30);
		lpass1.setBounds(100,480,200,30);
		lconsid.setBounds(100,400,200,30);
		lerr1.setBounds(200,550,200,100);
		lerr1.setBounds(200,600,200,100);
		lbdate.setBounds(100,320,200,30);
		tdate.setBounds(400,320,200,30);
		bdate.setBounds(600,320,150,30);
		lgender.setBounds(100,360,200,30);
        tname.setBounds(400, 70, 200, 30);
        scroll.setBounds(400, 110, 200, 80);
        temail.setBounds(400, 200, 200, 30);
		tphno.setBounds(400, 240, 200, 30);
        tuid.setBounds(400, 280, 200, 30);
		tpass.setBounds(400,440,200,30);
		tpass1.setBounds(400,480,200,30);
		lpass2.setBounds(650,460,300,30);

		jc.setBounds(400,400,200,30);
		ejc.setBounds(620,400,200,30);
		
		bMale.setBounds(400,360,100,30);
		bFemale.setBounds(500,360,100,30);
		
		bsubmit.setBounds(500,600,100,30);
		chngepass=new JButton("Change password");					
		chngepass.setBounds(100,600,200,30);
		add(chngepass);

	    add(lname); add(laddress); add(lemail);  add(lphno); add(luid); add(lpass); add(lpass1); add(lconsid);
		add(lerr1);	add(lerr2); add(bsubmit);
		add(lgender);add(lbdate);
		add(tname);  add(scroll);  add(temail); add(tphno); add(tuid); add(tconsid); add(tpass); 	add(tpass1);
        add(bsubmit);add(tdate);add(lpass2);
		add(bFemale);add(bMale);add(bdate);add(jc);add(ejc);
        bsubmit.addActionListener(this);
		bdate.addActionListener(this);
		chngepass.addActionListener(this);
		bFemale.setActionCommand("Female");
		bMale.setActionCommand("male");
		tdate.setEditable(false);
		chngepass.setVisible(false);
		 
    }
	
	 public void itemStateChanged(ItemEvent ie) { 
	 
    if(ie.getSource().equals(jc))
            {
  String s1 = (String)ie.getItem(); 
				
			System.out.println("selected itemis "+s1);
				ejc.setText("Selected Consituency "+s1);
				conid1=Integer.parseInt(s1.substring(0,1));
				System.out.println("selected con is ...."+conid1);
				
                         }
					
  }
	

	public void actionPerformed(ActionEvent e)  
	{
	
			if(e.getSource()==bdate)
		{		
		   tdate.setText(new DatePicker(this).setPickedDate());
              
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
				 cons=conid1;
				 gdate=tdate.getText();
				  sfd= new SimpleDateFormat("dd/MM/yyyy");
				  try{
              ud1= sfd.parse(gdate);
			  }
			  catch(Exception r5)
			  {
			  r5.printStackTrace();
			  }
				ms = ud1.getTime();
				sqld= new java.sql.Date(ms);

		
				
				ButtonModel b = group.getSelection();
				String t = "Not selected";
				if (b!=null) t = b.getActionCommand();
				System.out.println("dsfsdf......."+t);
				
				
                RegGetSet rgs=new RegGetSet(phno,email);
				
				 rgs.setname1(name);
				 System.out.println("name from get set "+rgs.getname1());
				 rgs.setname1(name); rgs.setemail1(email); rgs.setphno1(phno); rgs.setuid1(uid); rgs.setadd1(add); 
				 rgs.setpass1(pass); rgs.setcons1(cons);
		 
					 try{
					  getpass=rgs.setpass2(pass);
					 System.out.println("encrepted pas"+getpass);
					 }
					 catch(Exception r2)
					 {
					 r2.printStackTrace();
					 }System.out.println("\n\n Information before entering into database throgh get and set "+rgs.getname1()+" "+rgs.getemail1()+ " "+rgs.getphno1()+" "+rgs.getuid1()+" "+rgs.getadd1()+" "+rgs.getpass1()+" "+rgs.getcons1() );
					 
						 System.out.println("ph no is="+rgs.getphno1()); long p1=rgs.getphno1(); System.out.println("ph no is long="+p1);
						int i1=rgs.numdigits(p1);System.out.println("Mobile no digit is"+i1);String s1=rgs.getemail1();
						System.out.println("get mail iss"+s1);boolean b1=rgs.authenticateemail1();System.out.println("email l"+b1);
							
							
						if(!(rgs.checkPass(pass,pass1)))
						{
							lpass.setText("Please Enter valid password in aadhar details");
				
						}
							
			if(rgs.authenticatephno1(i1) && rgs.authenticateemail1() && rgs.checkPass(pass,pass1))
				{
		 
					try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					PreparedStatement ps = con.prepareStatement("insert into voterInfo (v_name, vemailid, phone_no, vid,vaddress,vpassword,cons_id,BIRTHDATE,GENDER) values (?, ?, ?, ?,?,?,?,?,?)");
					
					//--setting all values in database
					ps.setString(1,name);ps.setString(2,email);ps.setLong(3,phno);ps.setLong(4,uid);ps.setString(5,add);ps.setString(6,getpass);ps.setInt(7,cons);ps.setDate(8,sqld);ps.setString(9,t);

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
									apass=result.getString("vpassword");
									acons=result.getInt("cons_id");
									auid=result.getLong("vid");
									aphno=result.getLong("phone_no");;
									System.out.println("Current Data from Oracle : " +   aname+" "+aemail +" "+aadd +" "+apass+" "+acons+" "+auid+" "+aphno    );
								}
					System.out.println("\n\nAfter inserted record\n\n");			
					args.setname1(aname);args.setemail1(aemail);args.setphno1(aphno);args.setuid1(auid);args.setadd1(aadd);args.setpass1(apass);args.setcons1(acons);

					System.out.println("\n\nfetching value from database using get and set method \n\n"+args.getname1()+" "+args.getemail1()+ " "+args.getphno1()+" "+args.getuid1()+" "+args.getadd1()+" "+args.getpass2(apass)+" "+args.getcons1() );
			 
						
					}
					catch(Exception e2) { e2.printStackTrace(); }
						
	
				}
			else 
			{System.out.println("Please enter valid info");}
		
		
	}
	}
 
    public static void main(String arr[])
    { NewRegistration na=new NewRegistration();}
}

