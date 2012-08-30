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
	static JButton addtask, viewtask, calendar, moodboard;
	static JLabel todolabel, doinglabel;
	static JList todo, doing;
	static JFrame frmMain;
	static Container pane;
	static JPanel panel;
	
	public static void main( String[] args )
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//instantiate components
		addtask = new JButton( "Add Task" );
		viewtask = new JButton( "View Task" );
		calendar = new JButton( "Calendar" );
		moodboard = new JButton( "Mood Board" );
		todolabel = new JLabel( "To Do" );
		doinglabel = new JLabel( "Doing" );
		todo = new JList();
		doing = new JList();
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
		panel.add(addtask);
		panel.add(todolabel);
		panel.add(todo);
		panel.add(doinglabel);
		panel.add(doing);
		panel.add(viewtask);
		panel.add(calendar);
		panel.add(moodboard);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		addtask.setBounds(10, 25, 300, 25);
		todolabel.setBounds(10, 60, 80, 20);                                  
		todo.setBounds(10, 80, 300, 100);
		doinglabel.setBounds(10, 180, 80, 20);
		doing.setBounds(10, 200, 300, 100);
		viewtask.setBounds(10, 320, 300, 25);
		calendar.setBounds(10, 350, 300, 25);
		moodboard.setBounds(10, 380, 300, 25);
	}
}