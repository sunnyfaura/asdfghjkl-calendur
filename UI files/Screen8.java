/*
	Screen8 is Add Goal screen
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.File;
import javax.swing.filechooser.*;

public class Screen8
{
	static Container pane;
	static JPanel panel;
	static JFrame frmMain;
	
	//static JFileChooser imgchsr;
	static JLabel goalname, description, img, filename;
	static JTextArea descr;
	static JTextField name;
	static JButton upload, choose, addgoal, back;
	static JScrollPane desc;
	
	public Screen8()
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
		
		descr = new JTextArea();
		name = new JTextField();
		
		upload = new JButton("Upload Image");
		addgoal = new JButton("Add Goal");
		back = new JButton("Back");
		choose = new JButton("Browse");
		
		/*imgchsr = new JFileChooser();			file chooser :D
		imgFilter filter = new imgFilter();
		imgchsr.setFileFilter(filter);*/
		
		goalname = new JLabel("My goal");
		description = new JLabel("Description");
		img = new JLabel("Choose image");
		filename = new JLabel("filename.jpg"); //this should be an empty string if the user has not selected a file
		
		desc = new JScrollPane(descr);
		
		
		//add contents to pane
		pane.add(panel);
		panel.add(goalname);
		panel.add(name);
		panel.add(description);
		panel.add(img);
		panel.add(desc);
		panel.add(upload);
		panel.add(addgoal);
		panel.add(back);
		panel.add(choose);
		panel.add(filename);
		
		//set bounds
		panel.setBounds(0, 0, 500, 500);
		goalname.setBounds(10,25,80,20);
		name.setBounds(75,25,200,20);
		description.setBounds(10, 55, 80, 20);
		desc.setBounds( 10, 75, 300, 100);
		img.setBounds( 10, 200, 80, 20);
		choose.setBounds(10,230,140,20);
		upload.setBounds(170,230,140,20);
		filename.setBounds(10, 250, 80, 20);
		addgoal.setBounds(10, 300, 300, 25);
		back.setBounds(10, 330, 300, 25);
	}
	
	public static void main ( String[] args )
	{
		Screen8 screen8 = new Screen8();
	}
}

/*class imgFilter extends FileFilter
{
	public boolean accept(File f) {
			if (f.isDirectory()) {
				return true;
			}

			String extension = Utils.getExtension(f);
			if (extension != null) {
				if (extension.equals(Utils.tiff) ||
					extension.equals(Utils.tif) ||
					extension.equals(Utils.gif) ||
					extension.equals(Utils.jpeg) ||
					extension.equals(Utils.jpg) ||
					extension.equals(Utils.png)) {
						return true;
				} else {
					return false;
				}
			}

			return false;
		}

		//The description of this filter
		public String getDescription() {
			return "Just Images";
		}
}

class Utils {

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";

    /*
     * Get the extension of a file.
    
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
}*/