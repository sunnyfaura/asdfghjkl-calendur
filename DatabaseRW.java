import java.util.Calendar;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Timestamp;

//call this thingy with DatabaseRW.[functionwhatever] from anywhar

public class DatabaseRW
{
	static Derby database = null;

	public static void setDatabase(Derby db)
	{
		database = db;
	}

	public static int addTask(String n, String de, int y, int mo, int da, int h, int mi, int s, int p)
	{
		Timestamp startTime = intToTimestamp(y, mo, da, h, mi);
		
		int key = database.addTask(n, de, startTime, s, p);
		return key;
	}
	
	public static int addEvent(String n, String de, int y, int mo, int da, int h, int mi, int r, int rk)
	{
		Timestamp startTime = intToTimestamp(y, mo, da, h, mi);
		
		int key = database.addEvent(n, de, startTime, r, k);
		return key;
	}
	
	public static int updateEvent(int i, String n, String d, int y, int m, int da, int h, int mi, int r, int rk);
	{
		Timestamp startTIme = intToTimestamp(y, m, da, h, mi);
		
		int returny = database.updateTask(i, n, d, startTime, r, rk);
		
		return returny;
	}
	
	public static int updateTask(int i, String n, String d, int y, int m, int da, int h, int mi, int s, int p);
	{
		Timestamp startTIme = intToTimestamp(y, m, da, h, mi);
		
		int returny = database.updateTask(i, n, d, startTime, s, p);
		
		return returny;
	}
	
	public static int deleteEvent(int id)
	{
		int returny = database.deleteEvent(id);
		
		return returny;
	}
	
	public static int deleteTask(int id);
	{
		int returny = database.deleteTask(id);
		
		return returny;
	}
	
	public static ArrayList<Event> queryEvents(Timestamp start, Timestamp end)
	{
		return toEventArray(queryEvents(start, end));
	}
	
	public static ArrayList<Task> queryTasks(Timestamp start, Timestamp end)
	{
		return toTaskArray(queryTasks(start, end);
	}
	
	public static ArrayList<Event> queryDayEvents(int y, int m, int d)
	{
		Timestamp start = intToTimestamp(y, m, d, 0, 0);
		Timestamp end = intToTimestamp(y, m, d + 1, 0, 0);
		
		return queryEvents(start, end);
	}
	
	public static ArrayList<Task> queryDayTask(int y, int m, int d)
	{
		Timestamp start = intToTimestamp(y, m, d, 0, 0);
		Timestamp end = intToTimestamp(y, m, d + 1, 0, 0);
		
		return queryTasks(start, end);
	}

	public static void queryTask(){
		answer = 41;
		//database.go();
	}

	/*==================================*/
	/* Beginning of getValue() methods */
	/*================================*/
	//KILLED
	
	/*===========================*/
	/* Conveniece methods! Yay! */
	/*=========================*/
	
	public static Timestamp intToTimestamp(int year, int month, int day, int hour, int minute)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.set(year, month, day, hour, minute);
		
		return new Timestamp(tempCal.getTimeInMillis());
	}
	
	public static ArrayList<Event> toEventArray(ResultSet rs)
	{
		ArrayList<Event> returny = new ArrayList<Event>();
		
		try{
			while(rs.next())
			{
				int id = rs.getInt(1);
				String n = rs.getString(2);
				String d = rs.getString(3);
				Timestamp start = rs.getTimeStamp(4);
				int r = rs.getInt(5);
				int rk = rs.getInt(6);
				
				returny.add(new Event(id, n, d, start, r, rk);
			}
		} catch (SQLException balls){}
		
		return null;
	}
	
	public static ArrayList<Task> toEventArray(ResultSet rs)
	{
		ArrayList<Task> returny = new ArrayList<Task>();
		
		try{
			while(rs.next())
			{
				int id = rs.getInt(1);
				String n = rs.getString(2);
				String d = rs.getString(3);
				Timestamp start = rs.getTimeStamp(4);
				int s = rs.getInt(5);
				int p = rs.getInt(6);
				
				returny.add(new Task(id, n, d, start, s, p);
			}
		} catch (SQLException balls){}
		
		return null;
	}
}
