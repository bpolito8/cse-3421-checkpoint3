import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class AddRecordPanel extends JPanel {
	DataStorageService service;

	public AddRecordPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JTextField input = new JTextField(20);
		JTextField albumName = new JTextField(20);
		JTextField beatsPerMinute = new JTextField(20);
		JLabel bpmLabel = new JLabel("Beats per minute");
		JLabel albumLabel = new JLabel("Album Name");
		JComboBox combobox = new JComboBox(new Object[]{"Artist", "Track"});
		combobox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if (combobox.getSelectedItem() == "Track") {
					System.out.println("Track selected");
			        gbc.gridx = 1;
			        gbc.gridy = 2;
					add(albumLabel, gbc);
			        gbc.gridx = 1;
			        gbc.gridy = 3;
					add(albumName, gbc);
			        gbc.gridx = 1;
			        gbc.gridy = 4;
					add(bpmLabel, gbc);
			        gbc.gridx = 1;
			        gbc.gridy = 5;
					add(beatsPerMinute, gbc);
				} else if (combobox.getSelectedItem() == "Artist") {
					System.out.println("Artist selected");
					remove(bpmLabel);
					remove(albumLabel);
					remove(albumName);
					remove(beatsPerMinute);
				}
				revalidate();
			}
		});
        gbc.gridx = 0;
        gbc.gridy = 0;
		add(combobox, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
		add(new JLabel("Name"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
		add(input, gbc);
        		
		JButton button = new JButton("Add");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					if (combobox.getSelectedItem() == "Artist") {
						service.addNewArtist(input.getText());
					} else if (combobox.getSelectedItem() == "Track") {
						service.addNewTrack(input.getText(), albumName.getText(), Integer.parseInt(beatsPerMinute.getText()));
					}
				
			}
		});
        gbc.gridx = 2;
        gbc.gridy = 0;
		add(button, gbc);
	}
}
