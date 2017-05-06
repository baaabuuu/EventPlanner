package main;


import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import application.ProjectPlanningApp;

import java.awt.BorderLayout;


public class contentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	projectPanel projectPanel;
	taskPanel taskPanel;
	workerPanel workerPanel;
	ProjectPlanningApp app;
	/**
	 * Creates 3 sections each containing its own JPanel.
	 * @author s160902
	 */
	public contentPanel(ProjectPlanningApp app) {
		this.app = app;
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(900, 600));
		setLayout(new BorderLayout(0, 0));
		
		projectPanel = new projectPanel(this);
		add(projectPanel,BorderLayout.LINE_START);
		
		taskPanel = new taskPanel(this);
		add(taskPanel,BorderLayout.CENTER);
		
		workerPanel = new workerPanel(this);
		add(workerPanel,BorderLayout.LINE_END);
	}
	public projectPanel getProjectPanel(){
		return this.projectPanel;
	}
	public taskPanel getTaskPanel(){
		return this.taskPanel;
	}
	public workerPanel getWorkerPanel(){
		return this.workerPanel;
	}
	public ProjectPlanningApp getApp(){
		return this.app;
	}
}
