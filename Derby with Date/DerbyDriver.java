import java.util.ArrayList;
import java.sql.ResultSet;

/*
 * Java file for testing the derby prototype
 */
public class DerbyDriver {
	public static void main(String[] args) throws Exception {
		Derby d = new Derby();

		d.addEvent("Movie","The Avengers","2012","05","25","03","00",false,"2012","05","25","05","00",0);

		System.out.println("Creating Second Event");

		d.addEvent("Dinner","with friends","2012","05","25","06","00",false,"2012","05","25","08","00",0);
		d.updateDatabase();

		System.out.println("Events Added");

		ResultSet rs = d.dayEventsQuery("2012","05","25");
		while(rs.next()) {
			System.out.println("Name: " + rs.getString(1));
			System.out.println("Desc: " + rs.getString(2));
			System.out.println("Timestamp: " + rs.getTimestamp(3).toString());
			System.out.println("Is All Day: " + rs.getBoolean(4));
			System.out.println("End Timestamp: " + rs.getTimestamp(5).toString());
			System.out.println("Repeating: " + rs.getInt(6));
			System.out.println();
		}
	}
}