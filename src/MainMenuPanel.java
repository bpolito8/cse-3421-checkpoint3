import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
		//setLayout((LayoutManager) new BoxLayout(this, BoxLayout.PAGE_AXIS));
		initComponents();
		setVisible(true);
	}
	
	protected void initComponents() {
		
		
		JButton b=new JButton("Search");//creating instance of JButton  
		b.setBounds(0,0,100, 50);//x axis, y axis, width, height  
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new ArtistSearchPanel());
			}
		});
		add(b);
		
		JButton b2=new JButton("Add Record");//creating instance of JButton  
		b2.setBounds(100,0,100, 50);//x axis, y axis, width, height  
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new AddRecordPanel());
			}
		});
		add(b2);
		
		JButton b3=new JButton("Order Items");//creating instance of JButton  
		b3.setBounds(200,0,100, 50);//x axis, y axis, width, height  
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new OrderItemsPanel(root));
			}
		});
		add(b3);
		
		JButton b6=new JButton("Edit Record");//creating instance of JButton  
		b6.setBounds(100,0,100, 50);//x axis, y axis, width, height  
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new EditRecordPanel(root));
			}
		});
		add(b6);
		
		JButton b5=new JButton("Useful Reports");//creating instance of JButton  
		b5.setBounds(400,0,100, 50);//x axis, y axis, width, height  
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.switchPanels(new UsefulReportsPanel(root));
			}
		});
		add(b5);
	}
}
