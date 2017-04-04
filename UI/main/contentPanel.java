package main;


import java.awt.Dimension;
import java.awt.List;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import companyDatabase.CompanyProjects;
import taskManagement.Project;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class contentPanel extends JPanel implements KeyListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	projectPanel projectPanel;
	taskPanel taskPanel;
	workerPanel workerPanel;
	
	/**
	 * Creates 3 sections each containing its own JPanel.
	 * @author s160902
	 */
	public contentPanel() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(900, 600));
		setLayout(new BorderLayout(0, 0));
		
		projectPanel = new projectPanel();
		add(projectPanel,BorderLayout.LINE_START);
		
		taskPanel = new taskPanel();
		add(taskPanel,BorderLayout.CENTER);
		
		workerPanel = new workerPanel();
		add(workerPanel,BorderLayout.LINE_END);
	}
	/**
	 * Updates the project list.
	 * @author s160902
	 */
	private void updProjectList(){
		for(int i = 0; i<CompanyProjects.getAllProjects().size();i++){
			projectPanel.listModel.addElement(
					"<html>Project: "+CompanyProjects.getAllProjects().get(i).getName()+
					"<br/>Deadline:"+ CompanyProjects.getAllProjects().get(i).getDeadline()+
					"<br/>Leader: "+CompanyProjects.getAllProjects().get(i).getLeader()+"</html>");
		}
		projectPanel.listModel.addElement("<html>/--------------\\<br/>ADD NEW PROJECT<br/>/---------------\\</html>");
	}
	
	
	public void valueChanged(ListSelectionEvent e) {}
	public void keyPressed(KeyEvent e) {}
	/**
	 * Manages actions by key released.
	 * @author s160902
	 */
	public void keyReleased(KeyEvent e) {
		
		if(e.getSource() == projectPanel.projectList && e.getKeyCode() == KeyEvent.VK_ENTER){
			
			Project project = CompanyProjects.getAllProjects().get(projectPanel.projectList.getSelectedIndex());
			
			projectPanel.textProjectName.setText(project.getName());
			projectPanel.textEndWeek.setText(project.getDeadline());
			projectPanel.textProjectLeader.setText(project.getLeader());
			
			//workTime, projectCompletion
			
		}
		
	}
	public void keyTyped(KeyEvent e) {}
	
}
