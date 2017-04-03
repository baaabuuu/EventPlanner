package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;

public class workerPanel extends JPanel implements KeyListener, ListSelectionListener {
	
	private static final long serialVersionUID = 1L;
	public DefaultListModel<String> listModel;
	public JList<String> workerList;
	public JScrollPane workerScroll;
	
	public workerPanel() {
		
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
		
		listModel.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModel.addElement("<html>Project: soft_2 <br/>deadline: 14/08 <br/>By: workIsLife</html>");
		
		workerScroll = new JScrollPane(workerList);
		workerScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		workerScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		workerScroll.setOpaque(false);
		workerScroll.getViewport().setOpaque(false);
		workerScroll.setBounds(10, 30, 150, 270);
		workerScroll.setBackground(new Color(219, 142, 27));
		add(workerScroll);
		
		JLabel lblWorkerSelection = new JLabel("Worker Selection");
		lblWorkerSelection.setBounds(10, 10, 100, 20);
		add(lblWorkerSelection);
		
		JLabel lblCurrentWork = new JLabel("Current weeks work");
		lblCurrentWork.setBounds(170, 10, 120, 20);
		add(lblCurrentWork);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 30, 120, 20);
		add(comboBox);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(170, 93, 120, 20);
		add(lblNewLabel);

	}
	
	public void valueChanged(ListSelectionEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}
