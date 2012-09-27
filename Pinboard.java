/*
	Screen1 is PinBoard screen
*/

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pinboard extends JPanel
{
	private JLabel todolabel, doinglabel;
	private JList todo, doing, events;
	private JScrollPane todoscroll, doingscroll, eventscroll;
	
	
    public Pinboard() {
        super(new GridLayout(1, 1));

		todolabel = new JLabel("To Do");
		doinglabel = new JLabel("Doing");
		todo = new JList();
		doing = new JList();
		events = new JList();
		todoscroll = new JScrollPane(todo);
		doingscroll = new JScrollPane(doing);
		eventscroll = new JScrollPane(events);
        
        JTabbedPane tabbedPane = new JTabbedPane();
        ImageIcon icon = createImageIcon("tray.jpg");
        
        JComponent panel1 = makeTextPanel();
        tabbedPane.addTab("Tasks", icon, panel1,
                "Does nothing");
		panel1.add(todolabel);
		panel1.add(todoscroll);
		panel1.add(doinglabel);
		panel1.add(doingscroll);
        
        JComponent panel2 = makeTextPanel();
        tabbedPane.addTab("Events", icon, panel2,
                "Does twice as much nothing");
        panel2.add(eventscroll);
        
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
        java.net.URL imgURL = Pinboard.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Pinboard");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//added so that there may only be one pinboard frame
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
    		public void windowClosing(WindowEvent winEvt) {
    			Calendar.pinboardOpen++;
       		}
		});
        
        //Add content to the window.
        frame.add(new Pinboard(), BorderLayout.CENTER);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
		frame.setSize(325,450);
		frame.setResizable(false);
    }
}