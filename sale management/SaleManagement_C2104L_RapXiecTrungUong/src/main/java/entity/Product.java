package entity;

import java.time.LocalDateTime;

public class Product {
	private int id;
	private int categoryId;
	private int productNameId;
	private int quantity;
	private int quantitySold;
	private boolean isAvailable;
	private int buyPrice;
	private int price;
	private String information;
	private LocalDateTime createdAt;	
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	
	public Product(int id, int categoryId, int productNameId, int quantity, int quantitySold, boolean isAvailable,
			int buyPrice, int price, String information, LocalDateTime createdAt, LocalDateTime updatedAt,
			LocalDateTime deletedAt) {
		this.id = id;
		this.categoryId = categoryId;
		this.productNameId = productNameId;
		this.quantity = quantity;
		this.quantitySold = quantitySold;
		this.isAvailable = isAvailable;
		this.buyPrice = buyPrice;
		this.price = price;
		this.information = information;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	
	public Product() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getProductNameId() {
		return productNameId;
	}
	public void setProductNameId(int productNameId) {
		this.productNameId = productNameId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public int getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(int buyPrice) {
		this.buyPrice = buyPrice;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
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
	public LocalDateTime getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(LocalDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", categoryId=" + categoryId + ", productNameId=" + productNameId + ", quantity="
				+ quantity + ", quantitySold=" + quantitySold + ", isAvailable=" + isAvailable + ", buyPrice="
				+ buyPrice + ", price=" + price + ", information=" + information + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}
	
	
	
	
}
