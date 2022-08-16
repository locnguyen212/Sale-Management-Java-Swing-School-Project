package entity;

import java.time.LocalDateTime;

public class Category {
	private int id;
	private String name;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;
	private LocalDateTime deleted_at;
	
	public Category(int id, String name, LocalDateTime created_at, LocalDateTime updated_at, LocalDateTime deleted_at) {
		this.id = id;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.deleted_at = deleted_at;
	}
	public Category() {}
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public LocalDateTime getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}
	public LocalDateTime getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(LocalDateTime deleted_at) {
		this.deleted_at = deleted_at;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ ", deleted_at=" + deleted_at + "]";
	}
	
	
	
	
}
