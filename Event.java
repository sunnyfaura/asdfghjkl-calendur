import java.sql.Timestamp;

public class Event extends Entry
{
	public boolean isAllDay;
	public Timestamp endTime; 
	public int repeating; //0 = never, 1 = yearly, 2 = monthly, 3 = weekly, 4 = daily
	
	public Event( int i, String n, String dsc, Timestamp ts, boolean ad, int r )
	{
		super(i, n, dsc, ts);
		
		isAllDay = ad;

		repeating = r;
	}
}