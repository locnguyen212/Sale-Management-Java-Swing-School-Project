package entity;

import java.time.LocalDateTime;

public class ProductProperties {
	private int id;
	private int productId;
	private int propertiesId;
	private LocalDateTime createdAt;	
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	
	public ProductProperties(int id, int productId, int propertiesId, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalDateTime deletedAt) {
		super();
		this.id = id;
		this.productId = productId;
		this.propertiesId = propertiesId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	
	public ProductProperties() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPropertiesId() {
		return propertiesId;
	}

	public void setPropertiesId(int propertiesId) {
		this.propertiesId = propertiesId;
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
		return "ProductProperties [id=" + id + ", productId=" + productId + ", propertiesId=" + propertiesId
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}
	
	
	
	
}
