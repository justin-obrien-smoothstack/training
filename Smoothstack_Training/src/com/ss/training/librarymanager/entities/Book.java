package com.ss.training.librarymanager.entities;

import java.io.Serializable;

/**
 * Instances of this class will represent books in the user's library.
 * 
 * @author Justin O'Brien
 *
 */
public class Book implements Serializable {
	private static final long serialVersionUID = 6937091138810532549L;
	private final long id; // primary key of the book
	private String title; // title of the book
	private long author, publisher; // primary keys of the author and publisher of the book

	/**
	 * Generator function
	 * 
	 * @param id        primary key of the book
	 * @param title     title of the book
	 * @param author    primary key of the author of the book
	 * @param publisher primary key of the publisher of the book
	 */
	public Book(long id, String title, long author, long publisher) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public long getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(long author) {
		this.author = author;
	}

	/**
	 * @return the publisher
	 */
	public long getPublisher() {
		return publisher;
	}

	/**
	 * @param publisher the publisher to set
	 */
	public void setPublisher(long publisher) {
		this.publisher = publisher;
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
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
