import java.util.*;
import java.sql.*;

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
		database.addEvent(name, desc, year, month, day, hour, minute, isAllDay, endYear, endMonth, endDay, endHour, endMinute, repeating);
	}
	
	public static void updateEvent(int id, String name, String desc, int year, int month, int day, int hour, int minute, boolean isAllDay, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeating)
	{
		database.updateEvent(id, name, desc, year, month, day, hour, minute, isAllDay, endYear, endMonth, endDay, endHour, endMinute, repeating);
	}
	
	public static void deleteEvent(int id)
	{
		database.deleteEvent(id);
	}
	
	public static void addTask(String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority)
	{
		database.addTask(name, desc, year, month, day, hour, minute, status, priority);
	}
	
	public static void updateTask(int id, String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority, String toReplace)
	{
		database.updateTask(id, name, desc, year, month, day, hour,  minute, status, priority, toReplace);
	}
	
	public static void deleteTask(int id)
	{
		database.deleteTask(id);
	}
	
	public static ArrayList<Entry> dayQuery(int year, int month, int day) throws Exception
	{
		ResultSet taskResults = database.dayTasksQuery(year, month, day);
		ResultSet eventResults = database.dayEventsQuery(year, month, day);
		
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
	
	public static ArrayList<Task> toDoQuery(int year, int month, int day) throws Exception
	{
		ResultSet results = database.pinboardQuery(year, month, day, 0);
		
		return toTaskArray(results);
	}
	
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
			int year = rs.getInt(4);
			int month = rs.getInt(5);
			int day = rs.getInt(6);
			int hour = rs.getInt(7);
			int minute = rs.getInt(8);
			boolean isAllDay = rs.getBoolean(9);
			int endYear = rs.getInt(10);
			int endMonth = rs.getInt(11);
			int endDay = rs.getInt(12);
			int endHour = rs.getInt(13);
			int endMinute = rs.getInt(14);
			int repeating = rs.getInt(15);
			 
			list.add(new Event(id, name, desc, year, month, day, hour, minute, isAllDay, endYear, endMonth, endDay, endHour, endMinute, repeating));
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
			int year = rs.getInt(4);
			int month = rs.getInt(5);
			int day = rs.getInt(6);
			int hour = rs.getInt(7);
			int minute = rs.getInt(8);
			int status = rs.getInt(9);
			int priority = rs.getInt(10);
			 
			list.add(new Task(id, name, desc, year, month, day, hour, minute, status, priority));
		}
			
			return list;
	}
}