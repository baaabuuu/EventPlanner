package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;

public class taskPanel extends JPanel implements KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModelTask, listModelTaskedWorker;
	public JList<String> taskedWorkerList;
	public JScrollPane taskedWorkerScroll, taskDescScroll;
	public JLabel lblTaskDesc, lblTaskSelect, lblTaskName;
	public JTextArea textAreaTaskDesc;
	public JTextField textField;
	private JTextField textTaskName;
	private JTextField textEndWeek;
	private JTextField textField_1;
	/**
	 * Contains UI related to task.
	 * @author s160902
	 */
	public taskPanel() {
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
		
		JLabel lblTaskDeadline = new JLabel("Task deadline");
		lblTaskDeadline.setBounds(170, 55, 120, 20);
		add(lblTaskDeadline);
		
		JLabel lblEndWeek = new JLabel("End of week:");
		lblEndWeek.setBounds(170, 75, 80, 20);
		add(lblEndWeek);
		
		textEndWeek = new JTextField();
		textEndWeek.setColumns(10);
		textEndWeek.setBounds(250, 75, 40, 20);
		add(textEndWeek);
		
		JLabel lblAssignedWorkers = new JLabel("Assigned workers");
		lblAssignedWorkers.setBounds(170, 100, 120, 20);
		add(lblAssignedWorkers);
		
		JComboBox comboAssignedWorkers = new JComboBox();
		comboAssignedWorkers.setBounds(170, 120, 120, 20);
		add(comboAssignedWorkers);
		
		JButton btnAddWorker = new JButton("Add");
		btnAddWorker.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddWorker.setBounds(170, 145, 57, 20);
		add(btnAddWorker);
		
		JButton btnDelWorker = new JButton("DEL");
		btnDelWorker.setBounds(233, 145, 57, 20);
		add(btnDelWorker);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 190, 120, 20);
		add(comboBox);
		
		JLabel lblAssistedTime = new JLabel("Assisted work time");
		lblAssistedTime.setBounds(170, 170, 120, 20);
		add(lblAssistedTime);
		
		JLabel lblTotalWorktimeSpent = new JLabel("Total worktime spent");
		lblTotalWorktimeSpent.setBounds(170, 215, 120, 20);
		add(lblTotalWorktimeSpent);
		
		JLabel label = new JLabel("Hours:");
		label.setBounds(170, 234, 40, 20);
		add(label);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(215, 234, 75, 20);
		add(textField_1);
		
		JLabel lblTaskCompletion = new JLabel("Task completion");
		lblTaskCompletion.setBounds(170, 260, 120, 20);
		add(lblTaskCompletion);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(170, 280, 120, 20);
		add(comboBox_1);
		
		JButton btnSaveTask = new JButton("Save task");
		btnSaveTask.setBounds(10, 535, 135, 23);
		add(btnSaveTask);
		
		JButton btnDelTask = new JButton("Delete task");
		btnDelTask.setBounds(155, 535, 135, 23);
		add(btnDelTask);
		
	}
	
	public void valueChanged(ListSelectionEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
