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
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class contentPanel extends JPanel implements ListSelectionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	DefaultListModel<String> listModel;
	JList<String> itemList;
	JScrollPane listScroll;
	private JLabel lblProjectDescription;

	public contentPanel() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setPreferredSize(new Dimension(1000, 600));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Project selection");
		lblNewLabel.setBounds(10, 11, 100, 14);
		add(lblNewLabel);
		
		listModel = new DefaultListModel<String>();
		
		itemList = new JList<String>(listModel);
		itemList.setForeground(Color.white);
		itemList.setBackground(Color.darkGray);
		itemList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		itemList.setVisibleRowCount(-1);
		itemList.setLayoutOrientation(JList.VERTICAL);
		itemList.addListSelectionListener(this);
		itemList.addKeyListener(this);
		
		
		listModel.addElement("<html>Project: soft_1 <br/>deadline: 24/03 <br/>By: luvHTML<3</html>");
		listModel.addElement("<html>Project: soft_2 <br/>deadline: 14/08 <br/>By: workIsLife</html>");
		
		listScroll = new JScrollPane(itemList);
		listScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		listScroll.setOpaque(false);
		listScroll.getViewport().setOpaque(false);
		listScroll.setBounds(10, 40, 170, 180);
		listScroll.setBackground(new Color(219, 142, 27));
		add(listScroll);
		
		lblProjectDescription = new JLabel("Project description");
		lblProjectDescription.setBounds(10, 230, 100, 14);
		add(lblProjectDescription);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
