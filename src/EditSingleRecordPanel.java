import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class EditSingleRecordPanel extends JPanel {
	DataStorageService service;
	String originalArtistName = "";

	public EditSingleRecordPanel(String inputName) {
		this.originalArtistName = inputName;
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel artistLabel = new JLabel("Edit Artist Name");
        gbc.gridx = 0;
        gbc.gridy = 0;
		add(artistLabel, gbc);
		
		JTextField input = new JTextField(20);
		input.setText(this.originalArtistName);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(input, gbc);
        
        JButton button = new JButton("Save");//creating instance of JButton  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.updateArtist(originalArtistName, input.getText());
				
//				JLabel updatedTableLabel = new JLabel("Updated Artist table");
//		        gbc.gridx = 0;
//		        gbc.gridy = 1;
//				add(updatedTableLabel, gbc);
//				DefaultTableModel tableModel = new DefaultTableModel(0, 1);
//				tableModel.setColumnIdentifiers(new Object[] {"Artist Name"});
//				JTable table = new JTable(tableModel);
//				((DefaultTableModel) tableModel).setRowCount(0);
//				List<Artist> results = service.getArtists();
//				for(int i = 0; i < results.size(); i++) {
//					System.out.println(results.get(i));
//					((DefaultTableModel) tableModel).addRow(new Object[]{results.get(i).name});
//				}
//		        gbc.gridx = 0;
//		        gbc.gridy = 2;
//		        gbc.gridwidth = 3;
//		        gbc.fill = GridBagConstraints.HORIZONTAL;
//				add(table, gbc);

			}
		});
        gbc.gridx = 2;
        gbc.gridy = 0;
		add(button, gbc);

	}
}
