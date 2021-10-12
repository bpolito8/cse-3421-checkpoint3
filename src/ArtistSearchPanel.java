import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ArtistSearchPanel extends JPanel {
	DataStorageService service;
	public ArtistSearchPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
		
	}
	
	@SuppressWarnings("unchecked")
	protected void initializeComponents() {
		JTextField input = new JTextField(20);
		add(input);
		
		Object[][] rowData = {{"test"},{"test2"}};
		Object[] columnNames = {"Name"};
		
		TableModel tableModel = new DefaultTableModel(1, 1);
		JTable table = new JTable(tableModel);
		
		JComboBox combobox = new JComboBox(new Object[]{"Artist", "Track"});
		combobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
			          Object item = event.getItem();
			       }
			          
			}
		});
		add(combobox);
		
		JButton button = new JButton("Search");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
				
				List<Track> results = service.searchTracksByArtist(input.getText());
				Object[][] rowData = new Object[results.size()][1];
				for(int i = 0; i < results.size(); i++) {
					rowData[i][1] = results.get(i).mediaName;
					tableModel.addRow(new Object[]{results.get(i).mediaName});
				}
				
			}
		});
		add(button);
		
		add(table);
	}
}
