package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import application.Worker;
import application.WorkerMissingTask;

public class projectPanel extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	private Worker tempLeader;
	private Project selectedProject;
	
	DefaultListModel<String> listModel;
	JList<String> projectList;
	JScrollPane projectScroll, projectDescScroll;
	JLabel lblProjectDesc, lblProjectSelect, lblProjectName, lblProjectDeadline, lblEndWeek, lblProjectLeader;
	JLabel lblWorkTime, lblHours, lblProjectCompletion;
	JTextArea textAreaProjectDesc;
	JTextField textProjectName, textEndWeek, textProjectLeader, textWorkTime, textProjectCompletion;
	JButton btnAddLeader, btnDelLeader, btnProjectSave, btnDelProject;
	/**
	 * Contains UI related to project.
	 * @author s160902
	 * @param taskPanel 
	 */
	public projectPanel(contentPanel contentPanel) {
		this.contentPanel = contentPanel;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(300, 600));
		setLayout(null);

		lblProjectSelect = new JLabel("Project selection");
		lblProjectSelect.setBounds(10, 10, 120, 20);
		add(lblProjectSelect);
		
		listModel = new DefaultListModel<String>();
		
		projectList = new JList<String>(listModel);
		projectList.setForeground(Color.white);
		projectList.setBackground(Color.darkGray);
		projectList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		projectList.setVisibleRowCount(-1);
		projectList.setLayoutOrientation(JList.VERTICAL);
		projectList.addListSelectionListener(this);
		projectList.addKeyListener(this);
		
		projectScroll = new JScrollPane(projectList);
		projectScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		projectScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		projectScroll.setOpaque(false);
		projectScroll.getViewport().setOpaque(false);
		projectScroll.setBounds(10, 30, 150, 270);
		projectScroll.setBackground(new Color(219, 142, 27));
		add(projectScroll);
		
		lblProjectDesc = new JLabel("Project description");
		lblProjectDesc.setBounds(10, 310, 150, 20);
		add(lblProjectDesc);
		
		textAreaProjectDesc = new JTextArea();
		textAreaProjectDesc.setForeground(Color.white);
		textAreaProjectDesc.setBackground(Color.darkGray);
		textAreaProjectDesc.setLineWrap(true);
		textAreaProjectDesc.setWrapStyleWord(true);
		
		projectDescScroll = new JScrollPane(textAreaProjectDesc);
		projectDescScroll.setBounds(10, 330, 280, 200);
		projectDescScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		projectDescScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		projectDescScroll.setOpaque(false);
		projectDescScroll.getViewport().setOpaque(false);
		add(projectDescScroll);
		
		lblProjectName = new JLabel("Project name");
		lblProjectName.setBounds(170, 10, 120, 20);
		lblProjectName.setLocation(170, 10);
		add(lblProjectName);
		
		textProjectName = new JTextField();
		textProjectName.setColumns(10);
		textProjectName.setBounds(170, 30, 120, 20);
		add(textProjectName);
		
		lblProjectDeadline = new JLabel("Project deadline");
		lblProjectDeadline.setBounds(170, 55, 120, 20);
		add(lblProjectDeadline);
		
		lblEndWeek = new JLabel("Date:");
		lblEndWeek.setBounds(170, 75, 80, 20);
		add(lblEndWeek);
		
		textEndWeek = new JTextField("yyyy-MM-dd");
		textEndWeek.setColumns(10);
		textEndWeek.setBounds(215, 75, 75, 20);
		add(textEndWeek);
		
		lblProjectLeader = new JLabel("Project leader");
		lblProjectLeader.setBounds(170, 100, 120, 20);
		add(lblProjectLeader);
		
		textProjectLeader = new JTextField();
		textProjectLeader.setEditable(false);
		textProjectLeader.setColumns(10);
		textProjectLeader.setBounds(170, 120, 120, 20);
		add(textProjectLeader);
		
		btnAddLeader = new JButton("Add");
		btnAddLeader.addActionListener(this);
		btnAddLeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddLeader.setBounds(170, 145, 57, 20);
		add(btnAddLeader);
		
		btnDelLeader = new JButton("DEL");
		btnDelLeader.addActionListener(this);
		btnDelLeader.setBounds(233, 145, 57, 20);
		add(btnDelLeader);
		
		lblWorkTime = new JLabel("Total worktime spent");
		lblWorkTime.setBounds(170, 170, 120, 20);
		add(lblWorkTime);
		
		textWorkTime = new JTextField();
		textWorkTime.setBounds(215, 190, 75, 20);
		textWorkTime.setEditable(false);
		textWorkTime.setColumns(10);
		add(textWorkTime);
		
		lblHours = new JLabel("Hours:");
		lblHours.setBounds(170, 190, 40, 20);
		add(lblHours);
		
		lblProjectCompletion = new JLabel("Project completion");
		lblProjectCompletion.setBounds(170, 215, 120, 20);
		add(lblProjectCompletion);
		
		textProjectCompletion = new JTextField();
		textProjectCompletion.setBounds(170, 235, 120, 20);
		textProjectCompletion.setEditable(false);
		textProjectCompletion.setColumns(10);
		add(textProjectCompletion);
		
		btnProjectSave = new JButton("Save project");
		btnProjectSave.addActionListener(this);
		btnProjectSave.setBounds(10, 535, 135, 23);
		add(btnProjectSave);
		
		btnDelProject = new JButton("Delete project");
		btnDelProject.addActionListener(this);
		btnDelProject.setBounds(155, 535, 135, 23);
		add(btnDelProject);
		
		updProjectList();
		
	}
	/**
	 * Updates the project list.
	 * @author s160902
	 */
	public void updProjectList(){
		listModel.clear();
		Project project;
		for(int i = 0; i < contentPanel.getApp().getAllProjects().size(); i++){
			project = contentPanel.getApp().getAllProjects().get(i);
			String leader = "No Leader";
			if(project.getLeader() != null)
				leader = project.getLeader().getName();
			
			listModel.addElement(
					"<html>Project : "+project.getName()+
					"<br/>Deadline :"+ contentPanel.getDateFormat().format(project.getDeadline().getTime())+
					"<br/>Leader   : "+leader+"</html>");
		}
		listModel.addElement("<html>-----------------------------<br/>ADD NEW PROJECT<br/>-----------------------------</html>");
	}
	/**
	 * Returns the current selected project.
	 * @author s160902
	 */
	public Project getSelectedProject(){
		return this.selectedProject;
	}
	/**
	 * Clears project textFields and textAreas.
	 * @author s160902
	 */
	public void clearProjectContent(){
		textAreaProjectDesc.setText("");
		textProjectName.setText("");
		textEndWeek.setText("2017-07-10");
		textProjectLeader.setText("");
		textWorkTime.setText("");
		textProjectCompletion.setText("");
	}
	/**
	 * Updates when a value changes in a list.
	 * @author s160902
	 */
	public void valueChanged(ListSelectionEvent e) {
		if(e.getSource() == projectList) {
			if (!e.getValueIsAdjusting()) {//This line prevents double events
				this.contentPanel.getTaskPanel().clearTaskList();
				this.contentPanel.getTaskPanel().clearTaskContent();
				clearProjectContent();
				selectedProject = null;
				
				if(projectList.getSelectedIndex() != -1 && listModel.size()>1 && 
						projectList.getSelectedIndex() < contentPanel.getApp().getAllProjects().size()) {
					
					selectedProject = contentPanel.getApp().getAllProjects().get(projectList.getSelectedIndex());
					
					contentPanel.getTaskPanel().updateTaskList(selectedProject.getTasks());
					
					textProjectName.setText(selectedProject.getName());
					textEndWeek.setText(contentPanel.getDateFormat().format(selectedProject.getDeadline().getTime()));
					if(selectedProject.getLeader() != null)
						textProjectLeader.setText(selectedProject.getLeader().getName());
					
					this.tempLeader = selectedProject.getLeader();
					try {
						textWorkTime.setText(String.valueOf(1.0*selectedProject.getWorkTime()/2));
					} catch (WorkerMissingTask e1) {
						JOptionPane.showMessageDialog(contentPanel, "Error: A worker didn't have the task requested. WorkTime isn't shown");
						e1.printStackTrace();
					}
					textProjectCompletion.setText(selectedProject.getStatus());
				}
			}
		}
	}
	//Unused keyEvents
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	/**
	 * Manages actions by buttons pressed.
	 * @author s160902
	 */
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == btnProjectSave) {
			 if(projectList.getSelectedIndex() != -1 && projectList.getSelectedIndex() < contentPanel.getApp().getAllProjects().size()){
				 Project project = contentPanel.getApp().getAllProjects().get(projectList.getSelectedIndex());
				 project.setName(textProjectName.getText());
				 project.setLeader(tempLeader);
				 try {
					project.setDeadline(contentPanel.getDateFormat().parse(textEndWeek.getText()));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(contentPanel, "Date wasn't saved, type in date correctly.");
				}
			 }else if (projectList.getSelectedIndex() != -1) {
				 Date date = null;
				 try {
						date = contentPanel.getDateFormat().parse(textEndWeek.getText());
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(contentPanel, "Project wasn't saved, type in the date correctly.");
					}
				 if(date != null)
					 contentPanel.getApp().addNewProject(textProjectName.getText(), date, tempLeader);
			 }
			 updProjectList();
		 }
		 //Delete task button is pressed.
		 if (e.getSource() == btnDelProject) {
			 //If within index
			 if(projectList.getSelectedIndex() != -1 && projectList.getSelectedIndex() <
					 contentPanel.getApp().getAllProjects().size())
			 {
				 //Remove project and update list.
				 contentPanel.getApp().removeProject(projectList.getSelectedIndex());
				 updProjectList();
			 }
		 }
		 if (e.getSource() == btnAddLeader) {
			 this.tempLeader = contentPanel.getApp().getWorker(contentPanel.getWorkerPanel().workerList.getSelectedIndex());
			 if(tempLeader != null) {
				 textProjectLeader.setText(tempLeader.getName());
			 }
		 }
		 if (e.getSource() == btnDelLeader) {
			 this.tempLeader = null;
			 textProjectLeader.setText("");
		 }
	}
}