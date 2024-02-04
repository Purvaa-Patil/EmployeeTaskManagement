package edu.jsp.EmployeeTaskManagement.Model;

public class EmployeeNotFoundException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Id : Employee Id does not exist . Please Enter valid Employee Id ";
	}
	public EmployeeNotFoundException() {
		super();
	}

	
	

}
