import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	JPanel contentPane;
	JLayeredPane layeredPane;
	
	public MainFrame() {
		super("Database App");
		initComponents();
		
        setSize(600, 480);
        //setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);//making the frame visible  
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		revalidate();
	}
	
	protected void initComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		JPanel artistSearchPanel = new ArtistSearchPanel();
		JPanel addRecordPanel = new AddRecordPanel();
		
		
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		setContentPane(contentPane);
//		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		JPanel mainMenuPanel = new MainMenuPanel(this);
		mainMenuPanel.setBounds(0, 200, 600, 280);
		
		layeredPane.setBounds(0, 200, 600, 280);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.add(new JPanel());
		//mainMenuPanel.add(layeredPane)
		
		contentPane.setBounds(0, 200, 600, 280);
		contentPane.add(layeredPane);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(mainMenuPanel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(contentPane, gbc);
		layeredPane.setVisible(true);
		contentPane.setVisible(true);
		
		
		
	}
}
