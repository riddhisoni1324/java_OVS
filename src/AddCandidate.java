package com.election.admin;
import com.election.*;
import com.election.admin.*;
import java.sql.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.text.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import java.awt.event.*;
import javax.swing.filechooser.FileFilter;


public class AddCandidate extends JFrame implements ActionListener,ItemListener {
	String cname,cqal,cdes,cemail1,tarea,t;
	int conid,cid,pid,setlable,conid1,pid1,cage,i1,ageconfrm;
	long tphno1;
	int counter;
	public final JPanel contentPanel = new JPanel();
	public JTextField tcanName,tconId,tcanAge,tcanQua,tpartyId, tcanPic,tphno,temail,tdate,tdes;
	JButton addCan;
	JLabel label,jl,jm,gender,bdate,cphno,cemail,email,ephno,edate,egender,ejc,ejc1,ldec;
	JTextArea textArea;	JButton browse,b1date;
	String filePath=null,filepath2=null,finalpath=null,g1date,g1date1;
	BufferedImage image=null;BufferedImage bi;Image temp;BufferedImage btemp;File f;ButtonModel b;	ButtonGroup group;
	JRadioButton bFemale,bMale;	JRadioButton radioButton;
		 java.text.SimpleDateFormat sfd,sfd1;    java.util.Date ud1,ud11;		long ms,ms1;  			java.sql.Date sqld,sqld1;
	  JComboBox jc,jc1;	 RegGetSet rgs;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddCandidate dialog = new AddCandidate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddCandidate() {
		setAlwaysOnTop(true);
		setBounds(0, 0, 1050, 750);
	
		setVisible(true);
		setResizable(false);
		
		validate();
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
			
		JLabel canid = new JLabel("Candidate ID");label = new JLabel("New label");JLabel canName = new JLabel("Candidate Name ");
		JLabel conId = new JLabel("Constituency ID");JLabel canQua = new JLabel("Candidate Qualification");
		JLabel partyId = new JLabel("Party ID");JLabel canPic = new JLabel("Candidate Picture");
		JLabel caddress = new JLabel("Candidate Address\r\n");
		cphno= new JLabel("Phone no");	cemail=new JLabel("E-mail");addCan = new JButton("Add Candidate");
		jl=new JLabel("");   textArea=new JTextArea("Address",5,10);b1date=new JButton("select date");
		email=new JLabel(" ");ephno=new JLabel(" ");edate=new JLabel(" ");egender=new JLabel(" ");ejc=new JLabel(" ");ejc1=new JLabel(" ");ldec=new JLabel("Candidate description");tdes=new JTextField();
   jc = new JComboBox(); 
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
	
	jc1 = new JComboBox(); 
    jc1.addItem("1.bjp"); 
    jc1.addItem("2.congress"); 
    jc1.addItem("3.bsp"); 
    jc1.addItem("4.cpm"); 
	jc1.addItem("5.ncp"); 
   jc1.addItem("6.sp"); 
   
   jc1.addItemListener(this); 
	jc1.setSelectedItem("1.bjp");
	
	
	String s11 = "1.Ahemdabad"; 
				
				ejc.setText("Selected Consituency "+s11);
				conid1=Integer.parseInt(s11.substring(0,1));
				System.out.println("selected con is ...."+conid1);
	String s21 = "1.bjp"; 
				
				ejc1.setText("Selected Consituency "+s21);
				pid1=Integer.parseInt(s21.substring(0,1));
				System.out.println("selected party is ...."+pid1);
	
   
    
		
		JScrollPane scroll = new JScrollPane (textArea);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		textArea.setEditable(true);
		textArea.setLineWrap(true);
		
			 
		bdate=new JLabel("Birth Date");	gender=new JLabel("gender");bMale = new JRadioButton("Male");bFemale = new JRadioButton("Female");
		group = new ButtonGroup();	group.add(bFemale); group.add(bMale);
		
		tcanName = new JTextField(); tconId = new JTextField();	tcanQua = new JTextField();	tpartyId = new JTextField();
		tcanPic = new JTextField();tphno=new JTextField();temail=new JTextField();	tdate=new JTextField();
			tcanPic.setEditable(false);
	
		browse =new JButton("Browse");
		final JLabel l = new JLabel("Candidate  Details");

    Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 1);
    Font newFont = myFont.deriveFont(20F);
	l.setBounds(250,0,500,15);
		
