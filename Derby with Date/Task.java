public class Task extends Entry
{
	public int status;
	public int priority;
	
	public Task(int i, String n, String dsc, String y, String m, String d, String h, String min, int s, int p)
	{
		super(i, n, dsc, y, m, d, h, min);
		
		status = s;
		priority = p;
	}
}