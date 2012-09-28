/*
header comments

*/

public abstract class Entry
{
	public String name;
	public String desc;
	public String date;
	public String time;
	
	public void editName(String n)
	{
		name = n;
	}
	
	public void editDesc(String d)
	{
		desc = d;
	}
	
	public void editDate(Date d)
	{
		date = d;
	}
	
	public void editTime(boolean b, Time t)
	{
		if(b) time = t;
	}
}