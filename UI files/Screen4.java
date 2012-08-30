/*
	Screen4 is View Important Dates screen
*/
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
	static JButton adddate, editdate, deletedate, back;
	static JLabel selectmonth;
	static JScrollPane datescroll;
	
	public Screen4()
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
		back = new JButton("Back");
		panel = new JPanel(null);
		
		adddate.addActionListener(new adddate_Action());
		editdate.addActionListener(new editdate_Action());
		deletedate.addActionListener(new deletedate_Action());
		back.addActionListener(new back_Action());
		
		//add contents to pane
		pane.add(panel);
		panel.add(month);
		panel.add(datescroll);
		panel.add(adddate);
		panel.add(editdate);
		panel.add(selectmonth);
		panel.add(deletedate);
		panel.add(back);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		month.setBounds(80, 25, 80, 20);
		datescroll.setBounds(10, 60, 300, 200);
		adddate.setBounds(10,300,300,25);
		editdate.setBounds(10, 330, 300, 25);
		selectmonth.setBounds(10, 25, 80, 20);
		deletedate.setBounds(10,360,300,25);
		back.setBounds(10, 270, 300, 25);
	}
	
	static class adddate_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			//nextScreen = 1;
			frmMain.dispose();
			Screen5 screen5 = new Screen5();
		}
	}
	
	static class editdate_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			//nextScreen = 1;
			frmMain.dispose();
			Screen6 screen6 = new Screen6();
		}
	}
	
	static class deletedate_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			frmMain.dispose();
			Screen1 screen1 = new Screen1(true);
		}
	}
	
	static class back_Action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			frmMain.dispose();
			Screen1 screen1 = new Screen1(true);
		}
	}
	
	// public static void main ( String[] args )
	// {
		 // Screen4 screen4 = new Screen4();
	// }
}