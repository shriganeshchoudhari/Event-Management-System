package com.database.project.eventManagmentSystem;

public class Admin extends Participant {
	
	private int id;
	
	public Admin(int id, String name){
		super(id, name);
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Admin [id=" + id + "]";
	}
}
