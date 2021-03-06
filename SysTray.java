import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class SysTray {
    final static Calendar calendar = new Calendar();
    final static Image image = Toolkit.getDefaultToolkit().getImage("tray.jpg");
    final static PopupMenu popup = new PopupMenu();
    final static TrayIcon trayIcon = new TrayIcon(image, "The Life Planner", popup);

    public static void main(String[] asdf) {
        Derby database = new Derby();
        DatabaseRW.setDatabase(database);
        //database.init();

        trayIcon.setImageAutoSize(true);
        final FileReadAgain fr = new FileReadAgain("hello.txt");
        fr.checkPastEvents(WriteText.getLastTimeClosed());

        Runnable runner = new Runnable() {
             public void run() {
                if (SystemTray.isSupported()) {
                    final SystemTray tray = SystemTray.getSystemTray();
                    fr.run();
                    
                    MenuItem item = new MenuItem();
                        PopupMenu comments = new PopupMenu("Choose a mood for commenter..  ");
                        MenuItem moods = new MenuItem("Diligent");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling diligent today","asasfdg",TrayIcon.MessageType.INFO);
                                //System.out.println("mood: diligent");
                                fr.getMood("diligent"); //run the thread depending on user's mood
                                //for diligent: notify for events and to do list
                            }
                        });

                        moods = new MenuItem("Responsible       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling responsible today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Responsible");
                                fr.getMood("responsible");
                                //for responsible: notify for to do and doing
                            }
                        });

                        moods = new MenuItem("Lazy       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling lazy today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Lazy");
                                fr.getMood("lazy");
                                //for lazy: notify for events only
                            }
                        });

                        moods = new MenuItem("Cramming Mode       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling like cramming today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Cramming");
                                fr.getMood("cramming");
                                //cramming: doing
                            }
                        });
                        
                        moods = new MenuItem("Normal       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling like cramming today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Cramming");
                                fr.getMood("normal");
                                //normal: events + doing
                            }
                        });

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
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            //get current date time with Date()
                            Date date = new Date();
                            String now = dateFormat.format(date) + "";
                            WriteText wt = new WriteText();
                            wt.addNewTimeClosed(now);
                            //System.out.println("Added to TextFile: " + now);
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