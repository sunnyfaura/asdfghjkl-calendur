import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddEntry extends JPanel {

	static private JLabel todolabel, doinglabel;
	static private JList todo, doing, events;
	static private JScrollPane todoscroll, doingscroll, eventscroll;
	static private JTextField name, name1;
	static private JTextArea desc, desc1;
	static private JComboBox month, day, year, hour, minute, ampm, status;
	static private JComboBox month1, day1, year1, hour1, minute1, ampm1, status1;
	static JFrame frame;
	
    public AddEntry() {
		super(new GridLayout(1, 1));

		todolabel = new JLabel("To Do");
		doinglabel = new JLabel("Doing");
		todo = new JList();
		doing = new JList();
		events = new JList();
		todoscroll = new JScrollPane(todo);
		doingscroll = new JScrollPane(doing);
		eventscroll = new JScrollPane(events);
		name = new JTextField();
		desc = new JTextArea();
		name1 = new JTextField();
		desc1 = new JTextArea();
		
		String[] months = { "January", "February", "March","April","May","June","July","August","September","October","November","December"};
		month = new JComboBox(months);
		month1 = new JComboBox(months);
		
		Integer[] days = new Integer[31];
		for( int i=0; i<=30; i++ )
			days[i] = i+1;
		day = new JComboBox(days);
		day1 = new JComboBox(days);
		
		Integer[] years = new Integer[100];
		for( int i=0; i<100; i++ )
			years[i] = 2012+i;
		year = new JComboBox(years);
		year1 = new JComboBox(years);
		
		Integer[] hours = new Integer[12];
		for( int i=0; i<12; i++)
			hours[i] = i+1;
		hour = new JComboBox(hours);
		hour1 = new JComboBox(hours);
		
		String[] minutes = new String[60];
		for( int i=0; i<60; i++ )
		{	if( i > 9 )
			minutes[i] = Integer.toString(i);
			else
			minutes[i] = "0"+Integer.toString(i);
		}
		minute = new JComboBox(minutes);
		minute1 = new JComboBox(minutes);
		
		String[] time = { "AM", "PM"};
		ampm = new JComboBox(time);
		ampm1 = new JComboBox(time);
		
		String[] status0 = { "To Do", "Doing", "Done" };
		status = new JComboBox(status0);
		status1 = new JComboBox(status0);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("tray.jpg");
        
        JComponent panel1 = makeTextPanel();
		panel1.setLayout(new GridLayout(1,1));
        tabbedPane.addTab("Tasks", icon, panel1,
                "Tasks");
		JPanel tasksPanelMain = new JPanel(new BorderLayout());
			tasksPanelMain.add(new JLabel(" "), BorderLayout.PAGE_START);
			tasksPanelMain.add(new JLabel(" "), BorderLayout.PAGE_END);
			tasksPanelMain.add(new JLabel("   "), BorderLayout.LINE_START);
			tasksPanelMain.add(new JLabel("   "), BorderLayout.LINE_END);
			JPanel taskPane = new JPanel(new BorderLayout());
				JPanel taskHead = new JPanel(new GridLayout(1,1));
					//taskHead.add(new JLabel(" "));
					JPanel taskNameLabel = new JPanel(new BorderLayout());
						taskNameLabel.add(new JLabel("Task Name:   \t"), BorderLayout.LINE_START);
						taskNameLabel.add(name, BorderLayout.CENTER);
					taskHead.add(taskNameLabel);
				taskPane.add(taskHead, BorderLayout.PAGE_START);
				JPanel taskBody = new JPanel(new GridLayout(2, 1));
					JPanel taskDescMain = new JPanel(new BorderLayout());
						taskDescMain.add(new JLabel(" "), BorderLayout.PAGE_START);
						JPanel taskDescLabel = new JPanel(new FlowLayout());
						taskDescMain.add(new JLabel("Description:   \t"), BorderLayout.LINE_START);
						taskDescMain.add(desc, BorderLayout.CENTER);
					taskBody.add(taskDescMain);
					JPanel taskProp = new JPanel(new GridLayout(2,1));
						JPanel taskDeadlineMain = new JPanel(new BorderLayout());
							taskDeadlineMain.add(new JLabel("Deadline: \t"), BorderLayout.LINE_START);
							JPanel taskDeadlineInfo = new JPanel(new GridLayout(2,1));
								JPanel taskDate = new JPanel(new FlowLayout()); //DATE
									taskDate.add(month);
									taskDate.add(day);
									taskDate.add(year);
								taskDeadlineInfo.add(taskDate);
								JPanel taskTime = new JPanel(new FlowLayout()); //TIME
									taskTime.add(hour);
									taskTime.add(minute);
									taskTime.add(ampm);
								taskDeadlineInfo.add(taskTime);
							taskDeadlineMain.add(taskDeadlineInfo, BorderLayout.CENTER);
						taskProp.add(taskDeadlineMain);
						JPanel taskButtons = new JPanel(new GridLayout(3,1));
							JPanel taskStatusMain = new JPanel( new BorderLayout());
								taskStatusMain.add(new JLabel("Status:   \t"), BorderLayout.LINE_START);
								taskStatusMain.add(status, BorderLayout.CENTER);
								//taskStatusMain.add(new JLabel(" "), BorderLayout.PAGE_END);
							taskButtons.add(taskStatusMain);
							JButton b = new JButton("Add Task");
							taskButtons.add(b);
							b.addActionListener( new addTask_Action() );
							//JButton c = new JButton("Cancel");
							//c.addActionListener( new cancel_Action() );
							//taskButtons.add(c);
						taskProp.add(taskButtons);
					taskBody.add(taskProp);
					//taskBody.add(new JButton("Deadline and Buttons Here")); // EDIT
				taskPane.add(taskBody);
			tasksPanelMain.add(taskPane, BorderLayout.CENTER);
		panel1.add(tasksPanelMain);
		//panel1.add(todolabel);
		//panel1.add(todoscroll);
		//panel1.add(doinglabel);
		//panel1.add(doingscroll);
        
        JComponent panel2 = makeTextPanel();
		panel2.setLayout(new GridLayout(1,1));
        tabbedPane.addTab("Events", icon, panel2,
                "Events");
		JPanel eventsPanelMain = new JPanel(new BorderLayout());
			eventsPanelMain.add(new JLabel(" "), BorderLayout.PAGE_START);
			eventsPanelMain.add(new JLabel(" "), BorderLayout.PAGE_END);
			eventsPanelMain.add(new JLabel("   "), BorderLayout.LINE_START);
			eventsPanelMain.add(new JLabel("   "), BorderLayout.LINE_END);
			JPanel eventPane = new JPanel(new BorderLayout());
				JPanel eventHead = new JPanel(new GridLayout(1,1));
					//taskHead.add(new JLabel(" "));
					JPanel eventNameLabel = new JPanel(new BorderLayout());
						eventNameLabel.add(new JLabel("Event Name:  \t"), BorderLayout.LINE_START);
						eventNameLabel.add(name1, BorderLayout.CENTER);
					eventHead.add(eventNameLabel);
				eventPane.add(eventHead, BorderLayout.PAGE_START);
				JPanel eventBody = new JPanel(new GridLayout(2, 1));
					JPanel eventDescMain = new JPanel(new BorderLayout());
						eventDescMain.add(new JLabel(" "), BorderLayout.PAGE_START);
						JPanel eventDescLabel = new JPanel(new FlowLayout());
						eventDescMain.add(new JLabel("Description:   \t"), BorderLayout.LINE_START);
						eventDescMain.add(desc1, BorderLayout.CENTER);
					eventBody.add(eventDescMain);
					JPanel eventProp = new JPanel(new GridLayout(5,1));
						JPanel eventDeadlineMain = new JPanel(new BorderLayout());
							eventDeadlineMain.add(new JLabel("Deadline: \t"), BorderLayout.LINE_START);
							JPanel eventDate = new JPanel(new FlowLayout()); //DATE
									eventDate.add(month1);
									eventDate.add(day1);
									eventDate.add(year1);
							eventDeadlineMain.add(eventDate, BorderLayout.CENTER);
						eventProp.add(eventDeadlineMain);
							JPanel eventDeadlineInfo = new JPanel(new BorderLayout());	
								JRadioButtonMenuItem timeSelection = new JRadioButtonMenuItem("Time");
								eventDeadlineInfo.add(timeSelection, BorderLayout.LINE_START);
								JPanel eventTime = new JPanel(new FlowLayout()); //TIME
									eventTime.add(hour1);
									eventTime.add(minute1);
									eventTime.add(ampm1);
								eventDeadlineInfo.add(eventTime, BorderLayout.CENTER);
						eventProp.add(eventDeadlineInfo);
						JRadioButtonMenuItem allDay = new JRadioButtonMenuItem("All Day");
						eventProp.add(allDay);
						/*JPanel eventButtons = new JPanel(new GridLayout(3,1));
							JPanel eventStatusMain = new JPanel( new BorderLayout());
								eventStatusMain.add(new JLabel("Status:   \t"), BorderLayout.LINE_START); //STATUS
								eventStatusMain.add(status1, BorderLayout.CENTER);
								//taskStatusMain.add(new JLabel(" "), BorderLayout.PAGE_END);
							eventButtons.add(eventStatusMain);*/
						JButton bb = new JButton("Add Event");
						eventProp.add(bb);
							bb.addActionListener( new addEvent_Action());//BUTTON1
						//JButton cc = new JButton("Cancel");	
						//cc.addActionListener(new cancel_Action());
						//eventProp.add(cc); //BUTTON2
						//eventProp.add(eventButtons);
					eventBody.add(eventProp);
					//taskBody.add(new JButton("Deadline and Buttons Here")); // EDIT
				eventPane.add(eventBody);
			eventsPanelMain.add(eventPane, BorderLayout.CENTER);
		panel2.add(eventsPanelMain);
        //panel2.add(eventscroll);
        
        //Add the tabbed pane to this panel.
        add(tabbedPane);
        
        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
    protected JComponent makeTextPanel() {
        JPanel panel = new JPanel(false);
        panel.setLayout(new FlowLayout());
        return panel;
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = AddEntry.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from
     * the event dispatch thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
		frame = new JFrame("Add Entry");
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Close when X is clicked
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                Calendar.addEntryOpen++;
            }
        });
        //Add content to the window.
        frame.add(new AddEntry(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
		frame.setSize(325,450);
		frame.setResizable(false);
    }
    
  //   public static void main(String[] args) {
  //       //Schedule a job for the event dispatch thread:
  //       //creating and showing this application's GUI.
  //       SwingUtilities.invokeLater(new Runnable() {
  //           public void run() {
  //               //Turn off metal's use of bold fonts
		// UIManager.put("swing.boldMetal", Boolean.FALSE);
		// createAndShowGUI();
  //           }
  //       });
  //   }
	
	static class addTask_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//INSERT TASK TO DATABASE
			String n = name.getText();
			String dsc = desc.getText();
			int mnth = (int)month.getSelectedIndex(); //January = 0; December = 11;
			int dy = (int)day.getSelectedIndex()+1;  //returns exact day
			int yr = Integer.parseInt(year.getSelectedItem()+""); //returns exact year
			int stat = (int)status.getSelectedIndex(); // to-do = 0; doing = 1; done = 2;
			int hr = (int)hour.getSelectedIndex()+1; //returns exact hour
			int mins = (int)minute.getSelectedIndex(); //returns exact minutes
			int ap = (int)ampm.getSelectedIndex(); // am = 0; pm = 1;
			frame.setVisible(false);
			frame.dispose();
			System.out.println(n);
			System.out.println(dsc);
			System.out.println(mnth);
			System.out.println(dy);
			System.out.println(yr);
			System.out.println(stat);
			System.out.println(hr);
			System.out.println(mins);
			System.out.println(ap);
			Calendar.addEntryOpen++;
		}
	}
	
	static class addEvent_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			//INSERT EVENT TO DATABASE
			String n = name1.getText();
			String dsc = desc1.getText();
			String mnth = month1.getSelectedItem()+"";
			int dy = (int)day1.getSelectedIndex()+1;
			int yr = Integer.parseInt(year1.getSelectedItem()+"");
			System.out.println("what");
			frame.setVisible(false);
			frame.dispose();
			System.out.println(n);
			System.out.println(dsc);
			System.out.println(mnth);
			System.out.println(dy);
			System.out.println(yr);
			Calendar.addEntryOpen++;
		}
	}
	
	// static class cancel_Action implements ActionListener
	// {
	// 	public void actionPerformed(ActionEvent e)
	// 	{
	// 		frame.setVisible(false);
	// 		frame.dispose();
	// 		Calendar.addEntryOpen++;
	// 	}
	// }
}