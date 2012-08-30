import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class MainScreen
{
	public static boolean[] show = new boolean[2];

	public static void init()
	{
		//show = new boolean[2];
		//show = new boolean[9];
		//System.out.println("Constructor");
		show[0] = true;
		show[1] = false;
		//for(int i = 1; i < 9; i++) show[i] = false;
		
		//screen0 = new Screen0(show[0]);
		//screen1 = new Screen0(show[1]);
		//screen2 = new Screen2(show[2]);
		//screen3 = new Screen3(show[3]);
		//screen4 = new Screen4(show[4]);
		//screen5 = new Screen5(show[5]);
		//screen6 = new Screen6(show[6]);
		//screen7 = new Screen7(show[7]);
		//screen8 = new Screen8(show[8]);
	}
	
	public static void main(String args[])
	{
		init();
		//System.out.println(show[0]);
		//show = new boolean[2];
		//show[0] = true;
		//show[1] = false;
		//System.out.println(show[0]);
		//Screen0 screen0 = new Screen0(show[0]);
		Screen0 screen0 = new Screen0(show[0]);
		//screen1 = new Screen1(show[1]);
		//screen2 = new Screen2(show[2]);
		//screen3 = new Screen3(show[3]);
		//screen4 = new Screen4(show[4]);
		//screen5 = new Screen5(show[5]);
		//screen6 = new Screen6(show[6]);
		//screen7 = new Screen7(show[7]);
		//screen8 = new Screen8(show[8]);
	}
	
	public static void refresh(int scr)
	{
		for(int i = 0; i < 2; i++)
		{
			if (i == scr) show[i] = true;
			else show[i] = false;
		}
	}
}