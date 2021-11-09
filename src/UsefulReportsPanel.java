import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class UsefulReportsPanel extends JPanel {
	DataStorageService service;
	MainFrame root;

	public UsefulReportsPanel(MainFrame root) {
		this.root = root;
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        
		JButton b1 = new JButton("Tracks by artist before year");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new TracksByArtistBeforeYearReportPanel());
			}
		});
		add(b1, gbc);
		
		JButton b2 = new JButton("Number of albums checked out by patron");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new NumAlbumsCheckedOutBySinglePatronReportPanel());
			}
		});
		add(b2, gbc);
		
		JButton b3 = new JButton("Most popular actor");
		add(b3, gbc);
		
		JButton b4 = new JButton("Most listened-to artist");
		add(b4, gbc);
		
		JButton b5 = new JButton("Patron who has checked out the most videos");
		add(b5, gbc);
	}
}
