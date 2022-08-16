package gui.orderDetail;

public class ShopCart {
	private String name;
	private int quatity;
	private int price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public ShopCart(String name, int quatity, int price) {
		this.name = name;
		this.quatity = quatity;
		this.price = price;
	}
	public ShopCart() {}
	@Override
	public String toString() {
		return "ShopCart [name=" + name + ", quatity=" + quatity + ", price=" + price + "]";
	}
	
}
