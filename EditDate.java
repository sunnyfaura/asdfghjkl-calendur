/*
	Screen6 is Edit Date screen
*/

//exactly the same as edit task
//in fact, edit date is subset of edit task
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class EditDate
{
	static JButton save, back;
	static JLabel date, description, repeatlabel;
	static JComboBox month, day, year, repeat;
	static JTextArea descriptionarea;
	static Container pane;
	static JPanel panel;
	static JFrame frmMain;
	static JScrollPane desc;
	
	public static void main ( String[] args )
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
		
		panel = new JPanel(null);
		
		//frame stuff
		frmMain = new JFrame( "Edit Date" );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		description.setBounds(10, 80, 80, 20);
		desc.setBounds(75, 83, 200, 200);
		date.setBounds( 10, 30, 80, 20);
		month.setBounds( 75, 30, 90, 20);
		day.setBounds( 180, 30, 40, 20);
		year.setBounds( 230, 30, 60, 20);
		save.setBounds(10, 350, 300, 25);
		back.setBounds(10, 380, 300, 25);
		repeatlabel.setBounds(10, 300, 80, 20);
		repeat.setBounds(75, 300, 80, 20);
	}
}