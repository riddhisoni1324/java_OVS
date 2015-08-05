package com.election.voter;
import com.election.voter.*;
import java.sql.*;
import java.text.*;
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

public class VotedDB
{
long vid;
int cid;
//static int svid=98;
int counter;
java.text.SimpleDateFormat sfd,sfd1;    java.util.Date ud1,ud11;		long ms,ms1;  			java.sql.Date sqld,sqld1;
	String filePath=null,filepath2=null,finalpath=null,g1date,g1date1;

	  

VotedDB(long id1,int id2)
{
vid=id1;
cid=id2;
//svid++;
this.insertIntoVoted();

}
 public void insertIntoVoted(){
	try
					{
					Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  
					if(!con.isClosed()){ System.out.println("Connection Successful"); 
					try{
					String sqlsize ="select * from voted";
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
	
					System.out.println("befor enter into voted");
					PreparedStatement ps = con.prepareStatement("insert into voted (svid,vid,c_id) values (?,?, ?)");
					ps.setInt(1,++counter);
					ps.setLong(2,vid);
					ps.setInt(3,cid);
					int i=ps.executeUpdate();  
					System.out.println("after enter into voted");
						PreparedStatement ps1 = con.prepareStatement("insert into audittrail (vid,action) values (?,?)");
						
							
						
					ps1.setLong(1,vid);
					ps1.setString(2,"vote casted");
					int i2=ps1.executeUpdate();  
				
							
					}
					
					}
					catch(Exception e)
					{e.printStackTrace();
					            System.out.println("Vote is not done");
 
					}
					finally{System.out.println("Vote is not done");
					}
					
					

}


}


/*select cons_id into c
from voterinfo
where vid=:new.vid;

select cons_id into c
from voterinfo
where vid=:new.vid;
*/