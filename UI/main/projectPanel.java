package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
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
import companyDatabase.CompanyWorkers;
import taskManagement.Project;
import workerLogic.Worker;
import workerLogic.WorkerMissingTask;

public class projectPanel extends JPanel implements ActionListener, KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	private contentPanel contentPanel;
	private Worker tempLeader;
	
	public DefaultListModel<String> listModel;
	public JList<String> projectList;
	public JScrollPane projectScroll, projectDescScroll;
	public JLabel lblProjectDesc, lblProjectSelect, lblProjectName, lblProjectDeadline, lblEndWeek, lblProjectLeader;
	public JLabel lblWorkTime, lblHours, lblProjectCompletion;
	public JTextArea textAreaProjectDesc;
	public JTextField textProjectName, textEndWeek, textProjectLeader, textWorkTime, textProjectCompletion;
	public JButton btnAddLeader, btnDelLeader, btnProjectSave, btnDelProject;
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
		
		lblEndWeek = new JLabel("End of week:");
		lblEndWeek.setBounds(170, 75, 80, 20);
		add(lblEndWeek);
		
		textEndWeek = new JTextField();
		textEndWeek.setColumns(10);
		textEndWeek.setBounds(250, 75, 40, 20);
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
		btnAddLeader.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddLeader.setBounds(170, 145, 57, 20);
		add(btnAddLeader);
		btnAddLeader.setActionCommand("addLeader");
		
		btnDelLeader = new JButton("DEL");
		btnDelLeader.setBounds(233, 145, 57, 20);
		add(btnDelLeader);
		btnDelLeader.setActionCommand("deleteLeader");
		
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
		btnProjectSave.setBounds(10, 535, 135, 23);
		add(btnProjectSave);
		btnProjectSave.setActionCommand("saveProject");
		
		btnDelProject = new JButton("Delete project");
		btnDelProject.setBounds(155, 535, 135, 23);
		add(btnDelProject);
		btnDelProject.setActionCommand("deleteProject");
		
		updProjectList();
		
	}
	/**
	 * Updates the project list.
	 * @author s160902
	 */
	public void updProjectList(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Project project;
		for(int i = 0; i < CompanyProjects.getAllProjects().size(); i++){
			project = CompanyProjects.getAllProjects().get(i);
			listModel.addElement(
					"<html>Project : "+project.getName()+
					"<br/>Deadline :"+ format.format(project.getDeadline())+
					"<br/>Leader   : "+project.getLeader().getName()+"</html>");
		}
		listModel.addElement("<html>-----------------------------<br/>ADD NEW PROJECT<br/>-----------------------------</html>");
	}
	/**
	 * Clears project textFields and textAreas.
	 * @author s160902
	 */
	public void clearProjectContent(){
		textAreaProjectDesc.setText("");
		textProjectName.setText("");
		textEndWeek.setText("");
		textProjectLeader.setText("");
		textWorkTime.setText("");
		textProjectCompletion.setText("");
	}
	/**
	 * Updates when enter is pressed while focused on projectList.
	 * @author s160902
	 */
	public void valueChanged(ListSelectionEvent e) {
		/**
		 * Updates when a value changes in projectList.
		 * @author s160902
		 */
		if(e.getSource() == projectList){
			if (!e.getValueIsAdjusting()) {//This line prevents double events
				System.out.println("list says hello!");
				
				this.contentPanel.getTaskPanel().clearTaskList();
				this.contentPanel.getTaskPanel().clearTaskContent();
				clearProjectContent();
				
				if(listModel.size()>1 && projectList.getSelectedIndex() < listModel.size()){
					Project project = CompanyProjects.getAllProjects().get(projectList.getSelectedIndex());
					
					textProjectName.setText(project.getName());
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					textEndWeek.setText(format.format(project.getDeadline()));
					textProjectLeader.setText(project.getLeader().getName());
					this.tempLeader = project.getLeader();
					try {
						textWorkTime.setText(String.valueOf(1.0*project.getWorkTime()/2));
					} catch (WorkerMissingTask e1) {
						e1.printStackTrace();
					}
					textProjectCompletion.setText(project.getStatus());
				}
			}
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	/**
	 * Manages actions by buttons pressed.
	 * @author s160902
	 */
	public void actionPerformed(ActionEvent e) {
		 if ("saveProject".equals(e.getActionCommand())) {
			 
			 if(projectList.getSelectedIndex() < CompanyProjects.getAllProjects().size()){
				 Project project = CompanyProjects.getAllProjects().get(projectList.getSelectedIndex());
				 project.setName(textProjectName.getText());
				 project.setLeader(tempLeader);
				 //Deadline
			 }else{
				 CompanyProjects.addNewProject(textProjectName.getText());
				 Project project = CompanyProjects.getAllProjects().get(CompanyProjects.getAllProjects().size()-1);
				 project.setLeader(tempLeader);
				//Deadline
			 }
		 }
		 if ("deleteProject".equals(e.getActionCommand())) {
			 if(projectList.getSelectedIndex() < CompanyProjects.getAllProjects().size())
				 CompanyProjects.removeProject(projectList.getSelectedIndex());
		 }
		 if ("addLeader".equals(e.getActionCommand())) {
			 this.tempLeader = CompanyWorkers.getWorker(contentPanel.getWorkerPanel().workerList.getSelectedIndex());
			 textProjectLeader.setText(tempLeader.getName());
		 }
		 if ("deleteLeader".equals(e.getActionCommand())) {
			 this.tempLeader = null;
			 textProjectLeader.setText("");
		 }
	}
	
	
	
	
	
	
	
	
}
