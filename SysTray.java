import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import java.sql.Timestamp;

public class SysTray {
    final static Calendar calendar =new Calendar();
    final static Image image = Toolkit.getDefaultToolkit().getImage("tray.jpg");
    final static PopupMenu popup = new PopupMenu();
    final static TrayIcon trayIcon = new TrayIcon(image, "The Life Planner", popup);
    static Timestamp fin = new Timestamp(System.currentTimeMillis()-10000000);
    
    public static void main(String[] asdf) {
        Derby database = new Derby();
        DatabaseRW.setDatabase(database);
        //database.init();

        trayIcon.setImageAutoSize(true);

        Runnable runner = new Runnable() {
             public void run() {
                if (SystemTray.isSupported()) {
                    final SystemTray tray = SystemTray.getSystemTray();
                    final FileReadAgain fr = new FileReadAgain("hello.txt",fin);
                    
                    MenuItem item = new MenuItem();
                        PopupMenu comments = new PopupMenu("Choose a mood for commenter..  ");
                        MenuItem moods = new MenuItem("Diligent");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling diligent today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: diligent");
                                fr.run("diligent");
                            }
                        });

                        moods = new MenuItem("Responsible       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling responsible today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Responsible");
                                //fr.run("responsible");
                            }
                        });

                        moods = new MenuItem("Lazy       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling lazy today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Lazy");
                                //fr.run("lazy");
                            }
                        });

                        moods = new MenuItem("Cramming Mode       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling like cramming today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Cramming");
                                //fr.run("cramming");
                            }
                        });
                        
                        moods = new MenuItem("Normal       ");
                        comments.add(moods);
                        moods.addActionListener(new ActionListener(){
                            public void actionPerformed(ActionEvent e){
                                trayIcon.displayMessage("I am feeling like cramming today","asasfdg",TrayIcon.MessageType.INFO);
                                System.out.println("mood: Cramming");
                                //fr.run("normal");
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