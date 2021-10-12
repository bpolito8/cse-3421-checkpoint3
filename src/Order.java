import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
	Date arrivalDate;
	List<OrderItem> items;
	public Order(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
		items = new ArrayList<OrderItem>();
	}
	
	public void addOrderItem(OrderItem orderItem) {
		items.add(orderItem);
	}
	
	public double getTotalPrice() {
		double sum = 0.0;
		for(int i = 0; i < items.size(); i++) {
			sum += items.get(i).getTotalPrice();
		}
		return sum;
	}
}
