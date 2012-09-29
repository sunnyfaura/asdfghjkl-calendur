public class Event extends Entry
{
	public boolean isAllDay;
	public int endYear;
	public int endMonth;
	public int endDay;
	public int endHour;
	public int endMinute;
	public int repeating; //0 = never, 1 = yearly, 2 = monthly, 3 = weekly, 4 = daily
	
	public Event( String n, String dsc, int y, int m, int d, int h, int min, boolean ad, int ey, int emo, int ed, int eh, int emin, int r )
	{
		super(n, dsc, y, m, d, h, min);
		
		isAllDay = ad;
		endYear = ey;
		endMonth = emo;
		endDay = ed;
		endHour = eh;
		endMinute = emin;
		repeating = r;
	}
}