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
		panel = new JPanel(new GridLayout(2, 1));
		taskscroll = new JScrollPane(tasks);
		eventscroll = new JScrollPane(events);

		//frame stuff
		frmMain = new JFrame( months[imonth]+" "+idate+", "+iyear );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(new BorderLayout());
		frmMain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		frmMain.setAlwaysOnTop(true);
		//frmMain.setLayout(new GridLayout(2, 1));
		
		//panel.setLayout(new GridLayout(2,1));
		//add contents to pane
		pane.add(panel, BorderLayout.CENTER);
		JPanel taskPanel = new JPanel(new BorderLayout());
			JPanel taskLabelPanel = new JPanel(new GridLayout(2,1));
				taskLabelPanel.add(new JLabel(" "));
				taskLabelPanel.add(tasklabel);
			taskPanel.add(taskLabelPanel, BorderLayout.PAGE_START);
			taskPanel.add(taskscroll, BorderLayout.CENTER);
		panel.add(taskPanel);
		JPanel eventPanel = new JPanel(new BorderLayout());
			JPanel eventLabelPanel = new JPanel(new GridLayout(2, 1));
				eventLabelPanel.add(new JLabel(" "));
				eventLabelPanel.add(eventlabel);
			eventPanel.add(eventLabelPanel, BorderLayout.PAGE_START);
			eventPanel.add(eventscroll, BorderLayout.CENTER);
		//panel.add(eventlabel);
		//panel.add(eventscroll);
		panel.add(eventPanel);
		JPanel heading = new JPanel( new GridLayout(2, 1) );
			heading.add(new JLabel(" "));
			JPanel dayDatePanel = new JPanel(new FlowLayout());
				dayDatePanel.add(day);
				dayDatePanel.add(date);
			heading.add(dayDatePanel);
		pane.add(heading, BorderLayout.PAGE_START);
		pane.add(new JLabel(" "), BorderLayout.PAGE_END);
		pane.add(new JLabel("   "), BorderLayout.LINE_END);
		pane.add(new JLabel("   "), BorderLayout.LINE_START);
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		day.setBounds(10, 10, 80,20);
		date.setBounds(70, 10, 150,20);
		//tasklabel.setBounds(10, 40, 80, 20);                                  
		//taskscroll.setBounds(10, 60, 300, 150);
		//eventlabel.setBounds(10, 220, 80, 20);
		//eventscroll.setBounds(10, 240, 300, 150);
	}
	
	/*public static void main( String[] args )
	{
		new DayView(5,8,28,2012);
	}*/
}