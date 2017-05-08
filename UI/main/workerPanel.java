package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import application.InvalidDateRange;
import application.InvalidTime;
import application.Task;
import application.Worker;
import application.WorkerMissingTask;
import application.WorkerNameError;

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
	JComboBox<String> comboWeekWork;
	JTextField textTime, textAssTime, textSetBusy, textCurrentWeek, textAddWorker;
	JButton btnAddAssTime, btnRemTime, btnAddTime, btnAdvWeek, btnAdvDay, btnBusy, btnHireWorker,
	btnFireWorker;
	JLabel lblAddAssTime, lblTime, lblCurrentWork, lblWorkerSelection, lblFireWorker, lblTimeTravel, lblSetBusy,
	lblCurrentWeek, lblAddWorker;
	private JLabel lblFromWeek;
	private JLabel lblToWeek;
	private JTextField textFromWeek;
	private JTextField textToWeek;
	private JButton btnPrint;
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
		
		comboWeekWork = new JComboBox<String>();
		comboWeekWork.setBounds(170, 30, 120, 20);
		add(comboWeekWork);
		
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
		
		lblCurrentWeek = new JLabel("Current week is  : ");
		lblCurrentWeek.setBounds(10, 310, 105, 20);
		add(lblCurrentWeek);
		
		lblSetBusy = new JLabel("Set busy week    :");
		lblSetBusy.setBounds(10, 335, 105, 20);
		add(lblSetBusy);
		
		textSetBusy = new JTextField();
		textSetBusy.setBounds(115, 335, 40, 20);
		add(textSetBusy);
		
		textCurrentWeek = new JTextField(""+contentPanel.getApp().getSettings().getWeekNumber());
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
		
		btnHireWorker = new JButton("You're Hired!");
		btnHireWorker.addActionListener(this);
		btnHireWorker.setBackground(new Color(0, 255, 0));
		btnHireWorker.setFont(new Font("Dialog", Font.PLAIN, 11));
		btnHireWorker.setBounds(170, 240, 120, 20);
		add(btnHireWorker);
		
		btnFireWorker = new JButton("FIRE HIM!");
		btnFireWorker.setForeground(Color.white);
		btnFireWorker.addActionListener(this);
		btnFireWorker.setBackground(new Color(255, 0, 0));
		btnFireWorker.setBounds(170, 285, 120, 23);
		add(btnFireWorker);
		
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
		
		lblFromWeek = new JLabel("From Week  : ");
		lblFromWeek.setBounds(10, 460, 105, 20);
		add(lblFromWeek);
		
		lblToWeek = new JLabel("To Week      : ");
		lblToWeek.setBounds(10, 485, 105, 20);
		add(lblToWeek);
		
		textFromWeek = new JTextField();
		textFromWeek.setBounds(90, 460, 40, 20);
		add(textFromWeek);
		
		textToWeek = new JTextField();
		textToWeek.setBounds(90, 485, 40, 20);
		add(textToWeek);
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(this);
		btnPrint.setBounds(10, 510, 80, 20);
		add(btnPrint);
		
		updateWorkerList();
	}
	/**
	 * Returns currently selected worker.
	 * @author s160902
	 */
	public Worker getSelectedWorker(){
		return selectedWorker;
	}
	/**
	 * Updates worker combo-box.
	 * @author S164147 & s160902
	 */
	public void updateWorkerList() {
		listModel.clear();
		//For each worker, add it
		for(Worker worker : contentPanel.getApp().getAllWorkers()){
			listModel.addElement(worker.getWorkName()+"#" + worker.getWorkID());
		}
		workerList.setSelectedIndex(0);
	}
	/**
	 * Updates week work combo-box.
	 * @author S164147 & s160902
	 */
	public void updateWeekWorkCombo(){
		comboWeekWork.setModel(new DefaultComboBoxModel<String>());
		//Get all current tasks
		Task[] currentWeekTasks = selectedWorker.getCurrWeek().getAssignments();
		List<Task> tempTasks = new ArrayList<Task>();
		//add them to a list.
		if(currentWeekTasks[0] != null){
			int counter = 0;
			while(currentWeekTasks[counter] != null)
			{
				tempTasks.add(currentWeekTasks[counter]);
				counter++;
			}
			//Go through list and add all names to to string array.
			String[] taskNames = (String[]) tempTasks.stream().map(Task::getName).toArray(String[]::new);
			//Update combo-box and set selected index.
			comboWeekWork.setModel(new DefaultComboBoxModel<String>(taskNames));
			comboWeekWork.setSelectedIndex(0);
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		//When worker list gets updated
		if(!e.getValueIsAdjusting() && workerList.getSelectedIndex() != -1) { //Prevents double selection
			//Update selectedWorker
			selectedWorker = contentPanel.getApp().getWorker(workerList.getSelectedIndex());
			updateWeekWorkCombo();
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
				//Attempt to parse to integer.
				selectedWorker.getCurrWeek().updWorkTime(comboWeekWork.getSelectedIndex(), Integer.parseInt(textTime.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(contentPanel, "Attempted to add something else than integers to time");
			} catch (WorkerMissingTask e1) {
				JOptionPane.showMessageDialog(contentPanel, "Attempted to add time to a task, that the worker isn't assigned to");
			} catch (InvalidTime e1) {
				JOptionPane.showMessageDialog(contentPanel, "Attempted to add more hours than there is in a day, time wasn't added");
			}
		}
		//If remove time button is pressed.
		if(e.getSource() == btnRemTime) {
			try {
				//Attempt to remove time from task of selected worker.
				contentPanel.getApp().getAllWorkers().get(workerList.getSelectedIndex()).getCurrWeek().updWorkTime(comboWeekWork.getSelectedIndex(), -(Integer.parseInt(textTime.getText())));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(contentPanel, "Attempted to remove something else than integers from time");
			} catch (WorkerMissingTask e1) {
				JOptionPane.showMessageDialog(contentPanel, "Attempted to remove time from a task, that the worker isn't assigned to");
			} catch (InvalidTime e1) {
				JOptionPane.showMessageDialog(contentPanel, "This message should never be ale to appear when removing time from task");
			}
		}
		//If add assisted time button is pressed.
		if(e.getSource() == btnAddAssTime) {
			if(contentPanel.getTaskPanel().taskList.getSelectedIndex() != -1) {
				//Get selected task from task panel.
				Task task = contentPanel.getTaskPanel().getSelectedTask();
				//Add assisting worker
				task.addAssistingWorker(contentPanel.getWorkerPanel().getSelectedWorker());
				
				int time = 0;
				try {
					//Attempt to parse to integer.
					time = Integer.parseInt(textAssTime.getText());
					//If parsed time is not negative
					if(time > 0) {
						//Add assisted time to task.
						contentPanel.getWorkerPanel().getSelectedWorker().getCurrWeek().uppHelpedTasks(time, task);
					}
				  } catch (NumberFormatException e1) {
					  JOptionPane.showMessageDialog(contentPanel, "Attempted to add something else than integers to time");
				  } catch (InvalidTime e1) {
					  JOptionPane.showMessageDialog(contentPanel, "Attempted to add more hours than there is in a day, time wasn't added");
				}
			}
		}
		//If advance week is pressed
		if(e.getSource() == btnAdvWeek) {
			//Advance a day 7 times.
			for(int t = 0; t < 7; t++){
				try {
					contentPanel.getApp().advanceDay();
				} catch (WorkerMissingTask e1) {
					//Will never happen.
					e1.printStackTrace();
				}
			}
			//Update current week.
			textCurrentWeek.setText(""+contentPanel.getApp().getSettings().getWeekNumber());
			//Update combo-boxes.
			contentPanel.getTaskPanel().updateWorkerComboBox();
			contentPanel.getTaskPanel().updateAssistingComboBox();
		}
		//If advance day is pressed.
		if(e.getSource() == btnAdvDay) {
			try {
				//Advance one day
				contentPanel.getApp().advanceDay();
			} catch (WorkerMissingTask e1) {
				//Will never happen.
				e1.printStackTrace();
			}
			//Update current week.
			textCurrentWeek.setText(""+contentPanel.getApp().getSettings().getWeekNumber());
			//Update combo-boxes.
			contentPanel.getTaskPanel().updateWorkerComboBox();
			contentPanel.getTaskPanel().updateAssistingComboBox();
		}
		//If hire worker button is pressed and there's something in the text-field.
		if(e.getSource() == btnHireWorker && !textAddWorker.getText().equals("")) {
			try {
				//Add new worker. WorkerNameError should not occur, as empty field is tested for.
				contentPanel.getApp().addNewWorker(textAddWorker.getText());
			} catch (WorkerNameError e1) {
				JOptionPane.showMessageDialog(contentPanel, "Attempted to add un-named worker");
			}
			//Update worker list.
			updateWorkerList();
		}
		//If fire worker button is pressed and a worker is selected
		if(e.getSource() == btnFireWorker && workerList.getSelectedIndex() != -1) {
			//Remove worker and update worker list.
			contentPanel.getApp().removeWorker(workerList.getSelectedIndex());
			updateWorkerList();
		}
		//If busy button is pressed.
		if(e.getSource() == btnBusy) {
			try {
				//Attempt to parse input to integer and set that week as busy.
				contentPanel.getApp().getWorker(workerList.getSelectedIndex()).getXweek(
						Integer.parseInt(textSetBusy.getText())).setBussy();
			} catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(contentPanel, "Type in valid integer");
			}
		}
		if(e.getSource() == btnPrint) {
			if(textFromWeek.getText() != "" && textToWeek.getText() != ""){
				try {
					contentPanel.getApp().printWorkWeeks(selectedWorker, Integer.parseInt(textFromWeek.getText()), Integer.parseInt(textToWeek.getText()));
				} catch (NumberFormatException | WorkerMissingTask | IOException | InvalidDateRange e1) {
					JOptionPane.showMessageDialog(contentPanel, "Error: Type valid weeks.");
					e1.printStackTrace();
				}
			}
		}
	}
}