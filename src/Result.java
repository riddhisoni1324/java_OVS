package com.election.admin;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Result extends JFrame {
    JButton jButton1,jButton2,jButton3,jButton4;
	String action;
    	BufferedImage bf ;
    public Result() {
	setVisible(true);
	setBounds(100,100,800,600);
	setLayout(null);
	setAlwaysOnTop(true);
	setResizable(false);
	final JLabel l = new JLabel("Election Result");

    Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 15);
    Font newFont = myFont.deriveFont(50F);

    l.setFont(newFont);
    
	try{
	 bf = ImageIO.read(new File("icons/report1.png"));}catch(Exception e){}
 
// adding created component to the JFrame using my backImage class
 
 
this.setContentPane(new backImage(bf));

	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jButton1=new JButton("Candidate Votes");
		jButton2=new JButton("Winner: Constituency Wise");
		jButton3=new JButton("Total Votes Casted: Constituency Wise");
		jButton4=new JButton("Total Winning Seats: Party Wise");
	
	    jButton1.setBounds(400,150,300,50);
		jButton2.setBounds(400,250,300,50);
		jButton3.setBounds(400,350,300,50);
		jButton4.setBounds(400,450,300,50);
		l.setBounds(230,20,500,50);
		jButton1.setActionCommand("Candidatevotes");
		jButton2.setActionCommand("winner");
		jButton3.setActionCommand("r_cons");
		jButton4.setActionCommand("party_seats");
        
        add(jButton1);add(jButton2);add(jButton3);add(jButton4);add(l);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
			 action = evt.getActionCommand();
			TableFromDatabase frame = new TableFromDatabase(action);
        
            }
        });
		
		jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
			action = evt.getActionCommand();
			TableFromDatabase frame = new TableFromDatabase(action);
			//System.out.println("from client---");
        
            }
        });jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
			action = evt.getActionCommand();
			TableFromDatabase frame = new TableFromDatabase(action);
			//System.out.println("from client---");
        
            }
        });jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
			//System.out.println("from client---");
			action = evt.getActionCommand();
			TableFromDatabase frame = new TableFromDatabase(action);
        
            }
        });
        }

    
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Result nw=new Result();
				}
        });
    }

}
class backImage extends JComponent {
 
Image i;
 
//Creating Constructer
public backImage(Image i) {
this.i = i;
 
}
 
//Overriding the paintComponent method
@Override
public void paintComponent(Graphics g) {
 
g.drawImage(i, 0, 0, null);  // Drawing image using drawImage method
 
}
}


