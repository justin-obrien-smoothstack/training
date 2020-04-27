package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public class Author extends LMSObject {

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, HashMap<String, Object>> getFieldsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFieldsMap(HashMap<String, HashMap<String, Object>> fieldsMap) {
		// TODO Auto-generated method stub

	}

}
