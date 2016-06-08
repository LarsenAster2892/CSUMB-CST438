package edu.cst438.slttplus;

import java.util.ArrayList;

/* 
 * the purpose of this class is that Gson has problems
 * handling generic type lists.  This class makes it easier
 * to retrieve a list of projects to the client.
 */
public class ProjectList {
	
	public ArrayList<Student> list; 

}