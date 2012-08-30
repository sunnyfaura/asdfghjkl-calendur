/*
	Screen7 is Mood Board screen
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Screen7
{

	static Container pane;
	static JPanel panel;
	static JFrame frmMain;
	
	static JButton deletegoal, addgoal, save;
	static JComboBox mood;
	static JLabel goals, moodselector;
	static JScrollPane images, goalscroll;
	static JList goallist;
	
	public Screen7()
	{
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		//frame stuff
		frmMain = new JFrame( "Life Planner" );
		frmMain.setSize( 325, 450 );
		pane = frmMain.getContentPane();
		pane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.setResizable(false);
		frmMain.setVisible(true);
		
		//instantiate components
		panel = new JPanel();
		deletegoal = new JButton("Delete Goal");
		addgoal = new JButton("Add Goal");
		save = new JButton("Save Mood");
		String[] moods = { "Happy", "Sad", "Confused", "Lazy", "Productive"}; //fix this someday :))
		mood = new JComboBox(moods);
		goals = new JLabel("My Goals");
		moodselector = new JLabel("My Mood for Today");
		images = new JScrollPane(); //will contain images for the goals
		goallist = new JList();
		goalscroll = new JScrollPane(goallist);
		
		//add contents to pane
		pane.add(panel);
		panel.add(deletegoal);
		panel.add(addgoal);
		panel.add(save);
		panel.add(goals);
		panel.add(moodselector);
		panel.add(images);
		panel.add(goalscroll);
		panel.add(mood);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		goals.setBounds(10, 25, 80, 20);
		goalscroll.setBounds(10, 45, 300, 100);
		images.setBounds(10, 155, 300, 100);
		deletegoal.setBounds(10, 260, 300, 25);
		addgoal.setBounds(10, 290, 300, 25);
		moodselector.setBounds(10, 350, 100, 20);
		mood.setBounds(120, 350, 80, 20);
		save.setBounds(115, 380, 90, 20);
	}
	
	/*public static void main ( String[] args )
	{
		Screen7 screen7 = new Screen7();
	}*/
}