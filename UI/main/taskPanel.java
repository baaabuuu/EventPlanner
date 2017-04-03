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

public class taskPanel extends JPanel implements KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModelTask, listModelTaskedWorker;
	public JList<String> taskList, taskedWorkerList;
	public JScrollPane taskScroll, taskedWorkerScroll, taskDescScroll;
	public JLabel lblTaskDesc, lblTaskSelect, lblTaskName, lblTaskedWorkers;
	public JTextArea textAreaTaskDesc;
	public JTextField textField;
	
	public taskPanel() {
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
		
		listModelTask.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModelTask.addElement("<html>Project: soft_2 <br/>deadline: 14/08 <br/>By: workIsLife</html>");
		
		taskScroll = new JScrollPane(taskList);
		taskScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		taskScroll.setOpaque(false);
		taskScroll.getViewport().setOpaque(false);
		taskScroll.setBounds(10, 230, 150, 125);
		taskScroll.setBackground(new Color(219, 142, 27));
		add(taskScroll);
		
		lblTaskedWorkers = new JLabel("Tasked workers");
		lblTaskedWorkers.setBounds(10, 210, 100, 20);
		add(lblTaskedWorkers);
		
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
		taskedWorkerScroll.setBounds(10, 30, 150, 175);
		taskedWorkerScroll.setBackground(new Color(219, 142, 27));
		add(taskedWorkerScroll);
		
		lblTaskDesc = new JLabel("Task description");
		lblTaskDesc.setBounds(10, 360, 100, 20);
		add(lblTaskDesc);
		
		textAreaTaskDesc = new JTextArea();
		textAreaTaskDesc.setForeground(Color.white);
		textAreaTaskDesc.setBackground(Color.darkGray);
		textAreaTaskDesc.setLineWrap(true);
		textAreaTaskDesc.setWrapStyleWord(true);
		
		taskDescScroll = new JScrollPane(textAreaTaskDesc);
		taskDescScroll.setBounds(10, 379, 280, 170);
		taskDescScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		taskDescScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		taskDescScroll.setOpaque(false);
		taskDescScroll.getViewport().setOpaque(false);
		add(taskDescScroll);
		
		lblTaskName = new JLabel("Task name");
		lblTaskName.setBounds(170, 10, 120, 20);
		add(lblTaskName);
		
		
		
		
	}
	
	public void valueChanged(ListSelectionEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
