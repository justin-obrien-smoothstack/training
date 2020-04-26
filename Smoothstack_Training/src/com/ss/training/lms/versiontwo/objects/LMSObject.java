package com.ss.training.lms.versiontwo.objects;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class will be extended by each type of object in the LMS system
 * 
 * @author Justin O'Brien
 */
public abstract class LMSObject {

	/**
	 * @return The object's name, title, etc.
	 */
	public abstract String getName();

	/**
	 * @return Hash map containing object's fields in a format convenient for use by
	 *         presentation tier
	 */
	public abstract HashMap<String, HashMap<String,HashMap<String,Object>>> getFieldsMap();
}
