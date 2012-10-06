import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

public class MonthData
{
	public ArrayList<Event> events = null;
	public ArrayList<Task> tasks = null;
	public ArrayList<Integer> eventDays = null;
	public ArrayList<Integer> taskDays = null;
	
	public MonthData(int y, int m, int d)
	{
		events = DatabaseRW.queryMonthEvents(y, m, d);
		tasks = DatabaseRW.queryMonthTasks(y, m, d);
		
		eventDays = new ArrayList<Integer>();
		
		for(Event e : events)
		{
			Calendar temp = Calendar.getInstance();
			temp.setTimeInMillis(e.startTime.getTime());
			eventDays.add(temp.get(Calendar.DATE));
		}
		
		taskDays = new ArrayList<Integer>();
		
		for(Task t : tasks)
		{
			Calendar temp = Calendar.getInstance();
			temp.setTimeInMillis(t.startTime.getTime());
			eventDays.add(temp.get(Calendar.DATE));
		}
	}
}