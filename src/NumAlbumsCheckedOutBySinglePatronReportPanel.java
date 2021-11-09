import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NumAlbumsCheckedOutBySinglePatronReportPanel extends JPanel {
	DataStorageService service;

	public NumAlbumsCheckedOutBySinglePatronReportPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.gridx = 0;
        gbc.gridy = 0;
		add(new JLabel("Library Card Number"), gbc);
		
		JTextField libraryCardNumInput = new JTextField(20);
		libraryCardNumInput.setBounds(130,100,100, 100);//x axis, y axis, width, height
        gbc.gridx = 0;
        gbc.gridy = 1;
		add(libraryCardNumInput, gbc);
        
        DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] {"Count"});
		JTable table = new JTable(tableModel);
		
		JButton button = new JButton("Search");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int count = service.numAlbumsCheckedOutByPatron(Integer.parseInt(libraryCardNumInput.getText()));
				((DefaultTableModel) tableModel).setRowCount(0);
				((DefaultTableModel) tableModel).addRow(new Object[]{count});
			}
		});
		
		gbc.gridx = 2;
        gbc.gridy = 1;
		add(button, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
		add(table, gbc);
	}
}
