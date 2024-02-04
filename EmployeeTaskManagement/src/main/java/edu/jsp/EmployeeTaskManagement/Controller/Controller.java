package edu.jsp.EmployeeTaskManagement.Controller;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.jsp.EmployeeTaskManagement.Model.Employee;
import edu.jsp.EmployeeTaskManagement.Model.EmployeeNotFoundException;
import edu.jsp.EmployeeTaskManagement.Model.Task;
import edu.jsp.EmployeeTaskManagement.Model.TaskNotFoundException;

public class Controller {

	List<Employee> employeeList=new ArrayList<Employee>();
	List<Task> taskList= new ArrayList<Task>();
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Employee_Task_Mangement");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public Employee addEmployee(Employee employee) {
		
		transaction.begin();
		manager.persist(employee);
		transaction.commit();
		return employee;
		
		
	}

	public Employee removeEmployee(int id) throws EmployeeNotFoundException {

		transaction.begin();
		Employee employee = manager.find(Employee.class, id);
		if(employee!=null) {
			taskList=employee.getTaskList();
			if (taskList!=null) {
			
			for (Task task : taskList) {
				task.getEmployeeList().remove(employee);
			}
		}
			manager.merge(employee);
			manager.remove(employee);
			transaction.commit();
			return employee;
		}
		else {
			throw new EmployeeNotFoundException();
		}

	}

	public Employee searchEmployee(int id) {
		
		Employee employee = manager.find(Employee.class, id);

	    if (employee == null) {
	        throw new EmployeeNotFoundException();
	    }

	    return employee;
	}
	public Task addTask(Task task) {

		transaction.begin();
		manager.persist(task);
		transaction.commit();
		return task;
	}

	public Task removeTask(int id) {

		transaction.begin();
		
		Task task = manager.find(Task.class, id);
		
		if(task!=null) {
		employeeList=task.getEmployeeList();
		if(employeeList!=null) {
		for (Employee employee : employeeList) {
			employee.getTaskList().remove(task);
			}
		}
		manager.merge(task);
		manager.remove(task);
		transaction.commit();
		return task;
		}
		else {
			throw new TaskNotFoundException();
		}

	}

	public Task searchTask(int id) {
		
		Task task = manager.find(Task.class, id);
		if(task==null)
			throw new TaskNotFoundException();
		return task;
		
		}
		
	public List<Employee> getAllEmployee()
	{
		employeeList= manager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
		if (employeeList==null) {
			throw new EmployeeNotFoundException();
		}
		return employeeList;
	}
	
	public List<Task> getAllTask()
	{
		  taskList= manager.createQuery("SELECT t FROM Task t", Task.class).getResultList();
		  if (taskList==null) {
				throw new TaskNotFoundException();
			}
			return taskList;
	}
	

	public Task assignTask(int empId, int taskId) {
		
		Employee employee =  manager.find(Employee.class, empId);
		Task task = manager.find(Task.class, taskId);
		   if (employee == null) {
		        throw new EmployeeNotFoundException();
		    }

		    if (task == null) {
		        throw new TaskNotFoundException();
		    }
		   
		    taskList = employee.getTaskList();
		    taskList.add(task);
		    employee.setTaskList(taskList);
		    employeeList.add(employee);
		    task.setEmployeeList(employeeList);

	    transaction.begin();
	    manager.merge(employee);
	    manager.merge(task);
	    transaction.commit();
		return task;
	}
}