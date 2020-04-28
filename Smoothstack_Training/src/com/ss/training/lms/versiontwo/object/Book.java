package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.LMSService;

/**
 * @author Justin O'Brien
 */
public class Book extends LMSObject implements HasCopiesLoansAndIntegerId {

	int id;
	Integer pubId;
	String title;
	ArrayList<Integer> authorIds, genreIds;
	ArrayList<Copies> copies;
	ArrayList<Loan> loans;

	public Book() {
		authorIds = new ArrayList<Integer>();
		genreIds = new ArrayList<Integer>();
		copies = new ArrayList<Copies>();
		loans = new ArrayList<Loan>();
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
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String getDisplayName() {
		StringBuilder displayName = new StringBuilder(title);
		ArrayList<Author> authors = (ArrayList<Author>) new LMSService().getObjectsById(LMS.author, authorIds);
		if (authors.size() != 0) {
			displayName.append(" by " + authors.stream().map(author -> author.getName())
					.reduce((partialList, nextName) -> partialList + ", " + nextName));
		}
		return displayName.toString();
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
	 * @return the pubId
	 */
	public Integer getPubId() {
		return pubId;
	}

	/**
	 * @param pubId the pubId to set
	 */
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
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
	 * @return the authorIds
	 */
	public ArrayList<Integer> getAuthorIds() {
		return authorIds;
	}

	/**
	 * @param authorIds the authorIds to set
	 */
	public void setAuthorIds(ArrayList<Integer> authorIds) {
		this.authorIds = authorIds;
	}

	/**
	 * @return the genreIds
	 */
	public ArrayList<Integer> getGenreIds() {
		return genreIds;
	}

	/**
	 * @param genreIds the genreIds to set
	 */
	public void setGenreIds(ArrayList<Integer> genreIds) {
		this.genreIds = genreIds;
	}

	/**
	 * @return the copies
	 */
	public ArrayList<Copies> getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(ArrayList<Copies> copies) {
		this.copies = copies;
	}

	/**
	 * @return the loans
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

}
