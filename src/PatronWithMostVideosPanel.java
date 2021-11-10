import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PatronWithMostVideosPanel extends JPanel {
	DataStorageService service;

	public PatronWithMostVideosPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		List<LibraryCardHolder> patrons = service.getPatronWhoCheckedOutMostVideos();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
		
        gbc.gridx = 0;
        gbc.gridy = 0;

		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] {"Actor Name"});
		JTable table = new JTable(tableModel);
		for(int i = 0; i < patrons.size(); i++) {
			((DefaultTableModel) tableModel).addRow(new Object[]{patrons.get(i).firstName + " " + patrons.get(i).lastName });
		}
	
        gbc.fill = GridBagConstraints.HORIZONTAL;
		add(table, gbc);
		
	}

}
