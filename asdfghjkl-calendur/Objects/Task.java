public class Task extends Entry
{
	public int status;
	public int priority;
	
	public Task( String n, String dsc, int y, int m, int d, int h, int min, int s, int p)
	{
		super(n, dsc, y, m, d, h, min);
		
		status = s;
		priority = p;
	}
}