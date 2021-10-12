import javax.swing.JPanel;

public class PanelTemplate extends JPanel {
	DataStorageService service;

	public PanelTemplate() {
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		
	}
}
