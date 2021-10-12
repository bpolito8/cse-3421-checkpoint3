import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	JPanel contentPane;
	JLayeredPane layeredPane;
	
	public MainFrame() {
		super("Database App");
		initComponents();
		
        setSize(600, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setVisible(true);//making the frame visible  
	}
	
	public void switchPanels(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		revalidate();
	}
	
	protected void initComponents() {
		contentPane = new JPanel();
		layeredPane = new JLayeredPane();
		JPanel mainMenuPanel = new MainMenuPanel(this);
		
		layeredPane.setBounds(0, 0, 600, 480);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.add(mainMenuPanel);
		//mainMenuPanel.add(layeredPane)
		
		contentPane.setBounds(0, 0, 600, 480);
		contentPane.add(layeredPane);
		
		add(contentPane);
		layeredPane.setVisible(true);
		contentPane.setVisible(true);
		
		
		
	}
}
