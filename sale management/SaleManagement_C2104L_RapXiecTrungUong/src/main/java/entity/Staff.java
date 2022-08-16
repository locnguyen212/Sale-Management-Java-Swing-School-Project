package entity;

import java.time.LocalDateTime;

public class Staff {
	private int id;
	private String name;
	private int level;
	private String password;
	private LocalDateTime createAt;
	private LocalDateTime updateAt;
	private LocalDateTime deleteAt;
	
	public Staff() {
		
	}
	public Staff(int id, String name, int level, String password, LocalDateTime createAt, LocalDateTime deleteAt) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.password = password;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
	}
	public int getId() {
		return id;
	}
	public LocalDateTime getUpdateAt() {
		return updateAt;
	}
	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getCreateAt() {
		return createAt;
	}
	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}
	public LocalDateTime getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(LocalDateTime deleteAt) {
		this.deleteAt = deleteAt;
	}
	@Override
	public String toString() {
		return "Staff [id=" + id + ", name=" + name + ", level=" + level + ", password=" + password + ", createAt="
				+ createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + "]";
	}
	
	
	
	
	
}
