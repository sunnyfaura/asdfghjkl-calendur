import java.sql.Timestamp;

public abstract class Entry
{
	public int id;
	public String name;
	public String desc;
	public Timestamp ts;
	
	protected Entry(int i, String n, String dsc, String y, String mo, String d, String h, String min)
	{
		id = i;
		name = n;
		desc = dsc;

		//Format of Timestamp string: YYYY-MM-DD hh:mm:ss
		String s = y + "-" + mo + "-" + d + " " + h + ":" + min + ":00";

		ts = Timestamp.valueOf(s);
	}
}