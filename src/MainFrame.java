import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		JPanel artistSearchPanel = new ArtistSearchPanel();
		JPanel addRecordPanel = new AddRecordPanel();
		
		JButton b=new JButton("Search");//creating instance of JButton  
		b.setBounds(260,100,100, 100);//x axis, y axis, width, height  
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchPanels(artistSearchPanel);
			}
		});
		add(b);
		
		JButton b2=new JButton("Add test");//creating instance of JButton  
		b2.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switchPanels(addRecordPanel);
			}
		});
		add(b2);
		
		contentPane = new JPanel();
		layeredPane = new JLayeredPane();
		JPanel mainMenuPanel = new MainMenuPanel(this);
		
		layeredPane.setBounds(0, 200, 600, 480);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.add(mainMenuPanel);
		//mainMenuPanel.add(layeredPane)
		
		contentPane.setBounds(0, 200, 600, 480);
		contentPane.add(layeredPane);
		
		add(contentPane);
		layeredPane.setVisible(true);
		contentPane.setVisible(true);
		
		
		
	}
}
