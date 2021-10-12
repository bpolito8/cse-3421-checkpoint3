import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UsefulReportsPanel extends JPanel {
	DataStorageService service;

	public UsefulReportsPanel() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		JButton b1 = new JButton("Tracks by artist before year");
		add(b1, gbc);
		
		JButton b2 = new JButton("Number of albums checked out by patron");
		add(b2, gbc);
		
		JButton b3 = new JButton("Most popular actor");
		add(b3, gbc);
		
		JButton b4 = new JButton("Most listened-to artist");
		add(b4, gbc);
		
		JButton b5 = new JButton("Patron who has checked out the most videos");
		add(b5, gbc);
	}
}
