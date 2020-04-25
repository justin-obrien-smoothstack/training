package com.ss.training.lms.versiontwo.business;

/**
 * Provides business logic for LMS functions available to users who are administrators
 * 
 * @author Justin O'Brien
 */
public class AdminService {

	/**
	 * The single instance of this class
	 */
	static AdminService instance = null;

	/**
	 * Private constructor to make this class a singleton
	 */
	private AdminService() {

	}

	/**
	 * Gets the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static AdminService getInstance() {
		if (instance == null)
			instance = new AdminService();
		return instance;
	}
	
}
