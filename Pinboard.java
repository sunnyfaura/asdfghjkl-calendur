/*
	Pinboard is Pin Board screen
*/

//alternative page
//preferably a separate window so user can see the pinboard and calendar at the same time -done
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pinboard
{
	private JButton viewdates, addtask, viewtask, deletetask, calendar; //moodboard,  
	private JLabel todolabel, doinglabel;
	private JList todo, doing;
	private JFrame frmMain;
	private Container pane;
	private JPanel panel;
	private JScrollPane todoscroll, doingscroll;
	
	public Pinboard()
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//instantiate components
		viewdates = new JButton( "View Important Dates" );
		addtask = new JButton( "Add Task" );
		viewtask = new JButton( "View Task" ); //disable if a task is not selected
		deletetask = new JButton( "Delete Task" ); //disable if a task is not selected
		calendar = new JButton( "Calendar" );
		//moodboard = new JButton( "Mood Board" );
		todolabel = new JLabel( "To Do" );
		doinglabel = new JLabel( "Doing" );
		todo = new JList();
		doing = new JList();
		panel = new JPanel(null);
		todoscroll = new JScrollPane(todo);
		doingscroll = new JScrollPane(doing);

		//action listeners
		//viewdates.addActionListener(new viewdates_Action());
		addtask.addActionListener(new addtask_Action());
		//viewtask.addActionListener(new viewtask_Action());
		//deletetask.addActionListener(new deletetask_Action());
		
		//frame stuff
		frmMain = new JFrame( "Pinboard" );
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
		panel.add(addtask);
		panel.add(todolabel);
		panel.add(todoscroll);
		panel.add(doinglabel);
		panel.add(doingscroll);
		panel.add(viewtask);
		panel.add(calendar);
		//panel.add(moodboard);
		panel.add(deletetask);
		panel.add(viewdates);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		viewdates.setBounds(10, 4, 300, 25);
		addtask.setBounds(10, 30, 300, 25);
		todolabel.setBounds(10, 60, 80, 20);                                  
		todoscroll.setBounds(10, 80, 300, 100);
		doinglabel.setBounds(10, 180, 80, 20);
		doingscroll.setBounds(10, 200, 300, 100);
		deletetask.setBounds(10, 310, 300, 25);
		viewtask.setBounds(10, 340, 300, 25);
		calendar.setBounds(10, 370, 300, 25);
		//moodboard.setBounds(10, 400, 300, 25);
	}
	
	private class addtask_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			
		}
	}
}