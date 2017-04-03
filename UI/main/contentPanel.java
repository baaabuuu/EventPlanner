package main;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JComboBox;

public class contentPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	projectPanel projectPanel;
	taskPanel taskPanel;
	workerPanel workerPanel;
	
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
}
