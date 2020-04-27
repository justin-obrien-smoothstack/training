package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;
import java.util.HashMap;

import com.ss.training.lms.versiontwo.business.LMSService;

/**
 * @author Justin O'Brien
 */
public class Book extends LMSObject {

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
	public String getDisplayName() {
		StringBuilder displayName = new StringBuilder(title);
		ArrayList<Author> authors = new LMSService().getAuthorsById(authorIds);
		if (authors.size() != 0) {
			displayName.append(" by " + authors.stream().map(author -> author.getName())
					.reduce((partialList, nextName) -> partialList + ", " + nextName));
		}
		return displayName.toString();
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
