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

public class TracksByArtistBeforeYearReportPanel extends JPanel {
	DataStorageService service;

	public TracksByArtistBeforeYearReportPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.gridx = 0;
        gbc.gridy = 0;
		add(new JLabel("Artist Name"), gbc);
		
		JTextField artistInput = new JTextField(20);
		artistInput.setBounds(130,100,100, 100);//x axis, y axis, width, height
        gbc.gridx = 0;
        gbc.gridy = 1;
		add(artistInput, gbc);
		
		gbc.gridx = 1;
        gbc.gridy = 0;
		add(new JLabel("Year"), gbc);
		
		JTextField yearInput = new JTextField(20);
		artistInput.setBounds(130,100,100, 100);//x axis, y axis, width, height
        gbc.gridx = 1;
        gbc.gridy = 1;
		add(yearInput, gbc);
        
        DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] {"Track Name"});
		JTable table = new JTable(tableModel);
		
		JButton button = new JButton("Search");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((DefaultTableModel) tableModel).setRowCount(0);
				List<Track> results = service.getTracksByArtistBeforeYear(artistInput.getText(), yearInput.getText());
				for(int i = 0; i < results.size(); i++) {
					System.out.println(results.get(i).mediaName);
					((DefaultTableModel) tableModel).addRow(new Object[]{results.get(i).mediaName});
				}
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
