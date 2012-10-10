import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Timestamp;

public class PinboardData
{
	public ArrayList<Event> events = null;
	public ArrayList<Task> toDoTasks = null;
	public ArrayList<Task> doingTasks = null;
	
	public String[] eventNames = null;
	public String[] toDoNames = null;
	public String[] doingNames = null;
	
	public PinboardData()
	{
		Timestamp start = new Timestamp(System.currentTimeMillis());
		
		events = DatabaseRW.queryPinboardEvents(start);
		toDoTasks = DatabaseRW.queryPinboardTasks(start, 0);
		doingTasks = DatabaseRW.queryPinboardTasks(start, 1);
		
		
		eventNames = new String[events.size()];
		int i = 0;
		for(Event e : events)
		{
			int m = (Integer.parseInt((e.startTime + "").substring(5,7)))-1;
			String d = "";
			if(m == 0) d = "12";
			else if(m < 10) d = "0" + m;
			else d = m + "";
			d = d + "/" + (e.startTime + "").substring(8,10);
			eventNames[i] = d + " " + e.name;
			i++;
		}
		
		toDoNames = new String[toDoTasks.size()];
		i = 0;
		for(Task t : toDoTasks)
		{
			toDoNames[i] = t.name;
			i++;
		}
		
		doingNames = new String[doingTasks.size()];
		i = 0;
		for(Task t : doingTasks)
		{
			doingNames[i] = t.name;
			i++;
		}
	}
}