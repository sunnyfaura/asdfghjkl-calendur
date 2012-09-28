/*
	Screen0 is Calendar screen
*/
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.event.WindowAdapter;

public class Calendar extends JFrame
{
	private Object[] colNames = {"Sun","Mon","Tue","Wed","Thurs","Fri","Sat"};
	private Object[][] days = new Object[6][7];

	private JLabel lblMonth, lblYear;
	private JButton btnPrev, btnNext, pinBoard, addEntry;
	private JTable table;
	private JComboBox cmbYear;
	private Container pane;
	private DefaultTableModel mtblCalendar; //Table model
	private JScrollPane scroll; //The scrollpane
	private JPanel pnlCalendar;
	private int realYear, realMonth, realDay, currYear, currMonth;

	int row, column;
	GregorianCalendar calx = new GregorianCalendar();
	GregorianCalendar cal;
	TableModel model;

	public static int pinboardOpen = 0, calendarOpen = 1;
	public Pinboard pinboard_frame;



	public Calendar()
	{
		realDay = calx.get(GregorianCalendar.DAY_OF_MONTH);
		realMonth = calx.get(GregorianCalendar.MONTH);
		realYear = calx.get(GregorianCalendar.YEAR);
		cal = new GregorianCalendar(realYear, realMonth+1, realDay);

		GregorianCalendar cald = new GregorianCalendar(realYear, realMonth, realDay);

		int nod = cald.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		int som = cal.get(GregorianCalendar.DAY_OF_WEEK);


		System.out.println(realYear + " " + realMonth + " " + realDay);
		currYear = realYear;
		currMonth = realMonth;
		for( int i=1; i<=nod; i++ )
		{
			row = new Integer((i+som-2)/7);
			column = (i+som-2)%7;
			days[row][column] = new Integer(i);
		}

		model = new DefaultTableModel(days, colNames)
		{
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int row, int column)
			{
				return days[row][column] != null;
			}
		};

		table = new JTable(model);
		table.setRowHeight(38);
		table.setColumnSelectionAllowed(true);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		for(int i=0; i<7; i++)
		{
			table.getColumnModel().getColumn(i).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
		}

