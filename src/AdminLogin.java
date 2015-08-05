package com.election.admin;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import com.election.admin.*;
import com.election.*; 
public class AdminLogin extends JDialog {
 
    public JTextField tfUsername;
    public JPasswordField pfPassword;
    public JLabel lbUsername;
    public JLabel lbPassword;
    public JButton btnAdminLogin;
    public JButton btnCancel;
    public boolean succeeded;
 
    public AdminLogin() {
		setAlwaysOnTop(true);
		setResizable(false);
		setSize(getPreferredSize());	
		this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });

		setVisible(true);
		setBounds(100, 100, 600, 600);
		
        JPanel panel = new JPanel();
        
        lbUsername = new JLabel("Username: ");
        panel.add(lbUsername);
 
        tfUsername = new JTextField(20);
        panel.add(tfUsername);
 
        lbPassword = new JLabel("Password: ");
        panel.add(lbPassword);
 
        pfPassword = new JPasswordField(20);
        panel.add(pfPassword);
        
        btnAdminLogin = new JButton("AdminLogin");
 
        btnAdminLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (Login.authenticate(getUsername(), getPassword())) {
                    JOptionPane.showMessageDialog(AdminLogin.this,
                            "Hi " + getUsername() + "! You have successfully logged in.",
                            "AdminLogin",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
					AdminScreen as=new AdminScreen();
                } else {
                    JOptionPane.showMessageDialog(AdminLogin.this,
                            "Invalid username or password",
                            "AdminLogin",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    tfUsername.setText("");
                    pfPassword.setText("");
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
        bp.add(btnAdminLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
       // setLocationRelativeTo(parent);
    }
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
	
		public static void main(String[] args) {
		try {
			AdminLogin dialog = new AdminLogin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


class Login {
 
    public static boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("admin") && password.equals("OVS@dmin")) {
            return true;
        }
        return false;
    }
}