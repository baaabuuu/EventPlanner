package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import taskManagement.Project;

public class projectPanel extends JPanel implements KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
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
	 */
	public projectPanel() {
		
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
		
		listModel.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModel.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModel.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModel.addElement("<html>------------------------<br/>Add new project<br/>------------------------</html>");
		
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
		
		btnDelLeader = new JButton("DEL");
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
		btnProjectSave.setBounds(10, 535, 135, 23);
		add(btnProjectSave);
		
		btnDelProject = new JButton("Delete project");
		btnDelProject.setBounds(155, 535, 135, 23);
		add(btnDelProject);
		
	}
	/**
	 * Updates the project list.
	 * @author s160902
	 */
	private void updProjectList(){
		for(int i = 0; i < CompanyProjects.getAllProjects().size(); i++){
			listModel.addElement(
					"<html>Project: "+CompanyProjects.getAllProjects().get(i).getName()+
					"<br/>Deadline:"+ CompanyProjects.getAllProjects().get(i).getDeadline()+
					"<br/>Leader: "+CompanyProjects.getAllProjects().get(i).getLeader().getName()+"</html>");
		}
		listModel.addElement("<html>/--------------\\<br/>ADD NEW PROJECT<br/>/---------------\\</html>");
	}
	
	public void valueChanged(ListSelectionEvent e) {}
	public void keyPressed(KeyEvent e) {}
	/**
	 * Manages actions by key released.
	 * @author s160902
	 */
	public void keyReleased(KeyEvent e) {
		if(e.getSource() == projectList && e.getKeyCode() == KeyEvent.VK_ENTER){
			System.out.println("projectList says HI!");
			Project project = CompanyProjects.getAllProjects().get(projectList.getSelectedIndex());
			
			textProjectName.setText(project.getName());
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			textEndWeek.setText(format1.format(project.getDeadline()));
			textProjectLeader.setText(project.getLeader().getName());
			int total = 0;
			for(int i = 0; i < project.getallTasks().size(); i++){
				for(int j = 0; j < project.getTaskbyIndex(i).getAllAssignedWorkers().size(); j++){
					
				}
			}
			
			//textWorkTime.setText(project.get);
			
			//workTime, projectCompletion
			
		}
	}
	public void keyTyped(KeyEvent e) {}

}
