import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MostListenedArtistPanel extends JPanel {
	DataStorageService service;

	public MostListenedArtistPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		List<Artist> artist = service.getMostPopularArtist();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] {"Actor Name"});
		JTable table = new JTable(tableModel);
		((DefaultTableModel) tableModel).setRowCount(0);
		for(int i = 0; i < artist.size(); i++) {
			((DefaultTableModel) tableModel).addRow(new Object[]{artist.get(i).name});
		}
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
		add(table, gbc);
		
	}

}
