import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class ActivateItemReceivedPanel extends JPanel {
	DataStorageService service;
	public ActivateItemReceivedPanel() {
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
			((DefaultTableModel) tableModel).addRow(new Object[]{ String.valueOf(results.get(i).orderNumber)});
		}
		table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		    	String selectedCellValue = (String) table.getValueAt(table.getSelectedRow() , table.getSelectedColumn());
		    	System.out.println(selectedCellValue);
		        JTable table =(JTable) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		        	int orderNum = Integer.parseInt(selectedCellValue);
		        	List<OrderItem> orderItems = service.getOrderItemByOrderNumber(orderNum);
		        	for(int i = 0; i < orderItems.size(); i++) {
		        		for(int j = 0; j < orderItems.get(i).quantity; j++) {
		        			service.activateOrderReceived(orderItems.get(i).mediaName);
		        		}
		        	}
		        	service.deleteOrderItems(orderNum);
		        	service.deleteOrder(orderNum);
		        	JOptionPane.showMessageDialog(null, "Activated items successfully.");
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
