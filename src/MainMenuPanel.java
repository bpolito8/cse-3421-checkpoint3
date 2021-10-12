import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainMenuPanel extends JPanel {
	MainFrame root;
	public MainMenuPanel(MainFrame root) {
		this.root = root;
		//super(new BorderLayout());
		setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		initComponents();
		setVisible(true);
	}
	
	protected void initComponents() {
		JPanel artistSearchPanel = new ArtistSearchPanel();
		
		
		JButton b=new JButton("Search");//creating instance of JButton  
		b.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				root.switchPanels(artistSearchPanel);
			}
		});
		add(b);
		
		JButton b2=new JButton("Add record");//creating instance of JButton  
		b2.setBounds(130,100,100, 100);//x axis, y axis, width, height  
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				root.switchPanels(artistSearchPanel);
			}
		});
		add(b2);
		
		           
	}
}
