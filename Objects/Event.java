public class Event extends Entry
{
	public boolean isAllDay;
	public Time endTime;
	public int repeating; //0 = never, 1 = yearly, 2 = monthly, 3 = weekly, 4 = daily


	
	public Event( String n, String dsc, String dt, boolean b, String t, String t2, )
	{
		name = n;
		desc = dsc;
		date = dt;
		isAllDay = b;
		if( b )
		{
			t = null; //12am
			endTime = null;//12pm
		}
		else
		{
			time = t;
			endTime = t2;
		}
	}
	
	public void editIsAllDay( boolean b )
	{
		isAllDay = b;
	}
	
	public void editTime( Time t1, Time t2 )
	{
		if( !isAllDay )
		{
			time = t1;
			endTime = t2;
		}
	}
}