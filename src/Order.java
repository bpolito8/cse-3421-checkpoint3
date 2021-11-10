import java.util.Date;

public class Order {
	Date arrivalDate;
	int orderNumber;
	public Order(int orderNum, Date arrivalDate) {
		this.arrivalDate = arrivalDate;
		this.orderNumber = orderNum;
	}
	
//	public void addOrderItem(OrderItem orderItem) {
//		items.add(orderItem);
//	}
//	
//	public double getTotalPrice() {
//		double sum = 0.0;
//		for(int i = 0; i < items.size(); i++) {
//			sum += items.get(i).getTotalPrice();
//		}
//		return sum;
//	}
}
