package com.election.voter;
import com.election.voter.*;
import com.election.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.*;


public class VoterScreen extends JFrame implements ActionListener {
	private long vid;
	private JPanel contentPane;
	private JButton bModifyVoterD;
	private JButton bCandidateD;
	private JButton bCastVote;
	private JButton bhelp,blogout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoterScreen frame = new VoterScreen(100L);
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
	public VoterScreen(long id) {
		vid=id;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Toolkit tk=Toolkit.getDefaultToolkit();
		int xsize=(int)tk.getScreenSize().getWidth();
		int ysize=(int)tk.getScreenSize().getHeight();
		System.out.println(xsize+"and"+ysize);
		setSize(xsize,ysize);
		
		setVisible(true);
		setAlwaysOnTop(true);
		setResizable(false);
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon b=new ImageIcon("icons//banner.gif");
		JLabel bannerl=new JLabel(b);
		bannerl.setBounds(0,0,1366,135);
		contentPane.add(bannerl);
		blogout=new JButton("Logout");
		blogout.setBounds(1200,150,125,30);
		blogout.addActionListener(this);
		contentPane.add(blogout);
		
		bModifyVoterD = new JButton(new ImageIcon("icons//Modify-Voter-icon.png"));
		bModifyVoterD.setBounds(350,150,256,256);
		contentPane.add(bModifyVoterD);
		
		bCandidateD = new JButton(new ImageIcon("icons//Candidate-icon.png"));
		bCandidateD.setBounds(750,150,256,256);
		contentPane.add(bCandidateD);
		
		bCastVote = new JButton(new ImageIcon("icons//Cast-Vote-icon.png"));
		bCastVote.setBounds(350,450,256,256);
		contentPane.add(bCastVote);
		
		bhelp = new JButton(new ImageIcon("icons//help-icon.png"));
		bhelp.setBounds(750,450,256,256);
		contentPane.add(bhelp);
		
		bModifyVoterD.addActionListener(this);
		bCastVote.addActionListener(this);
		bCandidateD.addActionListener(this);
		bhelp.addActionListener(this);
		 
		 
    }
	

	public void actionPerformed(ActionEvent e)  
	{
		if(e.getSource()==bModifyVoterD)
		{
		ModifiyVoterDetails mvd=new ModifiyVoterDetails(vid);
		}
		if(e.getSource()==bCastVote)
		{
		CastVote cv=new CastVote(vid);
		}
		if(e.getSource()==bCandidateD)
		{
		CandidateInfo ci=new CandidateInfo(); 
		}
		if(e.getSource()==bhelp)
		{
		Help h=new Help();}
		if(e.getSource()==blogout)
		{
		dispose();
		MainWindow mvd1=new MainWindow();
		}
}

}
