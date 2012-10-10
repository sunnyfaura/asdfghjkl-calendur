/** Simple Program to write a text file
*/

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Timestamp;

public class WriteText{

	public static void addNewTimeClosed(String timeClosed)
	{
		try
		{
			FileWriter outFile = new FileWriter("timeClosed.txt");
			PrintWriter out = new PrintWriter(outFile);
			out.println(timeClosed);
			out.close();
			//System.out.println("Inserted "+timeClosed+" to text file");
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
			str = br.readLine();
			
			if(!(str != null) )
			{
				java.util.Date today = new java.util.Date();
				java.sql.Timestamp ts = new java.sql.Timestamp(today.getTime());
				addNewTimeClosed(ts.toString());
				fr.close();
				return ts;
			}

			else
			{
				Timestamp ts = null;
				while( str != null )
				{
					ts = Timestamp.valueOf(str);
					str = null;
				}
				//System.out.println("Retrieved "+ts+" from text file");
				fr.close();
				return ts;
			}
				
		}
		catch(Exception e)
		{
			System.err.println("Error: "+e.getMessage());
			System.out.println("yo error");
		}
		return null;
	}

	/*public static void main(String[] args)
	{
		WriteText wt = new WriteText();
		System.out.println(wt.getLastTimeClosed());
		System.out.println(wt.getLastTimeClosed());
		wt.addNewTimeClosed("2012-10-06 23:25:00.045");
		System.out.println(wt.getLastTimeClosed());
		wt.addNewTimeClosed("2012-10-07 23:25:00.034");
		System.out.println(wt.getLastTimeClosed());

		//wt.addNewTimeClosed("2012-10-05 23:25:00.000");
		//wt.addNewTimeClosed("2012-10-04 23:25:00.000");
		//wt.addNewTimeClosed("2012-10-03 23:25:00.000");
	}*/
}