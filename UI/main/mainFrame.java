package main;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;

public class mainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	static contentPanel contentPanel;
	
	/**
	 * Starts the program.
	 * @author s160902
	 */
	public static void main(String[] args) {
		mainFrame frame = new mainFrame();
		frame.setVisible(true);
	}
	/**
	 * Creates the frame in which the UI is shown.
	 * @author s160902
	 */
	public mainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 625);
		
		contentPanel = new contentPanel();
		
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        setContentPane(scrollPane);
	}
}
