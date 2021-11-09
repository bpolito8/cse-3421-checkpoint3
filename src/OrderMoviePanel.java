import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderMoviePanel extends JPanel {
	DataStorageService service;

	public OrderMoviePanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
			
		JComboBox combobox = new JComboBox(service.getMovies().toArray());
//		combobox.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent event) {
//				if (combobox.getSelectedItem() == "Order Movie") {
//					System.out.println("Track selected");
//					add(albumName, gbc);
//					add(beatsPerMinute, gbc);
//				} else if (combobox.getSelectedItem() == "Artist") {
//					System.out.println("Artist selected");
//					remove(albumName);
//					remove(beatsPerMinute);
//				}
//				revalidate();
//			}
//		});
		add(new JLabel("Movie to order"));
		add(combobox, gbc);
		
		JTextField quantityField = new JTextField(20);
		JTextField priceField = new JTextField(20);
		add(new JLabel("Quantity"));
		add(quantityField, gbc);
		add(new JLabel("Price"));
		add(priceField, gbc);
		
		JButton button = new JButton("Add");//creating instance of JButton  
		button.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				service.orderMovie(((Movie)combobox.getSelectedItem()).name, Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()), new Date());
			}
		});
		add(button, gbc);

	}

}
