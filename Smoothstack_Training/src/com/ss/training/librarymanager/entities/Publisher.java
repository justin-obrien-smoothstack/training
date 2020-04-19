/**
 * 
 */
package com.ss.training.librarymanager.entities;

import java.io.Serializable;

/**
 * @author Justin O'Brien
 *
 */
public class Publisher implements Serializable {
	private static final long serialVersionUID = 5259732366592281794L;
	private final long id; // primary key of the publisher
	private String name; // name of the publisher
	private String address; // address of the publisher

	/**
	 * @param id      primary key of the publisher
	 * @param name    name of the publisher
	 * @param address address of the publisher
	 */
	public Publisher(long id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
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
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
		Publisher other = (Publisher) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
