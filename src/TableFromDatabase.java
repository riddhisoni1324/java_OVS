package com.election.admin;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;


public class TableFromDatabase extends JFrame
{
    public TableFromDatabase(String s)
    {
        Vector<Object> columnNames = new Vector<Object>();
        Vector<Object> data = new Vector<Object>();

        try
        {
           

								Class.forName("oracle.jdbc.driver.OracleDriver");  
					Connection connection=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","riddhi123soni");  

            String sql = "Select * from "+s;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( sql );
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();


            for (int i = 1; i <= columns; i++)
            {
                columnNames.addElement( md.getColumnName(i) );
            }

            //  Get row data

            while (rs.next())
            {
                Vector<Object> row = new Vector<Object>(columns);

                for (int i = 1; i <= columns; i++)
                {
                    row.addElement( rs.getObject(i) );
                }

                data.addElement( row );
            }

            rs.close();
            stmt.close();
            connection.close();
        }
        catch(Exception e)
        {
            System.out.println( e );
        }

        //  Create table with database data

        DefaultTableModel model = new DefaultTableModel(data, columnNames)
        {
        	@Override
            public Class getColumnClass(int column)
            {
                for (int row = 0; row < getRowCount(); row++)
                {
                    Object o = getValueAt(row, column);

                    if (o != null)
                    {
                        return o.getClass();
                    }
                }

                return Object.class;
            }
        };

		JTable table = new JTable( model );
        JScrollPane scrollPane = new JScrollPane( table );
        getContentPane().add( scrollPane );

        JPanel buttonPanel = new JPanel();
        getContentPane().add( buttonPanel, BorderLayout.SOUTH );
		setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        pack();
        setVisible(true);
		setAlwaysOnTop(true);
		setResizable(false);
				this.addWindowListener(new WindowAdapter(){
    @Override public void windowDeactivated(WindowEvent e){	dispose();   }
    @Override public void windowDeiconified(WindowEvent e){	dispose();   }
    @Override public void 	windowGainedFocus(WindowEvent e){ dispose();  }
	@Override public void 		windowLostFocus(WindowEvent e){ dispose();  }
  });

	
    }

    public static void main(String[] args)
    {
        //TableFromDatabase frame = new TableFromDatabase();
        //frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
        //frame.pack();
        //frame.setVisible(true);
    }
}