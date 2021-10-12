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

		           
	}
}
