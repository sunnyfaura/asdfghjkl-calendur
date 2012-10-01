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

	public static void addEvent(String name, String desc, int year, int month, int day, int hour, int minute, boolean isAllDay, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeating)
	{
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		Timestamp ets = intToTimestamp(endYear, endMonth, endDay, endHour, endMinute);
		
		database.addEvent(name, desc, ts, isAllDay, ets, repeating);
	}
	
	public static void updateEvent(int id, String name, String desc, int year, int month, int day, int hour, int minute, boolean isAllDay, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeating)
	{
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		Timestamp ets = intToTimestamp(endYear, endMonth, endDay, endHour, endMinute);
		
		database.updateEvent(id, name, desc, ts, isAllDay, ets, repeating);
	}
	
	public static void deleteEvent(int id)
	{
		database.deleteEvent(id);
	}
	
	public static void addTask(String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority)
	{
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		
		//database.addTask(name, desc, ts, status, priority);
	}
	
	public static void updateTask(int id, String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority)
	{
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		
		//database.updateTask(id, name, desc, ts, status, priority);
	}
	
	public static void deleteTask(int id)
	{
		database.deleteTask(id);
	}
	
	public static ArrayList<Entry> dayQuery(int year, int month, int day) throws Exception
	{
		Timestamp start = intToTimestamp(year, month, day, 0, 0);
		Timestamp end = intToTimestamp(year, month, day + 1, 0, 0);
		
		ResultSet taskResults = database.dayTasksQuery(start, end);
		ResultSet eventResults = database.dayEventsQuery(start, end);
		
		ArrayList<Task> taskList = toTaskArray(taskResults);
		ArrayList<Event> eventList = toEventArray(eventResults);
		
		ArrayList<Entry> entryList = new ArrayList<Entry>();
		
		for(Task asdf : taskList)
		{
			entryList.add((Entry) asdf);
		}
		
		for(Event asdf : eventList)
		{
			entryList.add((Entry) asdf);
		}
		
		return entryList;
	}
	
	//fix this laterz
	public static ArrayList<Task> toDoQuery(int year, int month, int day) throws Exception
	{
		ResultSet results = database.pinboardQuery(year, month, day, 0);
		
		return toTaskArray(results);
	}
	
	//also this
	public static ArrayList<Task> doingQuery(int year, int month, int day) throws Exception
	{
		ResultSet results = database.pinboardQuery(year, month, day, 1);
		
		return toTaskArray(results);
	}
	
	public static ArrayList<Event> toEventArray(ResultSet rs) throws Exception
	{
		ArrayList<Event> list = new ArrayList<Event>();
		
		while(rs.next()) { //This will iterate through the query list
			
			//The following are values obtained from each iteration from the query list. 
			//UNOPTIMIZED. Please shove these values into the parameter code instead 
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String desc = rs.getString(3);
			Timestamp ts = rs.getTimestamp(4);
			boolean isAllDay = rs.getBoolean(5);
			Timestamp ets = rs.getTimestamp(6);
			int repeating = rs.getInt(7);
			 
			list.add(new Event(id, name, desc, ts, isAllDay, ets, repeating));
		}
		
		return list;
	}
	
	public static ArrayList<Task> toTaskArray(ResultSet rs) throws Exception
	{
		ArrayList<Task> list = new ArrayList<Task>();
		
		while(rs.next()) { //This will iterate through the query list
			//The following are values obtained from each iteration from the query list. 
			//UNOPTIMIZED. Please shove these values into the parameter code instead 
			int id = rs.getInt(1);
			String name = rs.getString(2);
			String desc = rs.getString(3);
			Timestamp ts = rs.getTimestamp(4);
			int status = rs.getInt(5);
			int priority = rs.getInt(6);
			 
			list.add(new Task(id, name, desc, ts, status, priority));
		}
		
		return list;
	}
	
	public static Timestamp intToTimestamp(int year, int month, int day, int hour, int min)
	{
		Calendar tempCal = Calendar.getInstance();
		
		tempCal.set(year, month, day, hour, min);
		
		Timestamp ts = new Timestamp(tempCal.getTimeInMillis());
		
		return ts;
	}
}