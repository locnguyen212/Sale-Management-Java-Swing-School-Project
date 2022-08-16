package entity;

import java.time.LocalDateTime;

public class OrderDetail {
	private String productName;
	private int id;
	private int orderId;
	private int productId;
	private int quantity;
	private int price;
	private int DT;
	
	private LocalDateTime createdAt;	
	private LocalDateTime updatedAt;
	

	
	public OrderDetail(String productName, int id, int orderId, int productId, int quantity, int price,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.productName = productName;
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	public OrderDetail(int id, int orderId, int productId, int quantity,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	

	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public OrderDetail() {

	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Override
	public String toString() {
		return "OrderDetail [productName=" + productName + ", id=" + id + ", orderId=" + orderId + ", productId="
				+ productId + ", quantity=" + quantity + ", price=" + price + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}
	
	
	

	
}
