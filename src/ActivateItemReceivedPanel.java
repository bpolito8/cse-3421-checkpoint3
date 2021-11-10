import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ActivateItemReceivedPanel extends JPanel {
	DataStorageService service;
	MainFrame root;
	public ActivateItemReceivedPanel(MainFrame root) {
		this.root = root;
		service = DataStorageService.getInstance();
		initializeComponents();
		setVisible(true);
	}
	
	protected void initializeComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.setColumnIdentifiers(new Object[] {"Orders"});

		JTable table = new JTable(tableModel);
		TableColumnModel colModel=table.getColumnModel();
		TableColumn col=colModel.getColumn(0);
		col.setPreferredWidth(500);
		((DefaultTableModel) tableModel).setRowCount(0);
		List<Order> results = service.getAllOrders();
		for(int i = 0; i < results.size(); i++) {			
			((DefaultTableModel) tableModel).addRow(new Object[]{ String.valueOf(results.get(i).orderNumber) + " - " + results.get(i).arrivalDate.toString()});
		}
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		    	String selectedCellValue = (String) table.getValueAt(table.getSelectedRow() , table.getSelectedColumn());
		    	System.out.println(selectedCellValue);
		        JTable table =(JTable) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	// execute the queries based on selectedCellValue
		        }
		    }
		});
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
		add(table, gbc);
		
	}
}
