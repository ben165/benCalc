package benCalc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Gui implements ActionListener{
	
	// Globals
	public static int timerState;
	public static int lastClick;
	public static int sum;
	
	// GUI Stuff
	JFrame frame;
	JButton benAvailable;
	JButton benOutofOffice;
	
	// seconds
	JLabel infoLabel1;
	JTextField workingTime1;
	
	// hour
	JLabel infoLabel2;
	JTextField workingTime2;

	
	// Functions
	public void showTimes(double d) {
		this.workingTime1.setText( Double.toString(Math.round((d/60.0d)*100)/100d) );
		this.workingTime2.setText( Double.toString(Math.round((d/3600.0d)*100)/100d) );
	}	
	
	public void loop() throws Throwable {
		int diff = 0;
		while (true) {
			
			Thread.sleep(1000);
			
			if (timerState == 0 ) {
				// do nothing
			} else {
				diff  = (int) (System.currentTimeMillis() / 1000) - lastClick;
				showTimes(Double.valueOf(sum + diff));
			}			
		}
	}
	
	public void startGui() throws Throwable{
		
		// INIT
		
		sum = 0; // Working time in seconds
		timerState = 0; // Timer stopped
		
		frame = new JFrame("Ben Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 400);
		frame.setLayout(null);
		
		benAvailable = new JButton("Ben ist da!");
		benAvailable.addActionListener(this);
		benAvailable.setFocusable(false);
		benAvailable.setBounds(10, 10, 150, 30);
		frame.add(benAvailable);
		
		benOutofOffice = new JButton("Ben ist nicht da!");
		benOutofOffice.addActionListener(this);
		benOutofOffice.setFocusable(false);
		benOutofOffice.setBounds(10, 50, 150, 30);
		frame.add(benOutofOffice);
		
		infoLabel1 = new JLabel();
		infoLabel1.setText("Zeit in Minuten:");
		infoLabel1.setBounds(10, 120, 250, 30);
		frame.add(infoLabel1);
		
		workingTime1 = new JTextField();
		workingTime1.setBounds(10, 150, 150, 30);
		workingTime1.setEditable(false);
		frame.add(workingTime1);

		infoLabel2 = new JLabel();
		infoLabel2.setText("Zeit in Stunden:");
		infoLabel2.setBounds(10, 200, 250, 30);
		frame.add(infoLabel2);
		
		workingTime2 = new JTextField();
		workingTime2.setBounds(10, 230, 150, 30);
		workingTime2.setEditable(false);
		frame.add(workingTime2);
		
		frame.setVisible(true);
		
		// Init fields and start event loop
		showTimes(0d);
		loop();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		
		if (s.equals("Ben ist da!")) {
			if (timerState == 0) {
				timerState = 1;
				lastClick = (int) (System.currentTimeMillis() / 1000);
			} else {
				// do nothing
			}
			
		}
		else if (s.equals("Ben ist nicht da!")) {
			if (timerState == 0) {
				// do nothing
			} else {
				// stop timer
				timerState = 0;
				sum += (int) (System.currentTimeMillis() / 1000) - lastClick;
			}
		}
	}

	
}
