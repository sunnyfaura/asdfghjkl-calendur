import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DayView
{
	private JLabel tasklabel, eventlabel, date, day;
	private JList tasks, events;
	private JFrame frmMain;
	private Container pane;
	private JPanel panel;
	private JScrollPane taskscroll, eventscroll;
	
	
	public DayView(int iday, int imonth, int idate, int iyear)
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//instantiate component

		tasklabel = new JLabel( "Tasks" );
		eventlabel = new JLabel( "Events" );
		
		String[] days = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		day = new JLabel( days[iday]+"" );
		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		date = new JLabel( months[imonth]+" "+idate+", "+iyear ); // <---connect to calendar or something
		tasks = new JList();
		events = new JList();
		panel = new JPanel(null);
		taskscroll = new JScrollPane(tasks);
		eventscroll = new JScrollPane(events);

		//frame stuff
		frmMain = new JFrame( months[imonth]+" "+idate+", "+iyear );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//added so that there may only be one pinboard frame
		frmMain.addWindowListener(new java.awt.event.WindowAdapter() {
    		public void windowClosing(WindowEvent winEvt) {
    			Calendar.pinboardOpen++;
       		}
		});
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		//add contents to pane
		pane.add(panel);
		panel.add(tasklabel);
		panel.add(taskscroll);
		panel.add(eventlabel);
		panel.add(eventscroll);
		panel.add(day);
		panel.add(date);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		day.setBounds(10, 10, 80,20);
		date.setBounds(70, 10, 150,20);
		tasklabel.setBounds(10, 40, 80, 20);                                  
		taskscroll.setBounds(10, 60, 300, 150);
		eventlabel.setBounds(10, 220, 80, 20);
		eventscroll.setBounds(10, 240, 300, 150);
	}
	
	public static void main( String[] args )
	{
		new DayView(5,8,28,2012);
	}
}