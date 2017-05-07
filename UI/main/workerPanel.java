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
	JTextField textTime, textAssTime, textSetBusy, textCurrentWeek, textAddWorker;
	JButton btnRemAssTime, btnAddAssTime, btnRemTime, btnAddTime, btnAdvWeek, btnAdvDay, btnBusy, btnAddWorker,
	btnFire;
	JLabel lblAddAssTime, lblTime, lblCurrentWork, lblWorkerSelection, lblFireWorker, lblTimeTravel, lblSetBusy,
	lblCurrentWeek, lblAddWorker;
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
		lblAddAssTime.setBounds(170, 125, 120, 20);
		add(lblAddAssTime);
		
		textAssTime = new JTextField();
		textAssTime.setBounds(172, 145, 116, 20);
		add(textAssTime);
		
		btnAddAssTime = new JButton("Add");
		btnAddAssTime.addActionListener(this);
		btnAddAssTime.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnAddAssTime.setBounds(170, 170, 57, 20);
		add(btnAddAssTime);
		
		btnRemAssTime = new JButton("DEL");
		btnRemAssTime.addActionListener(this);
		btnRemAssTime.setBounds(233, 170, 57, 20);
		add(btnRemAssTime);
		
		lblCurrentWeek = new JLabel("Current week is  :");
		lblCurrentWeek.setBounds(10, 310, 105, 20);
		add(lblCurrentWeek);
		
		lblSetBusy = new JLabel("Set busy week    :");
		lblSetBusy.setBounds(10, 335, 105, 20);
		add(lblSetBusy);
		
		textSetBusy = new JTextField();
		textSetBusy.setBounds(115, 335, 40, 20);
		add(textSetBusy);
		
		textCurrentWeek = new JTextField();
		textCurrentWeek.setEditable(false);
		textCurrentWeek.setBounds(115, 310, 40, 20);
		add(textCurrentWeek);
		
		btnBusy = new JButton("Busy");
		btnBusy.addActionListener(this);
		btnBusy.setBounds(90, 360, 65, 20);
		add(btnBusy);
		
		lblAddWorker = new JLabel("Hire Worker");
		lblAddWorker.setBounds(170, 195, 120, 20);
		add(lblAddWorker);
		
		textAddWorker = new JTextField();
		textAddWorker.setBounds(170, 215, 116, 20);
		add(textAddWorker);
		
		btnAddWorker = new JButton("You're Hired!");
		btnAddWorker.addActionListener(this);
		btnAddWorker.setBackground(new Color(0, 255, 0));
		btnAddWorker.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnAddWorker.setBounds(170, 240, 120, 20);
		add(btnAddWorker);
		
		btnFire = new JButton("FIRE HIM!");
		btnFire.setForeground(Color.white);
		btnFire.addActionListener(this);
		btnFire.setBackground(new Color(255, 0, 0));
		btnFire.setBounds(170, 285, 120, 23);
		add(btnFire);
		
		lblFireWorker = new JLabel("Fire Worker");
		lblFireWorker.setBounds(170, 265, 120, 20);
		add(lblFireWorker);
		
		lblTimeTravel = new JLabel("Travel forward in time");
		lblTimeTravel.setBounds(10, 390, 130, 20);
		add(lblTimeTravel);
		
		btnAdvWeek = new JButton("Week");
		btnAdvWeek.addActionListener(this);
		btnAdvWeek.setBounds(10, 410, 80, 20);
		add(btnAdvWeek);
		
		btnAdvDay = new JButton("Day");
		btnAdvDay.addActionListener(this);
		btnAdvDay.setBounds(10, 435, 80, 20);
		add(btnAdvDay);
		
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
		if(e.getSource() == btnAdvWeek) {
			//contentPanel.getApp().
		}
		if(e.getSource() == btnAdvDay) {
			
		}
	}
}
