import java.sql.Timestamp;

public class Task extends Entry
{
	public int status;
	public int priority;
	
	public Task(int i, String n, String dsc, Timestamp ts, int s, int p)
	{
		super(i, n, dsc, ts);
		
		status = s;
		priority = p;
	}
}