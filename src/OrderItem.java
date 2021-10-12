
public class OrderItem {
	Media media;
	int quantity;
	double pricePerItem;
	public OrderItem(Media media, int quantity, double pricePerItem) {
		this.media = media;
		this.quantity = quantity;
		this.pricePerItem = pricePerItem;
	}
	
	public double getTotalPrice() {
		return quantity * pricePerItem;
	}
}
