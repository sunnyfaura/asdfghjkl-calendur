/*
	Screen1 is Pin Board screen
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Screen1
{
	static JButton viewdates, addtask, viewtask, calendar, moodboard, deletetask;
	static JLabel todolabel, doinglabel;
	static JList todo, doing;
	static JFrame frmMain;
	static Container pane;
	static JPanel panel;
	static JScrollPane todoscroll, doingscroll;
	static boolean current;
	
	public Screen1(boolean c)
	{
		current = c;
	
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
		moodboard = new JButton( "Mood Board" );
		todolabel = new JLabel( "To Do" );
		doinglabel = new JLabel( "Doing" );
		todo = new JList();
		doing = new JList();
		panel = new JPanel(null);
		todoscroll = new JScrollPane(todo);
		doingscroll = new JScrollPane(doing);
		
		viewdates.addActionListener(new viewdates_Action());
		addtask.addActionListener(new addtask_Action());
		viewtask.addActionListener(new viewtask_Action());
		deletetask.addActionListener(new deletetask_Action());
		calendar.addActionListener(new calendar_Action());
		moodboard.addActionListener(new moodboard_Action());
		
		
		//frame stuff
		frmMain = new JFrame( "Life Planner" );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(current);
		
		//add contents to pane
		pane.add(panel);
		panel.add(addtask);
		panel.add(todolabel);
		panel.add(todoscroll);
		panel.add(doinglabel);
		panel.add(doingscroll);
		panel.add(viewtask);
		panel.add(calendar);
		panel.add(moodboard);
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
		moodboard.setBounds(10, 400, 300, 25);
	}
	
	static class viewdates_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			current = false;
			//nextScreen = 1;
			frmMain.dispose();
			Screen4 screen4 = new Screen4();
		}
	}
	
	static class addtask_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			current = false;
			//nextScreen = 1;
			frmMain.dispose();
			Screen2 screen2 = new Screen2();
		}
	}
	
	static class viewtask_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			current = false;
			//nextScreen = 1;
			frmMain.dispose();
			Screen3 screen3 = new Screen3();
		}
	}
	
	static class deletetask_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){

		}
	}
	
	static class calendar_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			current = false;
			//nextScreen = 1;
			frmMain.dispose();
			Screen0 screen0 = new Screen0(true);
		}
	}
	
	static class moodboard_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			current = false;
			//nextScreen = 1;
			frmMain.dispose();
			Screen7 screen7 = new Screen7();
		}
	}
	//public static void main( String[] args )
	//{
	//	Screen1 screen1 = new Screen1();
	//}
}