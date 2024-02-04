package edu.jsp.EmployeeTaskManagement.Model;

public class TaskNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Id : Task Id does not exist . Please Enter valid Task Id";
	}
	public TaskNotFoundException() {
		super();
	}

	}
