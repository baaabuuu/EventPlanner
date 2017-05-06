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
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import taskManagement.Task;
import workerLogic.Worker;
import workerLogic.WorkerMissingTask;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class taskPanel extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	private Task selectedTask;
	private List<Worker> tempWorkers;
	private List<Worker> tempAssistingWorkers;
	
	public DefaultListModel<String> listModelTask;
	public DefaultComboBoxModel<String> comboModelAssignedWorkers, comboModelAssistingWorkers, comboModelTaskCompletion;
	public JList<String> taskList;
	public JScrollPane taskScroll, taskDescScroll;
	public JLabel lblTaskDesc, lblTaskSelect, lblTaskName, lblTaskDeadline, lblEndWeek,
	lblAssignedWorkers, lblAssistedTime, lblTotalWorktimeSpent, lblHours, lblTaskCompletion;
	public JTextArea textAreaTaskDesc;
	public JTextField textTaskName, textEndWeek, textWorkTime;
	private JComboBox<String> comboAssignedWorkers, comboAssistedWorkTime, comboTaskCompletion;
	private JButton btnAddWorker, btnDelWorker, btnSaveTask, btnDelTask;
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
		
		textEndWeek = new JTextField();
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
		btnAddWorker.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddWorker.setBounds(170, 145, 57, 20);
		add(btnAddWorker);
		btnAddWorker.setActionCommand("addWorker");
		
		btnDelWorker = new JButton("DEL");
		btnDelWorker.setBounds(233, 145, 57, 20);
		add(btnDelWorker);
		btnDelWorker.setActionCommand("delWorker");
		
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
		btnSaveTask.setBounds(10, 535, 135, 23);
		add(btnSaveTask);
		btnSaveTask.setActionCommand("saveTask");
		
		btnDelTask = new JButton("Delete task");
		btnDelTask.setBounds(155, 535, 135, 23);
		add(btnDelTask);
		btnDelTask.setActionCommand("deleteTask");
		
	}
	/**
	 * Clears task content.
	 * @author s160902
	 */
	public void clearTaskContent(){
		textAreaTaskDesc.setText("");
		textTaskName.setText("");
		textEndWeek.setText("yyyy-MM-dd");
		comboModelAssignedWorkers = new DefaultComboBoxModel<String>();
		comboModelAssistingWorkers = new DefaultComboBoxModel<String>();
		comboModelTaskCompletion = new DefaultComboBoxModel<String>();
		textWorkTime.setText("");
		comboTaskCompletion.removeAllItems();
	}
	/**
	 * Updates comboAssignedWorkers comboBox.
	 * @author s160902
	 */
	public void updateWorkerComboBox(){
		ArrayList<String> workNames = (ArrayList<String>) tempWorkers.stream()
		.map(Worker::getWorkName).collect(Collectors.toList());
		
		comboModelAssignedWorkers = new DefaultComboBoxModel<String>((String[]) workNames.toArray());
	}
	/**
	 * Updates comboAssistedWorkTime comboBox.
	 * @author s160902
	 */
	public void updateAssistingComboBox(){
		ArrayList<String> workNames = (ArrayList<String>) tempAssistingWorkers.stream()
				.map(Worker::getWorkName).collect(Collectors.toList());
		
		comboModelAssistingWorkers = new DefaultComboBoxModel<String>((String[]) workNames.toArray());
	}
	/**
	 * Updates comboTaskCompletion comboBox.
	 * @author s160902
	 */
	public void updateCompletionComboBox(){
		comboModelAssistingWorkers.addElement("Incomplete");
		comboModelAssistingWorkers.addElement("Complete");
		
		if(selectedTask.getStatus())
			comboAssistedWorkTime.setSelectedIndex(1);
	}
	/**
	 * Clears the task list.
	 * @author s160902
	 */
	public void clearTaskList(){
		listModelTask.clear();
	}
	/**
	 * Updates the task list.
	 * @author s160902
	 */
	public void updateTaskList(List<Task> tasks){
		listModelTask.clear();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Task task;
		String status;
		for(int i = 0; i < tasks.size();i++){
			task = tasks.get(i);
			
			status = "Uncompleted";
			if(task.getStatus())
				status = "Completed";
			
			listModelTask.addElement(
					"<html>Task    : "+task.getName()+
					"<br/>Deadline : "+ format.format(task.getDeadline())+
					"<br/>Status   : "+status+"</html>");
		}
		listModelTask.addElement("<html>-----------------------------<br/>ADD NEW TASK<br/>-----------------------------</html>");
	}
	/**
	 * Updates when a value changes in a list.
	 * @author s160902
	 */
	public void valueChanged(ListSelectionEvent e) {
		
		if(e.getSource() == taskList){
			if (!e.getValueIsAdjusting()) {//This line prevents double events
				clearTaskContent();
				selectedTask = null;
				
				if(listModelTask.size()>1 && taskList.getSelectedIndex() < listModelTask.size()-1){
					selectedTask = contentPanel.projectPanel.getSelectedProject().getTask(taskList.getSelectedIndex());
					
					textTaskName.setText(selectedTask.getName());
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					textEndWeek.setText(format.format(selectedTask.getDeadline()));
					try {
						textWorkTime.setText(String.valueOf(1.0*selectedTask.getWorkTime()/2));
					} catch (WorkerMissingTask e1) {
						e1.printStackTrace();
					}
					
					tempWorkers = selectedTask.getAssignedWorkers();
					tempAssistingWorkers = selectedTask.getAssistingWorkers();
					
					updateWorkerComboBox();
					updateAssistingComboBox();
					updateCompletionComboBox();
				}
			}
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	/**
	 * Updates when a button is pressed.
	 * @author s160902
	 */
	public void actionPerformed(ActionEvent e) {
		if ("saveTask".equals(e.getActionCommand())) {
			 if(taskList.getSelectedIndex() < contentPanel.getApp().getAllProjects().size()){
				 
				 selectedTask.setName(textTaskName.getText());
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 try {
					selectedTask.setDeadline(format.parse(textEndWeek.getText()));
				 } catch (ParseException e1) {
					 // TODO Auto-generated catch block
					 e1.printStackTrace();
				 }
				 if(selectedTask.getStatus() == true && comboTaskCompletion.getSelectedIndex() == 0 ||
						 selectedTask.getStatus() == false && comboTaskCompletion.getSelectedIndex() == 1)
					 selectedTask.changeCompletion();
				 
			 }else{
				 Task task = new Task(contentPanel.projectPanel.getSelectedProject());
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 
				 task.setName(textTaskName.getText());
				 task.setDescription(textAreaTaskDesc.getText());
				 task.setAssignedWorkers(tempWorkers);
				 task.setAssistingWorkers(tempAssistingWorkers);
				 try {
					selectedTask.setDeadline(format.parse(textEndWeek.getText()));
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			 }
		 }
		 if ("deleteTask".equals(e.getActionCommand())) {
			 if(taskList.getSelectedIndex() < contentPanel.getApp().getAllProjects().size())
				 contentPanel.getApp().removeProject(taskList.getSelectedIndex());
		 }
		 if ("addWorker".equals(e.getActionCommand())) {
			 if(!tempWorkers.contains(contentPanel.workerPanel.workerList.getSelectedIndex()))
				 tempWorkers.add(contentPanel.getApp().getWorker(contentPanel.workerPanel.workerList.getSelectedIndex()));
			 updateAssistingComboBox();
		 }
		 if ("delWorker".equals(e.getActionCommand())) {
			 tempWorkers.remove(comboAssignedWorkers.getSelectedIndex());
			 updateWorkerComboBox();
		 }
	}
}
