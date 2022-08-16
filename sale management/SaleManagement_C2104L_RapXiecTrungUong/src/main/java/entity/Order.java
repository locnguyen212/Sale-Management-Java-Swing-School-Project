package entity;

import java.time.LocalDateTime;

public class Order {
	private int id;
	private int staffId;
	private int orderNumber;
	private LocalDateTime createdAt;
	private LocalDateTime updateAt;
	private String nameStaff;
	private String tolTalCash;

	public Order() {}
	
	
	




	public Order(int id, int staffId, int orderNumber, LocalDateTime createdAt, LocalDateTime updateAt,
			String nameStaff, String tolTalCash) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.orderNumber = orderNumber;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
		this.nameStaff = nameStaff;
		this.tolTalCash = tolTalCash;
	}

	public Order(int id, int staffId, int orderNumber, LocalDateTime createdAt, LocalDateTime deleteAt) {
		super();
		this.id = id;
		this.staffId = staffId;
		this.orderNumber = orderNumber;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}
  
	public String getTolTalCash() {
		return tolTalCash;
	}

	public void setTolTalCash(String tolTalCash) {
		this.tolTalCash = tolTalCash;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime deleteAt) {
		this.updateAt = deleteAt;
	}

	public String getNameStaff() {
		return nameStaff;
	}



	public void setNameStaff(String nameStaff) {
		this.nameStaff = nameStaff;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", staffId=" + staffId + ", orderNumber=" + orderNumber + ", createdAt=" + createdAt
				+ ", updateAt=" + updateAt + ", nameStaff=" + nameStaff + ", tolTalCash=" + tolTalCash + "]";
	}







	

}
