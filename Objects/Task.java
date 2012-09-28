public class Task extends Entry
{
	public int status;
	public int priority;
	
	public Task( String n, String dsc, String dt, String t, int i, int j )
	{
		name = n;
		desc = dsc;
		date = dt;
		time = t;
		status = i;
		priority = j;
	}
	
	public void editStatus( int i )
	{
		status = i;
	}
	
	public boolean isDone()
	{
		return (status == 2);
	}
	
	public void editPriority( int i )
	{
		priority = i;
	}
}