    l.setFont(newFont);
	contentPanel.add(l);
		canid.setBounds(175, 36, 200, 30);
		canName.setBounds(175, 70, 200, 30);
		conId.setBounds(175, 105, 200, 30);
		ldec.setBounds(175,145,200,30);
		canQua.setBounds(175, 180, 200, 30);
		canPic.setBounds(175, 361, 200, 30);
		caddress.setBounds(175, 256, 200, 30);
		bdate.setBounds(175,400,200,30);
		tdate.setBounds(400,400,150,30);
		b1date.setBounds(600,400,150,30);
		edate.setBounds(780,400,150,30);
	
		gender.setBounds(175,450,200,30);
		bMale.setBounds(400,450,100,30);
		bFemale.setBounds(470,450,100,30);
		egender.setBounds(600,450,200,30);
		
		cphno.setBounds(175,490,200,30);
		cemail.setBounds(175,530,200,30);
		
		tcanName.setBounds(400, 67, 150, 30);
		jc.setBounds(400, 105, 150, 30);
		tdes.setBounds(400,145,150,30);
		ejc.setBounds(600,105,200,30);
		tcanQua.setBounds(400, 177, 150, 30);
		partyId.setBounds(175, 211, 200, 30);
		
		jc1.setBounds(400, 211, 150, 30);
		ejc1.setBounds(600,211,200,30);
		tcanPic.setBounds(400, 358, 150, 30);
		tphno.setBounds(400,490,150,30);
		ephno.setBounds(600,490,200,30);
		temail.setBounds(400,530,150,30);
		email.setBounds(600,530,200,30);
		label.setBounds(400, 36, 46, 30);
		
		addCan.setBounds(165, 650, 150, 30);
		
		scroll.setBounds(400, 256, 150, 63);
		browse.setBounds(600,361,150,30);
		
		jl.setBounds(800,200,256,256);
				
		jl.setVisible(true);
		contentPanel.add(canid); contentPanel.add(label); contentPanel.add(canName); contentPanel.add(conId);contentPanel.add(canQua); contentPanel.add(partyId); contentPanel.add(canPic);contentPanel.add(caddress);contentPanel.add(bFemale);contentPanel.add(bMale);contentPanel.add(tdate);contentPanel.add(b1date);
		
		contentPanel.add(tcanName); contentPanel.add(tconId);  contentPanel.add(tcanQua);contentPanel.add(tpartyId); contentPanel.add(tcanPic); contentPanel.add(addCan);contentPanel.add(scroll);contentPanel.add(browse);contentPanel.add(cphno);contentPanel.add(cemail);contentPanel.add(tphno);contentPanel.add(temail);
			contentPanel.add(jc);contentPanel.add(jc1); contentPanel.add(ejc);contentPanel.add(ejc1); 
 
 
		contentPanel.add(jl);contentPanel.add(email);contentPanel.add(ephno);contentPanel.add(edate);contentPanel.add(egender);
		contentPanel.add(bdate);contentPanel.add(gender);contentPanel.add(ldec);contentPanel.add(tdes);
		addCan.addActionListener(this);	browse.addActionListener(this);	b1date.addActionListener(this);
		bFemale.setActionCommand("Female");	bMale.setActionCommand("Male");
		tdate.setEditable(false);
	
				try{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
				
					String sqlsize ="select * from candidate";
    				PreparedStatement preStatementsize = con.prepareStatement(sqlsize);
				
					ResultSet resultsize = preStatementsize.executeQuery();
					while(resultsize.next()){
					counter++;
					}
					System.out.println("count is= "+counter);
					setlable=++counter;
					}
					
					catch(Exception e5)
					{e5.printStackTrace();}	
					label.setText(String.valueOf(setlable));
				
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
			f=new File("../canimg/ot2.png");
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
			
	
			
	 public void itemStateChanged(ItemEvent ie) { 
	 
    if(ie.getSource().equals(jc))
            {
  String s1 = (String)ie.getItem(); 
				
			System.out.println("selected itemis "+s1);
				ejc.setText("Selected Consituency "+s1);
				conid1=Integer.parseInt(s1.substring(0,1));
				System.out.println("selected con is ...."+conid1);
				
                         }
						 if(ie.getSource().equals(jc1))
            {
  String s2 = (String)ie.getItem(); 
				
			System.out.println("selected itemis "+s2);
				ejc1.setText("Selected Consituency "+s2);
				pid1=Integer.parseInt(s2.substring(0,1));
				System.out.println("selected party is ...."+pid1);
				
                         }
	
  }
  
  

