import java.util.Calendar;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Timestamp;

//call this thingy with DatabaseRW.[functionwhatever] from anywhar
//updates these values in Derby.java:
//answer
//entry columns
//task columns
//event columns

public class DatabaseRW
{
	static Derby database = null;

	public static void setDatabase(Derby db)
	{
		database = db;
	}

	//Always define an answer before doing anything else
	public static void defineAnswer(int ans){
		database.answer = ans;
	}

	public static void addTask(String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority)
	{
		//Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		database.name = name;
		database.desc = desc;
		database.year = year;
		database.month = month;
		database.day = day;
		database.hour = hour;
		database.minute	= minute;
		database.status = status;
		database.priority = priority;

		database.answer = 11;
	}
	
	public static void updateTask(int id, String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority)
	{
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
	}
	
	public static void deleteTask(int id)
	{

	}

	public static void addEvent(String name, String desc, int year, int month, int day, int hour, int minute, boolean isAllDay, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeating)
	{
		//defineAnswer(ans);
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		Timestamp ets = intToTimestamp(endYear, endMonth, endDay, endHour, endMinute);
	}
	
	public static void updateEvent(int id, String name, String desc, int year, int month, int day, int hour, int minute, boolean isAllDay, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeating)
	{
		//defineAnswer(ans);
		Timestamp ts = intToTimestamp(year, month, day, hour, minute);
		Timestamp ets = intToTimestamp(endYear, endMonth, endDay, endHour, endMinute);
	}
	
	public static void deleteEvent(int id)
	{
		//defineAnswer(ans);
	}
	
	// public static void queryEvent() throws Exception
	// {
	// 	ArrayList<Event> temp = toEventArray(database.queryEvent());

	// 	for(Event asdf : temp)
	// 	{
	// 		System.out.println(asdf.name);
	// 	}
	// }

	// public static ArrayList<Entry> dayQuery(int year, int month, int day) throws Exception
	// {
	// 	Timestamp start = intToTimestamp(year, month, day, 0, 0);
	// 	Timestamp end = intToTimestamp(year, month, day + 1, 0, 0);
		
	// 	ResultSet taskResults = database.queryTasks(start, end);
	// 	//ResultSet eventResults = database.queryEvents(start, end);
		
	// 	ArrayList<Task> taskList = toTaskArray(taskResults);
	// 	//ArrayList<Event> eventList = toEventArray(eventResults);
		
	// 	ArrayList<Entry> entryList = new ArrayList<Entry>();
		
	// 	for(Task asdf : taskList)
	// 	{
	// 		entryList.add((Entry) asdf);
	// 	}
		
	// 	// for(Event asdf : eventList)
	// 	// {
	// 	// 	entryList.add((Entry) asdf);
	// 	// }
		
	// 	return entryList;
	// }
	
	// //fix this laterz
	// public static ArrayList<Task> toDoQuery(int year, int month, int day) throws Exception
	// {
	// 	ResultSet results = database.pinboardQuery(year, month, day, 0);
		
	// 	return toTaskArray(results);
	// }
	
	// //also this
	// public static ArrayList<Task> doingQuery(int year, int month, int day) throws Exception
	// {
	// 	ResultSet results = database.pinboardQuery(year, month, day, 1);
		
	// 	return toTaskArray(results);
	// }
	
	// public static ArrayList<Event> toEventArray(ResultSet rs) throws Exception
	// {
	// 	ArrayList<Event> list = new ArrayList<Event>();
		
	// 	while(rs.next()) { //This will iterate through the query list
			
	// 		//The following are values obtained from each iteration from the query list. 
	// 		//UNOPTIMIZED. Please shove these values into the parameter code instead 
	// 		int id = rs.getInt(1);
	// 		String name = rs.getString(2);
	// 		String desc = rs.getString(3);
	// 		Timestamp ts = rs.getTimestamp(4);
	// 		boolean isAllDay = rs.getBoolean(5);
	// 		Timestamp ets = rs.getTimestamp(6);
	// 		int repeating = rs.getInt(7);
			 
	// 		list.add(new Event(id, name, desc, ts, isAllDay, ets, repeating));
	// 	}
		
	// 	return list;
	// }
	
	// public static ArrayList<Task> toTaskArray(ResultSet rs) throws Exception
	// {
	// 	ArrayList<Task> list = new ArrayList<Task>();
		
	// 	while(rs.next()) { //This will iterate through the query list
	// 		//The following are values obtained from each iteration from the query list. 
	// 		//UNOPTIMIZED. Please shove these values into the parameter code instead 
	// 		int id = rs.getInt(1);
	// 		String name = rs.getString(2);
	// 		String desc = rs.getString(3);
	// 		Timestamp ts = rs.getTimestamp(4);
	// 		int status = rs.getInt(5);
	// 		int priority = rs.getInt(6);
			 
	// 		list.add(new Task(id, name, desc, ts, status, priority));
	// 	}
		
	// 	return list;
	// }
	
	public static Timestamp intToTimestamp(int year, int month, int day, int hour, int min)
	{
		Calendar tempCal = Calendar.getInstance();
		
		tempCal.set(year, month, day, hour, min);
		
		Timestamp ts = new Timestamp(tempCal.getTimeInMillis());
		
		return ts;
	}
}