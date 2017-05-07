package main;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import application.ProjectPlanningApp;
import application.WorkerNameError;

public class mainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	contentPanel contentPanel;
	/**
	 * Starts the program.
	 * @author s160902
	 */
	public static void main(String[] args) {
		ProjectPlanningApp app = new ProjectPlanningApp();
		
		//Test
		try {
			app.addNewWorker("Hubert");
			app.addNewWorker("Bob");
			app.addNewWorker("Jessica");
			app.addNewWorker("Tylor");
		} catch (WorkerNameError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainFrame frame = new mainFrame(app);
		frame.setVisible(true);
	}
	/**
	 * Creates the frame in which the UI is shown.
	 * @author s160902
	 */
	public mainFrame(ProjectPlanningApp app) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 625);
		
		contentPanel = new contentPanel(app);
		
		JScrollPane scrollPane = new JScrollPane(contentPanel);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(50, 30, 300, 50);
        setContentPane(scrollPane);
	}
}
