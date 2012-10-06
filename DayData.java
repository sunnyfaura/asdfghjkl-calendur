import java.util.ArrayList;

public class DayData
{
	public ArrayList<Event> events = null;
	public ArrayList<Task> tasks = null;
	public ArrayList<String> eventNames = null;
	public ArrayList<String> taskNames = null;
	
	public DayData(int y, int m, int d)
	{
		events = DatabaseRW.queryDayEvents(y, m, d);
		tasks = DatabaseRW.queryDayTasks(y, m, d);
		
		eventNames = new ArrayList<String>();
		
		for(Event e : events)
		{
			eventNames.add(e.name);
		}
		
		taskNames = new ArrayList<String>();
		
		for(Task t : tasks)
		{
			taskNames.add(t.name);
		}
	}
}