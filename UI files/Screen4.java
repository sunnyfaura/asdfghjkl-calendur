/*
	Screen4 is View Important Dates screen
*/

//same as calendar except with names
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Screen4
{
	static Container pane;
	static JPanel panel;
	static JFrame frmMain;
	static JComboBox month;
	static JList dates;
	static JButton adddate, editdate, deletedate;
	static JLabel selectmonth;
	static JScrollPane datescroll;
	
	public static void main ( String[] args )
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//frame stuff
		frmMain = new JFrame( "Life Planner" );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		//instantiate stuff
		String[] months = { "January", "February", "March","April","May","June","July","August","September","October","November","December"};
		month = new JComboBox(months); //by default this should display current month
		dates = new JList();
		adddate = new JButton("Add Important Date");
		editdate = new JButton("Edit Important Date"); //must be disabled if user has not selected a date from the list
		selectmonth = new JLabel("Select Month");
		deletedate = new JButton("Delete Important Date");//must be disabled if user has not selected a date
		datescroll = new JScrollPane(dates);
		panel = new JPanel(null);
		
		//add contents to pane
		pane.add(panel);
		panel.add(month);
		panel.add(datescroll);
		panel.add(adddate);
		panel.add(editdate);
		panel.add(selectmonth);
		panel.add(deletedate);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		month.setBounds(80, 25, 80, 20);
		datescroll.setBounds(10, 60, 300, 200);
		adddate.setBounds(10,300,300,25);
		editdate.setBounds(10, 340, 300, 25);
		selectmonth.setBounds(10, 25, 80, 20);
		deletedate.setBounds(10,380,300,25);
	}
}