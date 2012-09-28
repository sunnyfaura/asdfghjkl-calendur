import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Pinboard extends JPanel {

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
		panel1.setLayout(new GridLayout(1,1));
        tabbedPane.addTab("Tasks", icon, panel1,
                "Tasks");
		JPanel tasksPanelMain = new JPanel(new BorderLayout());
			tasksPanelMain.add(new JLabel(" "), BorderLayout.PAGE_START);
			tasksPanelMain.add(new JLabel(" "), BorderLayout.PAGE_END);
			tasksPanelMain.add(new JLabel("   "), BorderLayout.LINE_START);
			tasksPanelMain.add(new JLabel("   "), BorderLayout.LINE_END);
			JPanel tasksPane = new JPanel(new GridLayout(2, 1));
				JPanel toDoPanel = new JPanel(new BorderLayout());
					JPanel toDoLabelPanel = new JPanel(new GridLayout(2, 1));
						toDoLabelPanel.add(new JLabel(" "));
						toDoLabelPanel.add(todolabel);
					toDoPanel.add(toDoLabelPanel, BorderLayout.PAGE_START);
					toDoPanel.add(todoscroll, BorderLayout.CENTER);
				tasksPane.add(toDoPanel);
				JPanel doingPanel = new JPanel(new BorderLayout());
					JPanel doingLabelPanel = new JPanel(new GridLayout(2, 1));
						doingLabelPanel.add(new JLabel(" "));
						doingLabelPanel.add(doinglabel);
					doingPanel.add(doingLabelPanel, BorderLayout.PAGE_START);
					doingPanel.add(doingscroll, BorderLayout.CENTER);
				tasksPane.add(doingPanel);
			tasksPanelMain.add(tasksPane, BorderLayout.CENTER);
		panel1.add(tasksPanelMain);
		//panel1.add(todolabel);
		//panel1.add(todoscroll);
		//panel1.add(doinglabel);
		//panel1.add(doingscroll);
        
        JComponent panel2 = makeTextPanel();
		panel2.setLayout(new GridLayout(1,1));
        tabbedPane.addTab("Events", icon, panel2,
                "Events");
		JPanel eventsPanel = new JPanel(new BorderLayout());
			eventsPanel.add(new JLabel(" "), BorderLayout.PAGE_START);
			eventsPanel.add(new JLabel(" "), BorderLayout.PAGE_END);
			eventsPanel.add(new JLabel("   "), BorderLayout.LINE_START);
			eventsPanel.add(new JLabel("   "), BorderLayout.LINE_END);
			eventsPanel.add(eventscroll, BorderLayout.CENTER);
		panel2.add(eventsPanel);
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
        java.net.URL imgURL = Pinboard.class.getResource(path);
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
        JFrame frame = new JFrame("Pinboard");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Close when X is clicked
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
    
    // public static void main(String[] args) {
    //     //Schedule a job for the event dispatch thread:
    //     //creating and showing this application's GUI.
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             //Turn off metal's use of bold fonts
		  //         UIManager.put("swing.boldMetal", Boolean.FALSE);
		  //         createAndShowGUI();
    //         }
    //     });
    // }
}