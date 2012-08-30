/*
	Screen6 is Edit Date screen
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Screen6
{
	static JButton save, back;
	static JLabel date, description, repeatlabel, timelabel;
	static JComboBox month, day, year, repeat, hour, minute, ampm;
	static JTextArea descriptionarea;
	static Container pane;
	static JPanel panel;
	static JFrame frmMain;
	static JScrollPane desc;
	static JCheckBox allday;
	
	
	public Screen6()
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//instantiate components
		save = new JButton( "Save Changes" );
		back = new JButton( "Back" );
		date = new JLabel( "Date" );
		description = new JLabel( "Description" );
		repeatlabel = new JLabel( "Repeat" );
		timelabel = new JLabel("Time");
		
		save.addActionListener(new save_Action());
		back.addActionListener(new back_Action());
		
		String[] repeats = { "Daily", "Weekly", "Monthly", "Yearly"};
		repeat = new JComboBox(repeats);
		
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
		
		descriptionarea = new JTextArea();
		descriptionarea.setLineWrap(true);
		desc = new JScrollPane(descriptionarea);
		
		allday = new JCheckBox( "All-day");
		
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
		
		panel = new JPanel(null);
		
		//frame stuff
		frmMain = new JFrame( "Life Planner" );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		//add contents to pane
		pane.add(panel);
		panel.add(date);
		panel.add(month);
		panel.add(day);
		panel.add(year);
		panel.add(save);
		panel.add(back);
		panel.add(repeat);
		panel.add(repeatlabel);
		panel.add(desc);
		panel.add(description);
		panel.add(allday);
		panel.add(hour);
		panel.add(minute);
		panel.add(ampm);
		panel.add(timelabel);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		description.setBounds(10, 60, 80, 20);
		desc.setBounds(75, 63, 200, 200);
		date.setBounds( 10, 30, 80, 20);
		month.setBounds( 75, 30, 90, 20);
		day.setBounds( 180, 30, 40, 20);
		year.setBounds( 230, 30, 60, 20);
		save.setBounds(10, 350, 300, 25);
		back.setBounds(10, 380, 300, 25);
		repeatlabel.setBounds(10, 275, 80, 20);
		repeat.setBounds(75, 275, 80, 20);
		allday.setBounds(10, 295, 80, 20);
		timelabel.setBounds( 10, 315, 80, 20);
		hour.setBounds(75, 315, 40, 20);
		minute.setBounds( 125, 315, 40, 20);
		ampm.setBounds(175, 315, 40, 20);
	}
	
	static class save_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			//nextScreen = 1;
			frmMain.dispose();
			Screen1 screen1 = new Screen1(true);
		}
	}
	
	static class back_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			//nextScreen = 1;
			frmMain.dispose();
			Screen4 screen4 = new Screen4();
		}
	}
	
	// public static void main ( String[] args )
	// {
		// Screen6 screen6 = new Screen6();
	// }
}