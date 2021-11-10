
public class OrderItem {
	String mediaName;
	int quantity;
	double pricePerItem;
	public OrderItem(String mediaName, int quantity, double pricePerItem) {
		this.mediaName = mediaName;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
	}
	
	public double getTotalPrice() {
		return quantity * pricePerItem;
	}
}
