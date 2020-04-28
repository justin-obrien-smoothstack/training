package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;

/**
 * @author Justin O'Brien
 */
public class Author extends LMSObject implements HasIntegerId {

	private int id;
	private String name;
	private ArrayList<Integer> bookIds;
	
	public Author() {
		bookIds = new ArrayList<Integer>();
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

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the bookIds
	 */
	public ArrayList<Integer> getBookIds() {
		return bookIds;
	}

	/**
	 * @param bookIds the bookIds to set
	 */
	public void setBookIds(ArrayList<Integer> bookIds) {
		this.bookIds = bookIds;
	}

	@Override
	public String getDisplayName() {
		return getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
