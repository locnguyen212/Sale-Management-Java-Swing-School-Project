package entity;

import java.time.LocalDateTime;

public class Properties {
	private int id;
	private String name;
	private int parentId;
	private LocalDateTime createdAt;	
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	
	public Properties(int id, String name, int parentId, LocalDateTime createdAt, LocalDateTime updatedAt,
			LocalDateTime deletedAt) {
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
	}
	
	public Properties() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
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
		return "Properties [id=" + id + ", name=" + name + ", parentId=" + parentId + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt + "]";
	};
	
	
}