		table.getTableHeader().setReorderingAllowed(false);

		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);

		scroll = new JScrollPane(table);

		this.setTitle("Calendar");
		this.setSize(325,450);
		pane = this.getContentPane();
		pane.setLayout(null);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //Close when X is clicked
		this.addWindowListener(new java.awt.event.WindowAdapter() {
    		public void windowClosing(WindowEvent winEvt) {
    			calendarOpen++;
       		}
		});
		this.setVisible(true);
		this.setResizable(false);

		//create controls
		lblMonth = new JLabel("January");
		lblYear = new JLabel("Change year: ");
		cmbYear = new JComboBox();
		btnPrev = new JButton("<<");
		btnNext = new JButton(">>");
		pinBoard = new JButton("Go to Pin Board");
		addEntry = new JButton("Add Entry");
		pnlCalendar = new JPanel(null);

		for( int i=realYear-100; i<=realYear+100; i++)
		{
			cmbYear.addItem(String.valueOf(i));
		}

		//register action listeners
		btnPrev.addActionListener( new btnPrev_Action() );
		btnNext.addActionListener( new btnNext_Action() );
		cmbYear.addActionListener( new cmbYear_Action() );
		pinBoard.addActionListener(new pinBoardAction());

		//add controls to pane
		pane.add(pnlCalendar);
		pnlCalendar.add(scroll);
		pnlCalendar.add(lblMonth);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(pinBoard);
		pnlCalendar.add(addEntry);


		//set bounds
		pnlCalendar.setBounds(0,0,500,500);
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100,25);
		lblYear.setBounds(10,305,80,20);
		cmbYear.setBounds(230,305,80,20);
		btnPrev.setBounds(10,25,50,25);
		btnNext.setBounds(260,25,50,25);
		pinBoard.setBounds(10,340,300,25);
		addEntry.setBounds(10,380, 300, 25);
		scroll.setBounds(10,50,300,250);
		refreshCalendar( currMonth, currYear); //refresh calendar
	}

	public void refreshCalendar(int month, int year){
		String[] months = {"January","February","March","April","May","June","July","August","September","October","November","December"};
		int nod, som; //number of days, start of month
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		days = new Object[6][7];
		if( month == 0 && year <= realYear-100)
			btnPrev.setEnabled(false);
		if( month == 11 && year >= realYear+100 )
			btnNext.setEnabled(false);
		lblMonth.setText(months[month]); //refresh month label
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25,180,25); //re-align label with calendar
		cmbYear.setSelectedItem(String.valueOf(year));
		cal = new GregorianCalendar(year, month, 1);
		System.out.println("refreshed: " + year + " " + month + " 1");
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);

		//Clear table
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){
				table.setValueAt(null, i, j);
			}
		}

		for( int i=1; i<=nod; i++ )
		{
			row = new Integer((i+som-2)/7);
			column = (i+som-2)%7;
			days[row][column] = new Integer(i);
			table.setValueAt(i, row, column);
		}

		scroll.validate();
		scroll.repaint();
		pnlCalendar.repaint();
		pane.repaint();
	}

	public void closeCalendar(){
		setVisible(false);
		dispose();
		System.exit(0);
	}

	//Inner classes
	private class ClientsTableRenderer extends DefaultCellEditor
	{
		private JButton button;
		private String label;
		private boolean clicked;
		private int row, col;
		private JTable table;

		public ClientsTableRenderer(JCheckBox checkBox)
		{
			super(checkBox);
			button = new JButton();
			button.setOpaque(true);
			button.addActionListener(new ActionListener()
			{
				public void actionPerformed( ActionEvent e )
				{
					fireEditingStopped();
				}
			});
		}

		public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column)
		{
			this.table = table;
			this.row = row;
			this.col = column;
			button.setForeground(Color.black);
			button.setBackground(UIManager.getColor("Button background"));
			label = (value == null) ? "" : value.toString();
			button.setText(label);
			clicked = true;
			return button;
		}

		public Object getCellEditorValue()
		{
			if(clicked)
			{
				//this is what happens when a cell is clicked
				//JOptionPane.showMessageDialog( button, "Column with Value: "+table.getValueAt(row,col)+" - Clicked!\n Row: "+row+" Column: "+col);
				String temp = table.getValueAt(row,col).toString();
				int date = Integer.parseInt(temp);
				new DayView(col, currMonth, date, currYear);

				/*
					A check might be done to prevent multiple windows of the same date popping up
				*/
			}
			clicked = false;
			return new String(label);
		}

		public boolean stopCellEditing()
		{
			clicked = false;
			return super.stopCellEditing();
		}

		protected void fireEditingStopped()
		{
			super.fireEditingStopped();
		}
	}

	class btnPrev_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( currMonth == 0 )
			{
				currMonth = 11;
				currYear -= 1;
			}
			else
			{
				currMonth -= 1;
			}

			refreshCalendar(currMonth, currYear);
		}
	}

	class btnNext_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( currMonth == 11 )
			{
				currMonth = 0;
				currYear += 1;
			}
			else
			{
				currMonth += 1;
			}

			refreshCalendar(currMonth, currYear);
		}
	}

	class cmbYear_Action implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if( cmbYear.getSelectedItem() != null )
			{
				String b = cmbYear.getSelectedItem().toString();
				currYear = Integer.parseInt(b);
				refreshCalendar(currMonth, currYear);
			}
			else
			{
				currMonth -= 1;
			}

			refreshCalendar(currMonth, currYear);
		}
	}

	private class pinBoardAction implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if(pinboardOpen%2==0){
				pinboard_frame = new Pinboard();
				pinboard_frame.createAndShowGUI();
				pinboardOpen++;
			}
		}
	}
}