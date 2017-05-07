package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

import application.Task;
import application.Worker;
import application.WorkerMissingTask;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

import javax.swing.JTextField;

public class workerPanel extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	private Worker selectedWorker;
	
	Task[] currentWeekTasks;
	DefaultListModel<String> listModel;
	JList<String> workerList;
	JScrollPane workerScroll;
	DefaultComboBoxModel<String> taskList;
	JComboBox<String> comboBox;
	JTextField textTime, textAssTime;
	JButton btnRemAssTime, btnAddAssTime, btnRemTime, btnAddTime;
	JLabel lblAddAssTime, lblTime, lblCurrentWork, lblWorkerSelection;
	/**
	 * Contains UI related to worker.
	 * @author s160902
	 */
	workerPanel(contentPanel contentPanel) {
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
		
		workerScroll = new JScrollPane(workerList);
		workerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		workerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		workerScroll.setOpaque(false);
		workerScroll.getViewport().setOpaque(false);
		workerScroll.setBounds(10, 30, 150, 270);
		workerScroll.setBackground(new Color(219, 142, 27));
		add(workerScroll);
		
		lblWorkerSelection = new JLabel("Worker Selection");
		lblWorkerSelection.setBounds(10, 10, 100, 20);
		add(lblWorkerSelection);
		
		lblCurrentWork = new JLabel("Current weeks work");
		lblCurrentWork.setBounds(170, 10, 120, 20);
		add(lblCurrentWork);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(170, 30, 120, 20);
		add(comboBox);
		
		lblTime = new JLabel("Add time to task");
		lblTime.setBounds(170, 55, 120, 20);
		add(lblTime);
		
		btnAddTime = new JButton("Add");
		btnAddTime.addActionListener(this);
		btnAddTime.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddTime.setBounds(170, 100, 57, 20);
		add(btnAddTime);
		
		btnRemTime = new JButton("DEL");
		btnRemTime.addActionListener(this);
		btnRemTime.setBounds(233, 100, 57, 20);
		add(btnRemTime);
		
		textTime = new JTextField();
		textTime.setColumns(10);
		textTime.setBounds(170, 75, 120, 20);
		add(textTime);
		
		lblAddAssTime = new JLabel("Add assisted time");
		lblAddAssTime.setBounds(170, 132, 120, 20);
		add(lblAddAssTime);
		
		textAssTime = new JTextField();
		textAssTime.setBounds(172, 152, 116, 20);
		add(textAssTime);
		
		btnAddAssTime = new JButton("Add");
		btnAddAssTime.addActionListener(this);
		btnAddAssTime.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnAddAssTime.setBounds(170, 184, 57, 20);
		add(btnAddAssTime);
		
		btnRemAssTime = new JButton("DEL");
		btnRemAssTime.addActionListener(this);
		btnRemAssTime.setBounds(233, 184, 57, 20);
		add(btnRemAssTime);
		
		updateWorkerList();
	}
	public Worker getSelectedWorker(){
		return selectedWorker;
	}
	//S164147
	public void updateWorkerList() {
		for(Worker worker : contentPanel.getApp().getAllWorkers()){
			listModel.addElement(worker.getName());
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		//When worker list gets updated
		if(!e.getValueIsAdjusting()) { //Prevents double selection
			selectedWorker = contentPanel.getApp().getWorker(workerList.getSelectedIndex());
			
			Task[] currentWeekTasks = contentPanel.getApp().getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().getAssignments();
			String[] taskNames = {"Select Worker"};
			if(currentWeekTasks[0] != null){
				for(Task task : currentWeekTasks) {
					int i = 0;
					taskNames[i] = task.getName();
					i++;
				}
				taskList = new DefaultComboBoxModel<String>(taskNames);
			}
		}
	}
	//Unused keyEvents
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void actionPerformed(ActionEvent e) {
		//Check which button has been pressed
		if (e.getSource() == btnAddTime) {
			try {
				contentPanel.getApp().getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().updWorkTime(comboBox.getSelectedIndex(), Integer.parseInt(textTime.getText()));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.println("Please insert integer.");
			} catch (WorkerMissingTask e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnRemTime) {
			try {
				contentPanel.getApp().getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().updWorkTime(comboBox.getSelectedIndex(), -(Integer.parseInt(textTime.getText())));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
				System.out.println("Please insert integer.");
			} catch (WorkerMissingTask e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource() == btnAddAssTime) {
			if(contentPanel.getTaskPanel().taskList.getSelectedIndex() != -1) {
				Task task = contentPanel.getTaskPanel().getSelectedTask();
				task.addAssistingWorker(contentPanel.getApp().getWorker(workerList.getSelectedIndex()));
				
				int time = Integer.parseInt(textAssTime.getText())*2;
				contentPanel.getApp().getWorker(workerList.getSelectedIndex()).getCurrWeek().uppHelpedTasks(time, task);
			}
		}
		if(e.getSource() == btnRemAssTime) {
			
		}
	}
}
