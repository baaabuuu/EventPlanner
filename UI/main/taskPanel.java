package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.Project;
import application.Task;
import application.Worker;
import application.WorkerMissingTask;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class taskPanel extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	private Task selectedTask;
	private List<Worker> tempWorkers = new ArrayList<Worker>();
	private List<Worker> tempAssistingWorkers  = new ArrayList<Worker>();
	
	DefaultListModel<String> listModelTask;
	DefaultComboBoxModel<String> comboModelAssignedWorkers, comboModelAssistingWorkers, comboModelTaskCompletion;
	JList<String> taskList;
	JScrollPane taskScroll, taskDescScroll;
	JLabel lblTaskDesc, lblTaskSelect, lblTaskName, lblTaskDeadline, lblEndWeek,
	lblAssignedWorkers, lblAssistedTime, lblTotalWorktimeSpent, lblHours, lblTaskCompletion;
	JTextArea textAreaTaskDesc;
	JTextField textTaskName, textEndWeek, textWorkTime;
	JComboBox<String> comboAssignedWorkers, comboAssistedWorkTime, comboTaskCompletion;
	JButton btnAddWorker, btnDelWorker, btnSaveTask, btnDelTask;
	/**
	 * Contains UI related to task.
	 * @author s160902
	 */
	public taskPanel(contentPanel contentPanel) {
		this.contentPanel = contentPanel;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(300, 600));
		setLayout(null);

		lblTaskSelect = new JLabel("Task selection");
		lblTaskSelect.setBounds(10, 10, 100, 20);
		add(lblTaskSelect);
		
		listModelTask = new DefaultListModel<String>();
		
		taskList = new JList<String>(listModelTask);
		taskList.setForeground(Color.white);
		taskList.setBackground(Color.darkGray);
		taskList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		taskList.setVisibleRowCount(-1);
		taskList.setLayoutOrientation(JList.VERTICAL);
		taskList.addListSelectionListener(this);
		taskList.addKeyListener(this);
		
		taskScroll = new JScrollPane(taskList);
		taskScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		taskScroll.setOpaque(false);
		taskScroll.getViewport().setOpaque(false);
		taskScroll.setBounds(10, 30, 150, 270);
		taskScroll.setBackground(new Color(219, 142, 27));
		add(taskScroll);
		
		lblTaskDesc = new JLabel("Task description");
		lblTaskDesc.setBounds(10, 310, 100, 20);
		add(lblTaskDesc);
		
		textAreaTaskDesc = new JTextArea();
		textAreaTaskDesc.setForeground(Color.white);
		textAreaTaskDesc.setBackground(Color.darkGray);
		textAreaTaskDesc.setLineWrap(true);
		textAreaTaskDesc.setWrapStyleWord(true);
		
		taskDescScroll = new JScrollPane(textAreaTaskDesc);
		taskDescScroll.setBounds(10, 330, 280, 200);
		taskDescScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskDescScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		taskDescScroll.setOpaque(false);
		taskDescScroll.getViewport().setOpaque(false);
		add(taskDescScroll);
		
		lblTaskName = new JLabel("Task name");
		lblTaskName.setBounds(170, 10, 120, 20);
		add(lblTaskName);
		
		textTaskName = new JTextField();
		textTaskName.setBounds(170, 30, 120, 20);
		add(textTaskName);
		textTaskName.setColumns(10);
		
		lblTaskDeadline = new JLabel("Task deadline");
		lblTaskDeadline.setBounds(170, 55, 120, 20);
		add(lblTaskDeadline);
		
		lblEndWeek = new JLabel("Date:");
		lblEndWeek.setBounds(170, 75, 40, 20);
		add(lblEndWeek);
		
		textEndWeek = new JTextField("yyyy-MM-dd");
		textEndWeek.setColumns(10);
		textEndWeek.setBounds(215, 75, 75, 20);
		add(textEndWeek);
		
		lblAssignedWorkers = new JLabel("Assigned workers");
		lblAssignedWorkers.setBounds(170, 100, 120, 20);
		add(lblAssignedWorkers);
		
		comboModelAssignedWorkers = new DefaultComboBoxModel<String>();
		comboAssignedWorkers = new JComboBox<String>(comboModelAssignedWorkers);
		comboAssignedWorkers.setBounds(170, 120, 120, 20);
		add(comboAssignedWorkers);
		
		btnAddWorker = new JButton("Add");
		btnAddWorker.addActionListener(this);
		btnAddWorker.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddWorker.setBounds(170, 145, 57, 20);
		add(btnAddWorker);
		
		btnDelWorker = new JButton("DEL");
		btnDelWorker.addActionListener(this);
		btnDelWorker.setBounds(233, 145, 57, 20);
		add(btnDelWorker);
		
		comboModelAssistingWorkers = new DefaultComboBoxModel<String>();
		comboAssistedWorkTime = new JComboBox<String>(comboModelAssistingWorkers);
		comboAssistedWorkTime.setBounds(170, 190, 120, 20);
		add(comboAssistedWorkTime);
		
		lblAssistedTime = new JLabel("Assisted work time");
		lblAssistedTime.setBounds(170, 170, 120, 20);
		add(lblAssistedTime);
		
		lblTotalWorktimeSpent = new JLabel("Total worktime spent");
		lblTotalWorktimeSpent.setBounds(170, 215, 120, 20);
		add(lblTotalWorktimeSpent);
		
		lblHours = new JLabel("Hours:");
		lblHours.setBounds(170, 234, 40, 20);
		add(lblHours);
		
		textWorkTime = new JTextField();
		textWorkTime.setEditable(false);
		textWorkTime.setColumns(10);
		textWorkTime.setBounds(215, 234, 75, 20);
		add(textWorkTime);
		
		lblTaskCompletion = new JLabel("Task completion");
		lblTaskCompletion.setBounds(170, 260, 120, 20);
		add(lblTaskCompletion);
		
		comboModelTaskCompletion = new DefaultComboBoxModel<String>();
		comboTaskCompletion = new JComboBox<String>(comboModelTaskCompletion);
		comboTaskCompletion.setBounds(170, 280, 120, 20);
		add(comboTaskCompletion);
		
		btnSaveTask = new JButton("Save task");
		btnSaveTask.addActionListener(this);
		btnSaveTask.setBounds(10, 535, 135, 23);
		add(btnSaveTask);
		
		btnDelTask = new JButton("Delete task");
		btnDelTask.addActionListener(this);
		btnDelTask.setBounds(155, 535, 135, 23);
		add(btnDelTask);
		
	}
	/**
	 * Clears task content.
	 * @author s160902
	 */
	public void clearTaskContent() {
		textAreaTaskDesc.setText("");
		textTaskName.setText("");
		textEndWeek.setText("2017-07-10");
		comboModelAssignedWorkers = new DefaultComboBoxModel<String>();
		comboModelAssistingWorkers = new DefaultComboBoxModel<String>();
		comboModelTaskCompletion = new DefaultComboBoxModel<String>();
		textWorkTime.setText("");
		comboTaskCompletion.removeAllItems();
	}
	/**
	 * Returns currently selected task index.
	 * @author s160902
	 */
	public Task getSelectedTask(){
		return selectedTask;
	}
	/**
	 * Updates comboAssignedWorkers comboBox.
	 * @author s160902
	 */
	public void updateWorkerComboBox() {
		if(tempWorkers != null) {
			String[] workNames = (String[]) tempWorkers.stream()
					.map(Worker::getWorkName).toArray(String[]::new);
					
			comboModelAssignedWorkers = new DefaultComboBoxModel<String>(workNames);
			comboAssignedWorkers.setModel(comboModelAssignedWorkers);
		}else {
			comboModelAssignedWorkers.removeAllElements();
			comboAssignedWorkers.setModel(comboModelAssignedWorkers);
		}
	}
	/**
	 * Updates comboAssistedWorkTime comboBox.
	 * @author s160902
	 */
	public void updateAssistingComboBox() {
		if(tempAssistingWorkers != null) {
			String[] workNames = (String[]) tempAssistingWorkers.stream()
					.map(Worker::getWorkName).toArray(String[]::new);
					
			comboAssistedWorkTime.setModel(new DefaultComboBoxModel<String>(workNames));
		}
	}
	/**
	 * Updates comboTaskCompletion comboBox.
	 * @author s160902
	 */
	public void updateCompletionComboBox() {
		String[] status = {"Incomplete", "Complete"};
		comboTaskCompletion.setModel(new DefaultComboBoxModel<String>(status));
		
		if(taskList.getSelectedIndex() != -1 && listModelTask.size()>1 && taskList.getSelectedIndex() <
				contentPanel.getProjectPanel().getSelectedProject().getTasks().size() && selectedTask.getStatus())
			comboTaskCompletion.setSelectedIndex(1);
	}
	/**
	 * Clears the task list.
	 * @author s160902
	 */
	public void clearTaskList() {
		listModelTask.clear();
	}
	/**
	 * Updates the task list.
	 * @author s160902
	 */
	public void updateTaskList(List<Task> tasks) {
		listModelTask.clear();
		Task task;
		String status;
		for(int i = 0; i < tasks.size();i++){
			task = tasks.get(i);
			
			status = "Uncompleted";
			if(task.getStatus())
				status = "Completed";
			
			listModelTask.addElement(
					"<html>Task    : "+task.getName()+
					"<br/>Deadline : "+ contentPanel.getDateFormat().format(task.getDeadline().getTime())+
					"<br/>Status   : "+status+"</html>");
		}
		listModelTask.addElement("<html>-----------------------------<br/>ADD NEW TASK<br/>-----------------------------</html>");
	}
	/**
	 * Updates when a value changes in a list.
	 * @author s160902
	 */
	public void valueChanged(ListSelectionEvent e) {
		//Value changes in task-list with double selection prevention.
		if(e.getSource() == taskList && !e.getValueIsAdjusting()){
			clearTaskContent();
			selectedTask = null;
			tempWorkers = null;
			tempAssistingWorkers = null;
			
			if(taskList.getSelectedIndex() != -1 && listModelTask.size()>1 &&
					taskList.getSelectedIndex() < contentPanel.getProjectPanel().getSelectedProject().getTasks().size())
			{
				selectedTask = contentPanel.getProjectPanel().getSelectedProject().getTask(taskList.getSelectedIndex());
				
				textTaskName.setText(selectedTask.getName());
				textEndWeek.setText(contentPanel.getDateFormat().format(selectedTask.getDeadline().getTime()));
				
				try {
					textWorkTime.setText(String.valueOf(1.0*selectedTask.getWorkTime()/2));
				} catch (WorkerMissingTask e1) {
					e1.printStackTrace();
				}
				tempWorkers = new ArrayList<Worker>(selectedTask.getAssignedWorkers());
				tempAssistingWorkers = new ArrayList<Worker>(selectedTask.getAssistingWorkers());
			}else {
				tempWorkers = new ArrayList<Worker>();
				tempAssistingWorkers = new ArrayList<Worker>();
			}
			updateWorkerComboBox();
			updateAssistingComboBox();
			updateCompletionComboBox();
		}
	}
	//Unused keyEvents
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	/**
	 * Updates when a button is pressed.
	 * @author s160902
	 */
	public void actionPerformed(ActionEvent e) {
		//Button save task is pressed
		if (e.getSource() == btnSaveTask) {
			//Relevant index is selected.
			 if(taskList.getSelectedIndex() != -1 && taskList.getSelectedIndex() < contentPanel.getProjectPanel().getSelectedProject().getTasks().size()){
				 //Change task name.
				 selectedTask.setName(textTaskName.getText());
				 //Update worker list.
				 selectedTask.setAssignedWorkers(tempWorkers);
				 //Attempt to parse String to Date and catch with a pop-up telling user date wan't saved.
				 try {
					selectedTask.setDeadline(contentPanel.getDateFormat().parse(textEndWeek.getText()));
				 } catch (ParseException e1) {
					 JOptionPane.showMessageDialog(contentPanel, "Date wasn't saved, type in the date correctly.");
				 }
				 // Check if completion status changed.
				 if(selectedTask.getStatus() == true && comboTaskCompletion.getSelectedIndex() == 0 ||
						 selectedTask.getStatus() == false && comboTaskCompletion.getSelectedIndex() == 1)
					 selectedTask.changeCompletion();
				 //Update task list.
				 updateTaskList(contentPanel.getProjectPanel().getSelectedProject().getTasks());
			 }else{
				 Date date = null;
				 //Attempt to parse a Date from String, if it can't, then tell user he has done goof'ed.
				 try {
					date = contentPanel.getDateFormat().parse(textEndWeek.getText());
				 } catch (ParseException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Task wasn't saved, type in the date correctly.");
				 }
				 //If date is valid create task and update list.
				 if(date != null) {
					 new Task(textTaskName.getText(), textAreaTaskDesc.getText(), tempWorkers, tempAssistingWorkers,
							 date, contentPanel.getProjectPanel().getSelectedProject());
					 updateTaskList(contentPanel.getProjectPanel().getSelectedProject().getTasks());
				 }
			 }
		 }
		//If delete task is pressed.
		 if (e.getSource() == btnDelTask) {
			 //Relevant index selected, delete selected task.
			 if(taskList.getSelectedIndex() != -1 && taskList.getSelectedIndex() <
					 contentPanel.getProjectPanel().getSelectedProject().getTasks().size())
				 contentPanel.getProjectPanel().getSelectedProject().removeTask(taskList.getSelectedIndex());
			 updateTaskList(contentPanel.getProjectPanel().getSelectedProject().getTasks());
		 }
		 //Add worker pressed with a worker selected.
		 if (e.getSource() == btnAddWorker && contentPanel.getWorkerPanel().workerList.getSelectedIndex() != -1) {
			 //Check if that worker already exists.
			 if(tempWorkers == null || !tempWorkers.contains(contentPanel.getWorkerPanel().getSelectedWorker())
					 && contentPanel.getWorkerPanel().getSelectedWorker().isAvailableCurrWeek())
			 {
				 //Add worker to temporary list of workers and update combo-box.
				 tempWorkers.add(contentPanel.getWorkerPanel().getSelectedWorker());
				 updateWorkerComboBox();
			 }
		 }
		 //If delete worker is pressed and a worker is selected.
		 if (e.getSource() == btnDelWorker && comboAssignedWorkers.getSelectedIndex() != -1) {
			 //Remove worker and update combo-box.
			 tempWorkers.remove(comboAssignedWorkers.getSelectedIndex());
			 updateWorkerComboBox();
		 }
	}
}
