package com.election.voter;
import com.election.voter.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

 
public class NewRegistrationTrialLoginWindow extends JDialog {
 
    private JTextField tfUsername,tfOTP;
	String pass;
    private JLabel lbUsername,lbOTP,OTPSendInfo;
    private JButton btnNewRegistration,btnAfterOtp,btnCancel;
    private boolean succeeded;
	int dbOtp;long uid;String email;
    public NewRegistrationTrialLoginWindow() {
		setVisible(true);
		setAlwaysOnTop(true);
		setBounds(100, 100, 400, 150);

        JPanel panel = new JPanel();
        
        lbUsername = new JLabel("Aadhar id: ");
        panel.add(lbUsername);
		
        tfUsername = new JTextField(10);
        panel.add(tfUsername);
 
        lbOTP = new JLabel("OTP: ");
        panel.add(lbOTP);
		lbOTP.setVisible(false);
        tfOTP = new JTextField(10);
        panel.add(tfOTP);
		tfOTP.setVisible(false);
	    btnNewRegistration = new JButton("Enter");
		btnAfterOtp=new JButton("After OTP Recieve");
		btnAfterOtp.setVisible(false);
			
        btnNewRegistration.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
			InsertOTP o=new InsertOTP(getUsername());
			o.insert();
			      
						try{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con1.isClosed()){ System.out.println("Connection Successful"); }
					else{ System.out.println("Connection is Closed"); }
					
					String sql1 ="select * from voterinfo where vid="+getUsername();
					
    				PreparedStatement preStatement1 = con1.prepareStatement(sql1);
				//---get emailid from db
					ResultSet result1 = preStatement1.executeQuery();
								while(result1.next()){
									pass=result1.getString("vpassword");
									email=result1.getString("vemailid");;
									System.out.println("Current Data from Oracle : " +   email+
									" "+pass);
								}
								
								if(pass!=null)
								{
								JOptionPane.showMessageDialog(NewRegistrationTrialLoginWindow.this,
								"Hi " + getUsername() + "! You have already  registered in.",
								"NewRegistrationTrialLoginWindow",
								JOptionPane.INFORMATION_MESSAGE);
									//succeeded = true;
								//dispose();
								
								}
								
								
                
								
								
								
					String sql ="select * from OTPvoter where vid="+getUsername();
    				PreparedStatement preStatement = con1.prepareStatement(sql);
				
					ResultSet result = preStatement.executeQuery();
								while(result.next()){
									uid=result.getLong("vid");
									dbOtp=result.getInt("otp");;
									System.out.println("Current Data from Oracle : " +   uid+" "+dbOtp);
								}
					System.out.println("\n\nAfter inserted record\n\n");			
					GmailSender.TO=email;
					GmailSender.TEXT="hiii, "+getUsername()+" here's your OTP for Registration:"+dbOtp+"  Kindly Note this is for ONE TIME USE ONLY";
					 GmailSender.send();
					System.out.println("Mail sent successfully!");
					JOptionPane.showMessageDialog(NewRegistrationTrialLoginWindow.this,
                            "Hi " + getUsername() + "! your OTP is successfully sent to your email id",
                            "Acknowlegdment",
                            JOptionPane.INFORMATION_MESSAGE);
					lbOTP.setVisible(true);
					tfOTP.setVisible(true);
			
					
					
					
					
					btnNewRegistration.setVisible(false);
					btnAfterOtp.setVisible(true);
					}
					catch(Exception e4)
					{
					e4.printStackTrace();
					}
			
            }
        });
        btnAfterOtp.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
			
					
		        if (dbOtp==getOtp()) {
                    JOptionPane.showMessageDialog(NewRegistrationTrialLoginWindow.this,
                            "Hi " + getUsername() + "! You have successfully registered in."+
							"KINDLY CHANGE YOUR PASSWORD RIGHT NOW OTHERWISE YOU WILL HAVE TO LOGIN THROUGH OTP ONLY NEXT TIME.....",
                            "NewRegistrationTrialLoginWindow",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();

					AadharDetails vs=new AadharDetails(getUsername());
                } else {
                    JOptionPane.showMessageDialog(NewRegistrationTrialLoginWindow.this,
                            "Invalid username or OTP",
                            "NewRegistrationTrialLoginWindow",
                            JOptionPane.ERROR_MESSAGE);
                    tfUsername.setText("");
                    succeeded = false;
 
                }
			}
			});

        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnNewRegistration);
        bp.add(btnCancel);
		bp.add(btnAfterOtp);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        setResizable(true);
       }
 
    public long getUsername() {
        return Long.parseLong(tfUsername.getText());
    }
	 public int getOtp() {
        return Integer.parseInt(tfOTP.getText());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
	
		public static void main(String[] args) {
		try {
			NewRegistrationTrialLoginWindow dialog = new NewRegistrationTrialLoginWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}