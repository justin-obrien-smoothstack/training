package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;

/**
 * @author Justin O'Brien
 */
public class Borrower extends LMSObject implements HasIntegerId {

	private int cardNo;
	private String name, address, phone;
	private ArrayList<Loan> loans;
	
	public Borrower(){
		loans = new ArrayList<Loan>();
	}
	
	/**
	 * @return the cardNo
	 */
	public int getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
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
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getId() {
		return getCardNo();
	}
	
	public void setId(int id) {
		setCardNo(id);
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

	@Override
	public String getDisplayName() {
		return name == null ? "(name not found)" : getName();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cardNo;
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
		Borrower other = (Borrower) obj;
		if (cardNo != other.cardNo)
			return false;
		return true;
	}
}
