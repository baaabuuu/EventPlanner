package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTextField;

import taskManagement.Task;
import workerLogic.Worker;
import workerLogic.WorkerMissingTask;
import companyDatabase.CompanyWorkers;
import javax.swing.JTextPane;

public class workerPanel extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	
	
	
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	Task[] currentWeekTasks;
	public DefaultListModel<String> listModel;
	public JList<String> workerList;
	public JScrollPane workerScroll;
	public DefaultComboBoxModel<String> taskList;
	public JComboBox comboBox;
	private JTextField textField;
	/**
	 * Contains UI related to worker.
	 * @author s160902
	 */
	public workerPanel(contentPanel contentPanel) {
		this.contentPanel = contentPanel;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(300, 600));
		setLayout(null);
		
		listModel = new DefaultListModel<String>();
		
		workerList = new JList<String>(listModel);
		workerList.setForeground(Color.white);
		workerList.setBackground(Color.darkGray);
		workerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		workerList.setVisibleRowCount(-1);
		workerList.setLayoutOrientation(JList.VERTICAL);
		workerList.addListSelectionListener(this);
		workerList.addKeyListener(this);
		
		listModel.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModel.addElement("<html>Project: soft_2 <br/>deadline: 14/08 <br/>By: workIsLife</html>");
		
		workerScroll = new JScrollPane(workerList);
		workerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		workerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		workerScroll.setOpaque(false);
		workerScroll.getViewport().setOpaque(false);
		workerScroll.setBounds(10, 30, 150, 270);
		workerScroll.setBackground(new Color(219, 142, 27));
		add(workerScroll);
		
		JLabel lblWorkerSelection = new JLabel("Worker Selection");
		lblWorkerSelection.setBounds(10, 10, 100, 20);
		add(lblWorkerSelection);
		
		JLabel lblCurrentWork = new JLabel("Current weeks work");
		lblCurrentWork.setBounds(170, 10, 120, 20);
		add(lblCurrentWork);
		
		comboBox = new JComboBox();
		comboBox.setBounds(170, 30, 120, 20);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Add time to task");
		lblNewLabel.setBounds(170, 55, 120, 20);
		add(lblNewLabel);
		
		JButton button = new JButton("Add");
		button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button.setBounds(170, 100, 57, 20);
		add(button);
		
		JButton button_1 = new JButton("DEL");
		button_1.setBounds(233, 100, 57, 20);
		add(button_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(170, 75, 120, 20);
		add(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Add Assisted");
		lblNewLabel_1.setBounds(170, 132, 120, 20);
		add(lblNewLabel_1);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(172, 152, 116, 20);
		add(textPane);
		
		JButton button_2 = new JButton("Add");
		button_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		button_2.setBounds(170, 184, 57, 20);
		add(button_2);
		
		JButton button_3 = new JButton("DEL");
		button_3.setBounds(233, 184, 57, 20);
		add(button_3);

	}
	//S164147
	public void updateWorkerList()
	{
		for(Worker worker : CompanyWorkers.getAllWorkers()){
			listModel.addElement(worker.getName());
		}
			
	}
	public void valueChanged(ListSelectionEvent e) {

		
		if(!e.getValueIsAdjusting())
		{
			Task[] currentWeekTasks = CompanyWorkers.getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().getAssignments();
			String[] taskNames = {"Select Worker"};
		

			for(Task task : currentWeekTasks)
			{
				int i = 0;
				taskNames[i] = task.getName();
				i++;
			}
		
			taskList = new DefaultComboBoxModel<String>(taskNames);
		}

		
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void actionPerformed(ActionEvent e) {
		//Check which button has been pressed
		if (e.equals("Add"))
		{
			try {
				CompanyWorkers.getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().updWorkTime(comboBox.getSelectedIndex(), Integer.parseInt(textField.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.println("Please insert integer.");
			} catch (WorkerMissingTask e1) {
				e1.printStackTrace();
			}
			
		}
		if(e.equals("Del"))
		{
			try {
				CompanyWorkers.getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().updWorkTime(comboBox.getSelectedIndex(), -(Integer.parseInt(textField.getText())));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.println("Please insert integer.");
			} catch (WorkerMissingTask e1) {
				e1.printStackTrace();
			}
			
		}

	}
}
