import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

public class PinboardData
{
	public ArrayList<Event> events = null;
	public ArrayList<Task> toDoTasks = null;
	public ArrayList<Task> doingTasks = null;
	
	public ArrayList<String> eventNames = null;
	public ArrayList<String> toDoNames = null;
	public ArrayList<String> doingNames = null;
	
	public PinboardData()
	{
		Timestamp start = new Timestamp(System.currentTimeMillis());
		
		events = DatabaseRW.queryPinboardEvents(start);
		toDoTasks = DatabaseRW.queryPinboardTasks(start, 0);
		doingTasks = DatabaseRW.queryPinboardTasks(start, 1);
		
		
		eventNames = new ArrayList<String>();
		
		for(Event e : events)
		{
			eventNames.add(e.name);
		}
		
		toDoNames = new ArrayList<String>();
		
		for(Task t : toDoTasks)
		{
			toDoNames.add(t.name);
		}
		
		doingNames = new ArrayList<String>();
		
		for(Task t : doingTasks)
		{
			doingNames.add(t.name);
		}
	}
}