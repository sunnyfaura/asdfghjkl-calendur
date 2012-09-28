import java.util.Scanner;
import java.util.ArrayList;

import org.garret.perst.MultidimensionalIndex;
import org.garret.perst.Storage;
import org.garret.perst.StorageFactory;

public class Interface {
	
	public static void main(String args[]) {
	    // check if a root object is present in this file	    
		Storage db = StorageFactory.getInstance().createStorage();
		db.open("dates.dbs", 40*1024);
		MultidimensionalIndex<Date> root = (MultidimensionalIndex<Date>) db.getRoot();
		
		if (root == null) {  
            // Root is not yet defined: storage is not initialized
			root = db.<Date>createMultidimensionalIndex(
					Date.class, 
		    		new String[] {"month","day","year"},
					false);
            db.setRoot(root); // register root object
        }
		showList(root);
		
		Scanner in = new Scanner(System.in);
		while(true) {
			System.out.print("What will you do next: ");
			String s = in.nextLine();
			if(s.equals("exit")) break;
			if(s.equals("clear")) {
				clearDatabase(root);
				System.out.println("Date entries cleared!");
				continue;
			}
			if(s.equals("show")) {
				showList(root);
				continue;
			}
			String[] items = s.split(" ");
			root.add(new Date(items[0],items[1],items[2]));
			root.modify();
			System.out.println("Date added!");
		}
		db.close();
	}
	
	public static void clearDatabase(MultidimensionalIndex<Date> root) {
		root.clear();
	}
	
	public static void showList(MultidimensionalIndex<Date> root) {
		ArrayList<Date> list = root.queryByExample(null);
		if(list.size() == 0) {
			System.out.println("There are no date entries in this database");
		} else {
			System.out.println("The following dates are stored:");
			for(int i = 0; i < list.size(); ++i) {
				System.out.println(list.get(i).getMonth() + " " + list.get(i).getDay() + " " + list.get(i).getYear());
			}
		}
	}
}
