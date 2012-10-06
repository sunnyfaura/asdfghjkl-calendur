import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

/*
	Query by day --> check if since last open, may task na nagdeadline --> yes: pop notifs
	check if there are tasks for today --> check time --> notify 10 mins before
*/

public class FileRead extends Thread
{
	String filename;
	int delayInMins;
	public FileRead(ArrayList<Task> t, String lastOpen, String filename, int delayInMins)
	{
		this.filename = filename;
		this.delayInMins = delayInMins;
		checkPastEvents(t,lastOpen );
	}

	public void run()
	{
		try
		{
			while(true)
			{
				if(anotherWillAlarm("2012-10-06 03:18:00.000")) //change this parameter to deadline
				{
					readFile(filename);
				}
				Thread.sleep(setDelay(delayInMins));
				
			}
		}
		catch( Exception ex)
		{
		}
	}
	
	public int setDelay(int bleh) //bleh in seconds
	{	return (bleh*1000);
	}
	
	public void readFile(String fn)
	{
		try
		{
			System.out.println("thread");
			
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
	
	public boolean anotherWillAlarm(String current)
	{
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
		Date now = new Date();
		String s = sdfDate.format(now);
		Date deadline = null;
		try{
		deadline = sdfDate.parse(current);}
		catch(Exception e){}
		String s1 = sdfDate.format(deadline);
		
		String dateNow = s.substring(0,11);
		String dateDeadline = s1.substring(0,11);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1Now = null;
		Date d2Deadline = null;
		try{
			d1Now = sdf.parse(dateNow);
			d2Deadline = sdf.parse(dateDeadline);}
			catch(Exception e){}
		
		int cmp = d1Now.compareTo(d2Deadline);
		
		if( cmp < 0 )
		{
			return false;
		}
		else if( cmp == 0 )
		{ //same date
			SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
			String timeNow = s.substring(11, (s.length()-4));
			String timeDeadline0 = s1.substring(11, (s.length()-4));
			int m0 = Integer.parseInt(timeDeadline0.charAt(3) + "");
			String timeDeadline = "";
			if(m0 > 0)
			{
				m0 = m0 - 1;
				timeDeadline = timeDeadline0.substring(0, 3) + m0 + timeDeadline0.substring(4, timeDeadline0.length());
			}
			else
			{
				int h0 = Integer.parseInt(timeDeadline0.charAt(1) + "") - 1;
				timeDeadline = timeDeadline0.charAt(0) + "" + h0 + ":5" + timeDeadline0.substring(4, timeDeadline0.length());
			}

			Date d3Now = null;
			Date d4Deadline = null;
			try{
			d3Now = sdf1.parse(timeNow);
			d4Deadline = sdf1.parse(timeDeadline);}
			catch(Exception e){}
			
			int cmpAgain = d3Now.compareTo(d4Deadline);
			System.out.println(timeNow);
			System.out.println(timeDeadline);
			System.out.println(cmpAgain);

			if(cmpAgain < 0)
			{
				System.out.println("do you even go here");
				return false; //before
			}
			else if( cmpAgain == 0)
			{
				System.out.println("wat dapak is happening");
				return true; //exact
			}
		}
		return false;
	}
	
	public void checkPastEvents( ArrayList<Task> t, String lastOpen ) //query tasks from last open until current time
	{
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");//dd/MM/yyyy
		Date now = new Date();
		Date last = null;
		try{
		last = sdfDate.parse(lastOpen);}
		catch(Exception e){}
		if( t.size() > 0)
		{
			for(int i=0; i < t.size(); i++ )
			{
				readFile("test.txt");
			}
		}
	}
	
	public static void main(String[] args)
	{
		ArrayList<Task> asdf = null;
		Scanner in = new Scanner(System.in);
		String fn = in.next();
		int i = in.nextInt();
		Thread thr = new FileRead(asdf,fn,"test.txt",500);
		thr.start();
	}
}