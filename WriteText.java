/** Simple Program to write a text file
*/

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;

public class WriteText{

	public void addNewTimeClosed(String timeClosed)
	{
		try
		{
			FileWriter outFile = new FileWriter("timeClosed.txt");
			PrintWriter out = new PrintWriter(outFile);
			out.println(timeClosed);
			out.close();
		}
		catch(Exception e){}
	}

	public static Timestamp getLastTimeClosed()
	{
		String str="";
		String out="";
		try
		{
			FileReader fr = new FileReader("timeClosed.txt");
			BufferedReader br = new BufferedReader( fr );
			
				while( (str = br.readLine()) != null )
				{
					out = out+""+str;
				}
			fr.close();
				
		}
		catch(Exception e)
		{
			System.err.println("Error: "+e.getMessage());
		}
		System.out.println(">>>>>>>>>>>"+out);
		Timestamp ts = Timestamp.valueOf(out);
		return ts;
	}

	public static void main(String[] args)
	{
		WriteText wt = new WriteText();
		wt.addNewTimeClosed("2012-10-06 23:25:00.045");
		System.out.println(wt.getLastTimeClosed());
		wt.addNewTimeClosed("2012-10-07 23:25:00.034");
		System.out.println(wt.getLastTimeClosed());
		//wt.addNewTimeClosed("2012-10-05 23:25:00.000");
		//wt.addNewTimeClosed("2012-10-04 23:25:00.000");
		//wt.addNewTimeClosed("2012-10-03 23:25:00.000");
	}
}