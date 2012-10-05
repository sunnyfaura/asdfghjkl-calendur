import java.sql.Timestamp;

public class Event extends Entry
{
	public int repeating; //0 = never, 1 = yearly, 2 = monthly, 3 = weekly, 4 = daily
	public int repeatKey;
	
	public Event( int i, String n, String dsc, Timestamp ts, int r, int rk )
	{
		super(i, n, dsc, ts);

		repeating = r;
		repeatKey = rk;
	}
}