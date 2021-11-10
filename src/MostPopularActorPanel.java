import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;


import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MostPopularActorPanel extends JPanel {
	DataStorageService service;

	public MostPopularActorPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		List<Artist> actors = service.getMostPopularActor();
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        //gbc.gridwidth = GridBagConstraints.REMAINDER;
		
        gbc.gridx = 0;
        gbc.gridy = 0;

		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] {"Actor Name"});
		JTable table = new JTable(tableModel);
		((DefaultTableModel) tableModel).setRowCount(0);
		for(int i = 0; i < actors.size(); i++) {
			System.out.println(actors.get(i));
			((DefaultTableModel) tableModel).addRow(new Object[]{actors.get(i).name});
		}
	
		


        
        gbc.fill = GridBagConstraints.HORIZONTAL;
		add(table, gbc);
		
	}

}
