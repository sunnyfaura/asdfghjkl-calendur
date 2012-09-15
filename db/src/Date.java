import org.garret.perst.Persistent;

public class Date extends Persistent {
	private String month, day, year;
	
	public Date(String month, String day, String year) {
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getDay() {
		return day;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public void setYear(String year) {
		this.year = year;
	}
}
