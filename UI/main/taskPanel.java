package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.List;

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

import companyDatabase.CompanyProjects;
import taskManagement.Task;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class taskPanel extends JPanel implements KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	public DefaultListModel<String> listModelTask, listModelTaskedWorker;
	public JList<String> taskedWorkerList;
	public JScrollPane taskedWorkerScroll, taskDescScroll;
	public JLabel lblTaskDesc, lblTaskSelect, lblTaskName, lblTaskDeadline, lblEndWeek,
	lblAssignedWorkers, lblAssistedTime, lblTotalWorktimeSpent, lblWorkHours, lblTaskCompletion;
	public JTextArea textAreaTaskDesc;
	public JTextField textField, textTaskName, textEndWeek, textField_1;
	private JComboBox<String> comboAssignedWorkers, comboBox, comboBox_1;
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
		
		listModelTask.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModelTask.addElement("<html>Project: soft_2 <br/>deadline: 14/08 <br/>By: workIsLife</html>");
		
		listModelTaskedWorker = new DefaultListModel<String>();
		
		taskedWorkerList = new JList<String>(listModelTaskedWorker);
		taskedWorkerList.setForeground(Color.white);
		taskedWorkerList.setBackground(Color.darkGray);
		taskedWorkerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		taskedWorkerList.setVisibleRowCount(-1);
		taskedWorkerList.setLayoutOrientation(JList.VERTICAL);
		taskedWorkerList.addListSelectionListener(this);
		taskedWorkerList.addKeyListener(this);
		
		taskedWorkerScroll = new JScrollPane(taskedWorkerList);
		taskedWorkerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskedWorkerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		taskedWorkerScroll.setOpaque(false);
		taskedWorkerScroll.getViewport().setOpaque(false);
		taskedWorkerScroll.setBounds(10, 30, 150, 270);
		taskedWorkerScroll.setBackground(new Color(219, 142, 27));
		add(taskedWorkerScroll);
		
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
		
		lblEndWeek = new JLabel("End of week:");
		lblEndWeek.setBounds(170, 75, 80, 20);
		add(lblEndWeek);
		
		textEndWeek = new JTextField();
		textEndWeek.setColumns(10);
		textEndWeek.setBounds(250, 75, 40, 20);
		add(textEndWeek);
		
		lblAssignedWorkers = new JLabel("Assigned workers");
		lblAssignedWorkers.setBounds(170, 100, 120, 20);
		add(lblAssignedWorkers);
		
		comboAssignedWorkers = new JComboBox<String>();
		comboAssignedWorkers.setBounds(170, 120, 120, 20);
		add(comboAssignedWorkers);
		
		btnAddWorker = new JButton("Add");
		btnAddWorker.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddWorker.setBounds(170, 145, 57, 20);
		add(btnAddWorker);
		
		btnDelWorker = new JButton("DEL");
		btnDelWorker.setBounds(233, 145, 57, 20);
		add(btnDelWorker);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(170, 190, 120, 20);
		add(comboBox);
		
		lblAssistedTime = new JLabel("Assisted work time");
		lblAssistedTime.setBounds(170, 170, 120, 20);
		add(lblAssistedTime);
		
		lblTotalWorktimeSpent = new JLabel("Total worktime spent");
		lblTotalWorktimeSpent.setBounds(170, 215, 120, 20);
		add(lblTotalWorktimeSpent);
		
		lblWorkHours = new JLabel("Hours:");
		lblWorkHours.setBounds(170, 234, 40, 20);
		add(lblWorkHours);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(215, 234, 75, 20);
		add(textField_1);
		
		lblTaskCompletion = new JLabel("Task completion");
		lblTaskCompletion.setBounds(170, 260, 120, 20);
		add(lblTaskCompletion);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(170, 280, 120, 20);
		add(comboBox_1);
		
		btnSaveTask = new JButton("Save task");
		btnSaveTask.setBounds(10, 535, 135, 23);
		add(btnSaveTask);
		
		btnDelTask = new JButton("Delete task");
		btnDelTask.setBounds(155, 535, 135, 23);
		add(btnDelTask);
		
	}
	public void clearTaskContent(){
		
	}
	public void clearTaskList(){
		
	}
	public void updateTaskList(List<Task> tasks){
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
	}
	
	public void valueChanged(ListSelectionEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
