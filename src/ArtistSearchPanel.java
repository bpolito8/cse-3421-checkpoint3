import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class ArtistSearchPanel extends JPanel {
	DataStorageService service;
	public ArtistSearchPanel() {
		service = DataStorageService.getInstance();
		setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		initializeComponents();
		setVisible(true);
		
	}
	
	@SuppressWarnings("unchecked")
	protected void initializeComponents() {
		JTextField input = new JTextField(20);
		input.setBounds(130,100,100, 100);//x axis, y axis, width, height
		add(input);
		
		TableModel tableModel = new DefaultTableModel(0, 1);
		JTable table = new JTable(tableModel);
		input.setBounds(130,300,100, 100);//x axis, y axis, width, height
		
		JComboBox combobox = new JComboBox(new Object[]{"Artist", "Track"});
		combobox.setBounds(130,100,100, 100);//x axis, y axis, width, height
//		combobox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent event) {
//				if (event.getStateChange() == ItemEvent.SELECTED) {
//			          Object item = event.getItem();
//			       }
//			          
//			}
//		});
		add(combobox);
		
		JButton button = new JButton("Search");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(combobox.getSelectedItem() == "Artist") {
					((DefaultTableModel) tableModel).setRowCount(0);
					List<Artist> results = service.searchArtistsByName(input.getText());
					for(int i = 0; i < results.size(); i++) {
						System.out.println(results.get(i).name);
						((DefaultTableModel) tableModel).addRow(new Object[]{results.get(i).name});
					}
				} else { // Track
					((DefaultTableModel) tableModel).setRowCount(0);
					List<Track> results = service.searchTracksByName(input.getText());
					for(int i = 0; i < results.size(); i++) {
						System.out.println(results.get(i).mediaName);
						((DefaultTableModel) tableModel).addRow(new Object[]{results.get(i).mediaName});
					}
				}

			}
		});
		add(button);

		add(table);
	}
}
