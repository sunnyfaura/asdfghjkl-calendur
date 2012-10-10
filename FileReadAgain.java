import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class FileReadAgain extends Thread
{
	static String fn;
	PinboardData pbd;
	String mood;
	public FileReadAgain(String filename)
	{
		fn = filename;
		pbd = new PinboardData();
		//checkPastEvents(lastOpened);
	}

	public boolean compareDate(Entry e) //compareDate checks something should be notified 10 mins before
	{
		Timestamp t1 = e.startTime;
		Calendar st = Calendar.getInstance();
		st.add(Calendar.MINUTE, -10); //subtract 10 mins from deadline
		st.setTime(t1);
		Calendar now = Calendar.getInstance();
		int cmp = now.compareTo(st);

		return (cmp == 0);
	}

	public static void main(String[] args)
	{
		Date today = new Date();
		System.out.println(new java.sql.Timestamp(today.getTime()));
		checkPastEvents(new java.sql.Timestamp(today.getTime()));
	}


	public void checkEventsList() //checks events from the PinboardData
	{
		if(pbd.events.size() > 0)
		{
			for(int i=0; i<pbd.events.size();i++)
			{
				if(compareDate(pbd.events.get(i)))
					SysTray.trayIcon.displayMessage(pbd.eventNames[i],selectMessage(1),TrayIcon.MessageType.INFO);

			}
		}
	}

	public void checkToDoList() //checks todo list from PinboardData
	{
		if(pbd.toDoTasks.size() > 0)
		{
			for(int i=0; i<pbd.toDoTasks.size();i++)
			{
				if(compareDate(pbd.toDoTasks.get(i)))
					SysTray.trayIcon.displayMessage(pbd.toDoNames[i],selectMessage(2),TrayIcon.MessageType.INFO);

			}
		}
	}

	public void checkDoingList() //checks doing list from PinboardData
	{
		if(pbd.doingTasks.size() > 0)
		{
			for(int i=0; i<pbd.doingTasks.size();i++)
			{
				if(compareDate(pbd.doingTasks.get(i)))
					SysTray.trayIcon.displayMessage(pbd.doingNames[i],selectMessage(3),TrayIcon.MessageType.INFO);

			}
		}
	}


	public static String[] readFile() //filereader part
	{
		try
		{
			FileReader fr = new FileReader(fn);
			BufferedReader br = new BufferedReader( fr );
			String str;
			String out[] = new String[100];
			int i=0;
				while( (str = br.readLine()) != null )
				{
					out[i] = str.substring(2,str.length());
					i++;
				}

			fr.close();
			return out;
				
		}
		catch(Exception e)
		{
			System.err.println("Read file error: "+e.getMessage());
			System.err.println("Read file error: "+e);
		}
	return null;
	}

	public static String selectMessage(int n)
	{
		String[] msg = readFile();
		return msg[n];
	}

	public static void checkPastEvents(Timestamp lastOpened) //checks for events/tasks that happened since you last closed the program until current date
	{
		Calendar c = Calendar.getInstance();
		c.setTime(lastOpened);		

		Calendar today = Calendar.getInstance();
		today.setTime(today.getTime());
		while(c.compareTo(today) < 0)
		{
			String lo = lastOpened.toString();
			int year = Integer.parseInt(lo.substring(0,4));
			int month = Integer.parseInt(lo.substring(5,7));
			int day = Integer.parseInt(lo.substring(8,10));
			DayData dd = new DayData(year,month,day);

			if(dd.events.size() > 0)
			{
				for(int i=0; i<dd.events.size(); i++)
				{
					SysTray.trayIcon.displayMessage(dd.eventNames.get(i),selectMessage(1),TrayIcon.MessageType.INFO);
				}
			}

			if(dd.tasks.size() > 0)
			{
				for(int i=0; i<dd.tasks.size(); i++)
				{
					SysTray.trayIcon.displayMessage(dd.taskNames.get(i),selectMessage(4),TrayIcon.MessageType.INFO);
				}
			}

			c.add(Calendar.DAY_OF_WEEK, 1);

		}
	}

	public void getMood(String m)
	{
		mood = m;
	}

	public void run()
	{
		try
		{
			while(true)
			{
				if(mood.equals("normal"))
				{	checkEventsList();
					checkDoingList();
				}
				else if(mood.equals("diligent"))
				{
					checkEventsList();
					checkToDoList();
				}
				else if(mood.equals("cramming"))
				{
					checkDoingList();
				}
				else if(mood.equals("lazy"))
				{
					checkEventsList();
				}
				else if(mood.equals("responsible"))
				{
					checkToDoList();
					checkDoingList();
				}
				Thread.sleep(500);
				
			}
		}
		catch( Exception ex)
		{
		}
	}
}