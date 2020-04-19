package com.ss.training.librarymanager.entities;

import java.io.Serializable;

/**
 * Instances of this class will represent authors of books in the user's
 * library.
 * 
 * @author Justin O'Brien
 *
 */
public class Author implements Serializable {
	private static final long serialVersionUID = -3301717654407922685L;
	private final long id; // primary key of the author
	private String name; // name of the author

	/**
	 * @param id   primary key of the author
	 * @param name name of the author
	 */
	public Author(long id, String name) {
		this.id = id;
		this.name = name;
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
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
