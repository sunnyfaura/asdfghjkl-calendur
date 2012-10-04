import java.sql.Timestamp;

public abstract class Entry
{
	public int id;
	public String name;
	public String desc;
	public Timestamp time;
	
	protected Entry(int i, String n, String dsc, Timestamp t)
	{
		id = i;
		name = n;
		desc = dsc;
		time = t;
	}

	public String getName()
	{
		return name;
	}
}