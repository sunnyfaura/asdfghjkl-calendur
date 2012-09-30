import java.sql.Timestamp;

public class Event extends Entry
{
	public boolean isAllDay;
	public Timestamp ets; 
	public int repeating; //0 = never, 1 = yearly, 2 = monthly, 3 = weekly, 4 = daily
	
	public Event( int i, String n, String dsc, String y, String m, String d, String h, String min, boolean ad, String ey, String emo, String ed, String eh, String emin, int r )
	{
		super(i, n, dsc, y, m, d, h, min);
		
		isAllDay = ad;
		
		//Format of Timestamp string: YYYY-MM-DD hh:mm:ss
		String s = ey + "-" + emo + "-" + ed + " " + eh + ":" + emin + ":00";

		ets = Timestamp.valueOf(s);

		repeating = r;
	}
}