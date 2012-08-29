/*
	AddingTask is Add Task screen
*/

//make this pop-up
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddingTask
{
	private JButton addtask;
	private JLabel namelabel, date, description, status;
	private JTextField name;
	private JComboBox month, day, year, hour, minute, ampm;
	private JTextArea descriptionarea;
	private Container pane;
	private JPanel panel;
	private JFrame frmMain;
	private JScrollPane desc;
	
	public AddingTask()
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//instantiate components
		addtask = new JButton( "Add Task" );
		namelabel = new JLabel( "Task Name" );
		date = new JLabel( "Deadline" );
		description = new JLabel( "Description" );
		status = new JLabel( "Status" );
		name = new JTextField();
		
		String[] months = { "January", "February", "March","April","May","June","July","August","September","October","November","December"};
		month = new JComboBox(months);
		
		Integer[] days = new Integer[31];
		for( int i=0; i<=30; i++ )
			days[i] = i+1;
		day = new JComboBox(days);
		
		Integer[] years = new Integer[100];
		for( int i=0; i<100; i++ )
			years[i] = 2012+i;
		year = new JComboBox(years);
		
		Integer[] hours = new Integer[12];
		for( int i=0; i<12; i++)
			hours[i] = i+1;
		hour = new JComboBox(hours);
		
		String[] minutes = new String[60];
		for( int i=0; i<60; i++ )
		{	if( i > 9 )
			minutes[i] = Integer.toString(i);
			else
			minutes[i] = "0"+Integer.toString(i);
		}
		minute = new JComboBox(minutes);
		
		String[] time = { "AM", "PM"};
		ampm = new JComboBox(time);
		
		descriptionarea = new JTextArea();
		descriptionarea.setLineWrap(true);
		desc = new JScrollPane(descriptionarea);
		
		panel = new JPanel(null);
		
		//frame stuff
		frmMain = new JFrame( "Adding Task" );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		//add contents to pane
		pane.add(panel);
		panel.add(namelabel);
		panel.add(date);
		panel.add(description);
		panel.add(status);
		panel.add(name);
		panel.add(month);
		panel.add(day);
		panel.add(year);
		panel.add(desc);
		panel.add(hour);
		panel.add(minute);
		panel.add(ampm);
		panel.add(addtask);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		namelabel.setBounds(10, 25, 80, 20);
		name.setBounds( 80, 27, 200, 20);
		description.setBounds(10, 60, 80, 20);
		desc.setBounds(80, 63, 200, 200);
		date.setBounds( 10, 280, 80, 20);
		month.setBounds( 75, 280, 90, 20);
		day.setBounds( 180, 280, 40, 20);
		year.setBounds( 230, 280, 60, 20);
		hour.setBounds(100, 310, 40, 20);
		minute.setBounds( 150, 310, 40, 20);
		ampm.setBounds(200, 310, 40, 20);
		addtask.setBounds(10, 350, 300, 25);
	}

	private class addtask_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			//nextScreen = 1;
			frmMain.dispose();
			Pinboard pinboard = new Pinboard();
		}
	}
}