	public void actionPerformed(ActionEvent e)  
	{
		if(e.getSource()==browse)
		{
		try{storeImage();}
		catch(Exception e3) {e3.printStackTrace();}
		}
		if(e.getSource()==b1date)
		{		
		   tdate.setText(new DatePicker(this).setPickedDate());
		      
		}
		if(e.getSource()==addCan)
		{
				 try{
				 
				ephno.setText(" ");
				email.setText(" ");
				edate.setText(" ");
				egender.setText(" ");
				
				label.setText(String.valueOf(setlable));
				cname=tcanName.getText();
				conid=conid1;
				pid=pid1;
				CanGetSet cg=new CanGetSet();
				cemail1=temail.getText();
				cdes=tdes.getText();
				cqal=tcanQua.getText();
				
				try{
				tphno1=Long.parseLong(tphno.getText());}catch(Exception t){ephno.setText("enter valid ph no");}
				tarea=textArea.getText();
				

               //---for date and gender
				g1date=tdate.getText();
				
				System.out.println("datevalidation....."+g1date+"...");
				if(tdate.getText().equals(""))
					{edate.setText("Please choose date");
							System.out.println("datevalidation....."+g1date+"...");
                                }
					else{
				  sfd= new SimpleDateFormat("dd/MM/yyyy");
				  try{ud1= sfd.parse(g1date);
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
				if(ageconfrm<25){edate.setText("Age must be >25");}
					
					System.out.println("sqlid....."+sqld+"time is "+ms);}
					catch(Exception e9){edate.setText("Please choose date");}  
				}
				
				
				
				}
				 catch(Exception r5)
				  {  r5.printStackTrace();	  }
				 	
				
				b = group.getSelection();
				t = null;
				if (b!=null) t = b.getActionCommand();
				System.out.println("dsfsdf......."+t);
				System.out.println("..........."+t +"."+sqld);
				
				}
						
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
					
			
				try{
				if(rgs.authenticatephno1(i1) && rgs.authenticateemail1() && b!=null && !(tdate.getText().equals("")) && ageconfrm>25)
				{
					g1date1=tdate.getText();
				
				System.out.println("datevalidation....."+g1date1+"...");
				  sfd1= new SimpleDateFormat("dd/MM/yyyy");
				  try{ud11= sfd1.parse(g1date1);
					ms1 = ud11.getTime();
					sqld1= new java.sql.Date(ms1);
					System.out.println("sqlid....."+sqld+"time is "+ms);}
					catch(Exception e9){edate.setText("Please choose date");}  
				
					try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					FileInputStream fin=new FileInputStream("C:\\java1\\jdk1.8.0_05\\ElectionVoting\\canimg\\ot2.png");
					
					PreparedStatement ps1 = con.prepareStatement("insert into candidate (c_id,c_name, cons_id,c_qualification,pid,profile_pic,email,gender,birthdate,phone_no,address,C_DESCRIPTION) values (?, ?,?,?,?,?,?,?,?,?,?,?)");
					
					ps1.setInt(1,setlable);ps1.setString(2,cname);ps1.setInt(3,conid);ps1.setString(4,cqal);
					ps1.setInt(5,pid);ps1.setString(7,cemail1);ps1.setString(8,t);ps1.setDate(9,sqld1);
					ps1.setBinaryStream(6,fin,fin.available());ps1.setLong(10,tphno1);ps1.setString(11,tarea);
					ps1.setString(12,cdes);
					
					int i=ps1.executeUpdate();  
					System.out.println(i+" records affected");  
					
					PreparedStatement ps2 = con.prepareStatement("insert into CANDIDATEVOTES (c_id,c_vote) values(?,?)");
					
					ps2.setInt(1,setlable);ps2.setInt(2,0);
					
					int i34=ps2.executeUpdate();  
					System.out.println(i34+" records affected");  
					
					
					}
					catch(Exception e2) { e2.printStackTrace(); }
					
					}
					}catch(Exception e7){System.out.println("ENter proper value");}

			
		}
	}
	

	
	
	