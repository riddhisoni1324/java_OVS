package com.election;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ElectionDetails {

    public ElectionDetails(){

        JFrame frame = new JFrame("ElectionDetails");
		frame.setBounds(0,0, 1050, 900);
					frame.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	frame.dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	frame.dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ frame.dispose();  }
	@Override public void 	windowLostFocus(WindowEvent e){ frame.dispose();  }
  });

		

		JLabel label=new JLabel("<html><br></FONT><font size=\"10\" face=\"Serif\" color=\"blue\"> "+"          Election Details</font><br><br><br>India is a constitutional democracy with a parliamentary system of government, and at the heart of the system is a commitment to hold regular, free and fair elections. These elections determine the composition of the government, the membership of the two houses of parliament, the state and union territory legislative assemblies, and the Presidency and vice-presidency.<br> Elections are conducted according to the constitutional provisions, supplemented by laws made by Parliament. The major laws are Representation of the People Act, 1950 <br><br><font size=\"4\" ><b>Parliament</b></font> <br>   The Parliament of the Union consists of the President, the Lok Sabha (House of the People) and the Rajya Sabha (Council of States). The President is the head of state, and he appoints the Prime Minister, who runs the government, according to the political composition of the Lok Sabha. Although the government is headed by a Prime Minister, the Cabinet is the central decision making body of the government. Members of more than one party can make up a government, and although the governing parties may be a minority in the Lok Sabha, they can only govern as long as they have the confidence of a majority of MPs, the members of the Lok Sabha. As well as being the body, which determines whom, makes up the government, the Lok Sabha is the main legislative body, along with the Rajya Sabha.<br><br><br><font size=\"4\" ><b>Rajya Sabha - The Council of States</b></font><br>The members of the Rajya Sabha are elected indirectly, rather than by the citizens at large. Rajya Sabha members are elected by each state Vidhan Sabha using the single transferable vote system. Unlike most federal systems, the number of members returned by each state is roughly in proportion to their population. At present there are 233 members of the Rajya Sabha elected by the Vidhan Sabhas, and there are also twelve members nominated by the President as representatives of literature, science, art and social services. Rajya Sabha members can serve for six years, and elections are staggered, with one third of the assembly being elected every 2 years. <br><br><br><font size=\"4\" ><b>Nominated members</b></font><br>The president can nominate 2 members of the Lok Sabha if it is felt that the representation of the Anglo-Indian community is inadequate, and 12 members of the Rajya Sabha, to represent literature, science, art and the social services.   <br><br><br><font size=\"4\" ><b>State Assemblies </b></font><br> India is a federal country, and the Constitution gives the states and union territories significant control over their own government. The Vidhan Sabhas (legislative assemblies) are directly elected bodies set up to carrying out the administration of the government in the 28 States of India. In some states there is a bicameral organisation of legislatures, with both an upper and Lower House. Two of the seven Union Territories viz., the National Capital Territory of Delhi and Pondicherry, have also legislative assemblies.  <br>Elections to the Vidhan Sabhas are carried out in the same manner as for the Lok Sabha election, with the states and union territories divided into single-member constituencies, and the first-past-the-post electoral system used. The assemblies range in size, according to population. The largest Vidhan Sabha is for Uttar Pradesh, with 403 members; the smallest Pondicherry, with 30 members.<br>In a number of seats in the Lok Sabha and the Vidhan Sabha, the candidates can only be from either one of the scheduled castes or scheduled tribes. The number of these reserved seats is meant to be approximately in proportion to the number of people from scheduled castes or scheduled tribes in each state. There are currently 79 seats reserved for the scheduled castes and 41 reserved for the scheduled tribes in the Lok Sabha.</html>");



label.setBounds(0,10,1050,900);
frame.add(label);
frame.setVisible(true);
frame.setAlwaysOnTop(true);
		frame.setResizable(false);
	


}

public static void main(String [] args) {
        ElectionDetails tester = new ElectionDetails();
    }
}
