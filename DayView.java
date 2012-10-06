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

		ArrayList<Task> t = null;
		ArrayList<Event> ev = null;
		//t = DatabaseRW.queryDayTasks(iyear, imonth, idate);
		try
		{
			t = DatabaseRW.queryDayTasks(iyear, (imonth+1), idate);
			System.out.println("Successfully Queried Day Task: " + iyear + " " + (imonth+1) + " " + idate);
			if(t == null) System.out.println("No ArrayList returned.");
			else System.out.println("ArrayList size: " + t.size());
		} catch (Exception e) {}
		try
		{
			ev = DatabaseRW.queryDayEvents(iyear, (imonth+1), idate);
			System.out.println("Successfully Queried Day Event: " + iyear + " " + (imonth+1) + " " + idate);
			if(ev == null) System.out.println("No ArrayList returned.");
			else System.out.println("ArrayList size: " + ev.size());
		} catch (Exception e) {}
		String[] taskNames = new String[1];
		taskNames[0] = "No tasks found";
		String[] eventNames = new String[1];
		eventNames[0] = "No tasks found";
		boolean clickable = false;
		if(t != null && t.size() > 0)
		{
			System.out.println("Returned arrayList size > 0");
			taskNames = new String[t.size()];
			for(int i = 0; i < t.size(); i++)
			{
				taskNames[i] = t.get(i).name;
			}
			clickable = true;
		}
		if(ev != null && ev.size() > 0)
		{
			System.out.println("Returned arrayList size > 0");
			eventNames = new String[ev.size()];
			for(int i = 0; i < ev.size(); i++)
			{
				eventNames[i] = ev.get(i).name;
			}
			clickable = true;
		}
		tasks = new JList(taskNames);
		//tasks = new JList();
		events = new JList(eventNames);
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
}