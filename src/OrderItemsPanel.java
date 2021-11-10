import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrderItemsPanel extends JPanel {
	DataStorageService service;
	MainFrame root;
	public OrderItemsPanel(MainFrame root) {
		this.root = root;
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
        JButton button = new JButton("Order a Movie");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new OrderMoviePanel());
			}
		});
		add(button, gbc);
		
		JButton button2 = new JButton("Activate Item Received");//creating instance of JButton  
		button2.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new ActivateItemReceivedPanel());
			}
		});
		add(button2, gbc);
	}

}
