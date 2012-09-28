/*
header comments

*/

public abstract class Entry
{
	public String name;
	public String desc;
	public int year;
	public int month;
	public int day;
	public int hour;
	public int minute;
	
	protected Entry(String n, String dsc, int y, int mo, int d, int h, int min)
	{
		name = n;
		desc = dsc;
		year = y;
		month = mo;
		day = d;
		hour = h;
		minute = min;
	}
}