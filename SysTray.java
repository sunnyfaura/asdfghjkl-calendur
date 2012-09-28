import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class SysTray {
    public static void main(String[] asdf) {
        final Calendar calendar =new Calendar();
        Runnable runner = new Runnable() {
             public void run() {
                if (SystemTray.isSupported()) {
                    final SystemTray tray = SystemTray.getSystemTray();
                    Image image = Toolkit.getDefaultToolkit().getImage("tray.jpg");
                    PopupMenu popup = new PopupMenu();
                    final TrayIcon trayIcon = new TrayIcon(image, "The Life Planner", popup);
                    trayIcon.setImageAutoSize(true);
                    MenuItem item = new MenuItem();
                        PopupMenu comments = new PopupMenu("Choose a mood for commenter..  ");
                        MenuItem moods = new MenuItem("Happy");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                        moods = new MenuItem("Happy       ");
                        comments.add(moods);
                    popup.add(comments);
                    popup.addSeparator();
                    item = new MenuItem("Open Calendar..      ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed (ActionEvent e){
                            if(calendar.calendarOpen%2==0){
                                calendar.setVisible(true);
                                calendar.calendarOpen++;
                            }
                        }
                    });
                    popup.add(item);
                    item = new MenuItem("Open Pinboard..      ");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed (ActionEvent e){
                            if(calendar.pinboardOpen%2==0){
                                calendar.pinboard_frame.createAndShowGUI();
                                calendar.pinboardOpen++;
                            }
                        }
                    });
                    popup.add(item);
                    item = new MenuItem("Close");
                    item.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            tray.remove(trayIcon);
                            calendar.closeCalendar();
                        }
                    });
                    popup.add(item);
                    try {
                        tray.add(trayIcon);
                    } catch (AWTException e) {
                        System.err.println("Can't add to tray");
                    }
                } else {
                    System.err.println("Tray unavailable");
                }
             }
         };
        EventQueue.invokeLater(runner);
    }
}