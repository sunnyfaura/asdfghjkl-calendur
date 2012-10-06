import java.util.Calendar;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Timestamp;

//call this thingy with DatabaseRW.[functionwhatever] from anywhar

public class DatabaseRW
{
	public static int answer;
	public static String name, desc;
	public static int status, priority;
	public static boolean isAllDay;
	public static int endYear, endMonth, endDay, endHour, endMinute, repeating;
	public static Timestamp startTime, endTime, queryStart, queryEnd;

	static Derby database = null;

	public static void setDatabase(Derby db)
	{
		database = db;
	}

	public static void addTask(String n, String de, int y, int mo, int da, int h, int mi, int s, int p)
	{
		name = n;
		desc = de;
		status = s;
		priority = p;
		startTime = intToTimestamp(y, mo, da, h, mi);

		answer = 11;
		database.insertTask(name,desc,startTime,status,priority);		
		System.out.println("Task table has been updated.");
		
	}
	
	public static void updateTask(int id, String name, String desc, int year, int month, int day, int hour, int minute, int status, int priority)
	{
		
	}
	
	public static void deleteTask(int id)
	{

	}

	public static void addEvent(String n, String de, int y, int mo, int da, int h, int mi, boolean iad, int r)
	{
		name = n;
		desc = de;
		startTime = intToTimestamp(y, mo, da, h, mi);
		isAllDay = iad;
		repeating = r;
		
		answer = 12;

		System.out.println("Event table has been updated.");
		//database.go();
	}
	
	public static void updateEvent(int id, String name, String desc, int year, int month, int day, int hour, int minute, boolean isAllDay, int endYear, int endMonth, int endDay, int endHour, int endMinute, int repeating)
	{

	}
	
	public static void deleteEvent(int id)
	{
		
	}

	public static void queryTask(){
		answer = 41;
		//database.go();
	}

	/*==================================*/
	/* Beginning of getValue() methods */
	/*================================*/

	public static int getAnswer(){
		return answer;
	}

	public static String getName(){
		return name;
	}

	public static String getDesc(){
		return desc;
	}

	public static int getStatus(){
		return status;
	}

	public static int getPriority(){
		return priority;
	}

	public static boolean getIsAllDay(){
		return isAllDay;
	}

	public static int getRepeating(){
		return repeating;
	}

	public static Timestamp getStartTime(){
		return startTime;
	}
	
	public static Timestamp getTimestamp(){
		return startTime;
	}
	
	/*===========================*/
	/* Conveniece methods! Yay! */
	/*=========================*/
	
	public static Timestamp intToTimestamp(int year, int month, int day, int hour, int minute)
	{
		Calendar tempCal = Calendar.getInstance();
		tempCal.set(year, month, day, hour, minute);
		
		return new Timestamp(tempCal.getTimeInMillis());
	}
}
