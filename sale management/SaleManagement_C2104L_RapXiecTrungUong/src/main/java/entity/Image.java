package entity;

import java.time.LocalDateTime;

public class Image {
	private int id;
	private int productNameId;
	private String name;
	private LocalDateTime createdAt;	
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	
	public Image(int id, int productNameId, String name, LocalDateTime createdAt, LocalDateTime updatedAt,
			LocalDateTime deletedAt) {
		this.id = id;
		this.productNameId = productNameId;
		this.name = name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	
	public Image() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductNameId() {
		return productNameId;
	}

	public void setProductNameId(int productNameId) {
		this.productNameId = productNameId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Image [id=" + id + ", productNameId=" + productNameId + ", name=" + name + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	}
	
	
	
	
	
}
