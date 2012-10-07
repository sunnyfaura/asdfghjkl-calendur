import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;

public class FileReadAgain extends Thread
{
	static String fn = "hello.txt";
	PinboardData pbd;

	public FileReadAgain(String filename, Timestamp lastOpened)
	{
		fn = filename;
		pbd = new PinboardData();
		checkPastEvents(lastOpened);
	}

	public boolean compareDate(Entry e)
	{
		Timestamp t1 = e.startTime;
		Calendar st = Calendar.getInstance();
		st.add(Calendar.MINUTE, -10);
		st.setTime(t1);
		Calendar now = Calendar.getInstance();
		int cmp = now.compareTo(st);

		return (cmp == 0);
	}

	public static void main(String[] args)
	{
		Derby database = new Derby();
    	DatabaseRW.setDatabase(database);
		Date today = new Date();
		System.out.println(new java.sql.Timestamp(today.getTime()));
		checkPastEvents(new java.sql.Timestamp(today.getTime()));
	}

	public void readForNotif(String mood)
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
	}

	public void checkEventsList()
	{
		if(pbd.events.size() > 0)
		{
			for(int i=0; i<pbd.events.size();i++)
			{
				if(compareDate(pbd.events.get(i)))
					readFile();

			}
		}
	}

	public void checkToDoList()
	{
		if(pbd.toDoTasks.size() > 0)
		{
			for(int i=0; i<pbd.toDoTasks.size();i++)
			{
				if(compareDate(pbd.toDoTasks.get(i)))
					readFile();
			}
		}
	}

	public void checkDoingList()
	{
		if(pbd.doingTasks.size() > 0)
		{
			for(int i=0; i<pbd.doingTasks.size();i++)
			{
				if(compareDate(pbd.doingTasks.get(i)))
					readFile();
			}
		}
	}


	public static void readFile()
	{
		try
		{
			FileReader fr = new FileReader(fn);
			BufferedReader br = new BufferedReader( fr );
			String str;
				while( (str = br.readLine()) != null )
				{
					System.out.println(str);
				}
			fr.close();
				
		}
		catch(Exception e)
		{
			System.err.println("Error: "+e.getMessage());
		}
	}

	public static void checkPastEvents(Timestamp lastOpened)
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
					readFile();
				}
			}

			if(dd.tasks.size() > 0)
			{
				for(int i=0; i<dd.tasks.size(); i++)
				{
					readFile();
				}
			}

			c.add(Calendar.DAY_OF_WEEK, 1);

		}
	}

	public void run(String mood)
	{
		try
		{
			while(true)
			{
				readForNotif(mood);
				Thread.sleep(500);
			}
		}
		catch( Exception ex)
		{
		}
	}
}