package edu.jspp.EmployeeTaskManagement.View;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import edu.jsp.EmployeeTaskManagement.Controller.Controller;
import edu.jsp.EmployeeTaskManagement.Model.Employee;
import edu.jsp.EmployeeTaskManagement.Model.EmployeeNotFoundException;
import edu.jsp.EmployeeTaskManagement.Model.Task;
import edu.jsp.EmployeeTaskManagement.Model.TaskNotFoundException;

public class View {

	static Scanner sc = new Scanner(System.in);
	static Employee employee;
	static Task task;
	static Controller controller = new Controller();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean view = true;

		while (view) {
			System.out.println("\n <<=============== EMPLOYEE TASK MANAGMENT SYSTEM ===============>> "
					+ " \n Enter your choice ..\n 1.Add Employee" 
					+ " \n 2.Remove Employee"
					+ " \n 3.Search Employee"
					+ " \n 4.Add Task"
					+ " \n 5.Remove Task"
					+ " \n 6.Search Task"
					+ " \n 7.Get All Employees"
					+ " \n 8.Get All Task"
					+ " \n 9.Assign Task"
					+ " \n 10.Add Employee to Task"
					+ " \n 11.Exit");
			int userInput = sc.nextInt();
			switch (userInput) {
			case 1:
				addEmployee();
				break;
			case 2:
				removeEmployee();
				break;
			case 3:
				searchEmployee();
				break;
			case 4:
				addTask();
				break;
			case 5:
				removeTask();
				break;
			case 6:
				searchTask();
				break;
			case 7:
				getAllEmployee();
				break;
			case 8:
				getAllTask();
				break;
			case 9:
				assignTask();
				break;
			case 10:
				System.out.println("<<=============== Thank You ===============>>");
				view = false;
				break;

			default:
				System.out.println("Invalid Choice ..");
				break;
			}
		}

	}
	private static void addEmployee() {
		// TODO Auto-generated method stub
		System.out.println("----------------- ENTER EMPLOYEE DETAILS-------------------");
		employee = new Employee();
		System.out.println("Enter Employee Name : ");
		employee.setName(sc.next());
		System.out.println("Enter Employee's age : ");
		employee.setAge(sc.nextInt());
		System.out.println("Enter Employee's Salary : ");
		employee.setSalary(sc.nextInt());
		employee.setDateOfJoining(LocalDate.now());
		controller.addEmployee(employee);
		System.out.println("------------------------------------");

	}

	private static void removeEmployee() {
		// TODO Auto-generated method stub
		try {
			System.out.println("----------------- ENTER EMPLOYEE DETAILS-------------------");
			System.out.println("Enter Employee Id ");
		
			Employee employee= controller.removeEmployee(sc.nextInt());
			System.out.println("Employee Id : "+employee.getId()+" Employee name : "+employee.getName()+" removed ");
			
		} catch (EmployeeNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
		}
		System.out.println("------------------------------------");
	}

	private static void searchEmployee() {
		// TODO Auto-generated method stub
		try {
			System.out.println("------------------------------------");
			System.out.println("Enter Employee Id ");
			employee = controller.searchEmployee(sc.nextInt());

			System.out.println("------------------EMPLOYEE DETAILS------------------");
			System.out.println(" \n ID : " + employee.getId()
				+ " \n Name : " + employee.getName()
				+ " \n Age : "+ employee.getAge()
				+ " \n Salary : " + employee.getSalary()
				+ " \n Date Of Joining : "+ employee.getDateOfJoining());

		} catch (EmployeeNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
		}
		System.out.println("------------------------------------");
	}

	private static void addTask() {
		// TODO Auto-generated method stub
		System.out.println("----------------- ENTER TASK DETAILS-------------------");

		task = new Task();
		System.out.println("Task Name : ");
		task.setTaskName(sc.next());
		sc.nextLine();
		task.setStartDate(LocalDate.now());
		System.out.println("Enter the date (format: dd-MM-yyyy): ");
		String dateInput = sc.nextLine();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate localDate = LocalDate.parse(dateInput, formatter);
		task.setEndDate(localDate);
		controller.addTask(task);

		System.out.println("------------------------------------");

	}

	private static void removeTask() {
		// TODO Auto-generated method stub
		try {
			System.out.println("----------------- ENTER EMPLOYEE DETAILS-------------------");
			System.out.println("Enter task Id ");
			Task task = controller.removeTask(sc.nextInt());
			System.out.println(task.getTaskName()+" has been removed successfully");
		} catch (TaskNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
		}
		System.out.println("------------------------------------");
	}

	private static void searchTask() {
		// TODO Auto-generated method stub
		try {
			System.out.println("------------------------------------");
			System.out.println("Enter task Id ");
			task = controller.searchTask(sc.nextInt());

			System.out.println("----------------TASK DETAILS--------------------");
			System.out.println(" \n ID : " + task.getTaskId() 
					+ " \n Name : " + task.getTaskName()
					+ " \n Assignment Date : " + task.getStartDate()
					+ " \n End Date : " + task.getEndDate());

		} catch (TaskNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
		}
		System.out.println("------------------------------------");
	}
	private static void getAllEmployee() {
		// TODO Auto-generated method stub
		try {
		List<Employee> employees =controller.getAllEmployee();
		for (Employee employee : employees) {
			
			System.out.println("----------------- EMPLOYEE DETAILS-------------------");
			System.out.println(" \n ID : " + employee.getId()
				+ " \n Name : " + employee.getName()
				+ " \n Age : "+ employee.getAge()
				+ " \n Salary : " + employee.getSalary()
				+ " \n Date Of Joining : "+ employee.getDateOfJoining()
				+ " \n Task Assigned :  ");
			
			if (employee.getTaskList()!=null) {
				for (Task task: employee.getTaskList()) {
				
				System.out.println(" \n ID : " + task.getTaskId()
					+ " \n Name : " + task.getTaskName()
					+ " \n Date of Assignment : "+ task.getStartDate()
					+ " \n End Date : " + task.getEndDate());
				}
			}
			else {
				System.out.println("No Task Assigned Yet");
		}
		}
		}catch (EmployeeNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
		}
	}
	private static void getAllTask() {
		// TODO Auto-generated method stub
		try {
			List<Task> tasks= controller.getAllTask();
			for (Task task : tasks) {
				
					System.out.println("----------------- TASK DETAILS-------------------");
					System.out.println(" \n ID : " + task.getTaskId()
						+ " \n Name : " + task.getTaskName()
						+ " \n Date of Assignment : "+ task.getStartDate()
						+ " \n End Date : " + task.getEndDate());
						
				}
		} catch (TaskNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
		}
	}


	private static void assignTask() {
		// TODO Auto-generated method stub
		try {
		System.out.println("----------------- ENTER DETAILS-------------------");
		System.out.println("Enter Employee Id :");
		int empId=sc.nextInt();
		System.out.println("Enter Task Id :");
		int taskId=sc.nextInt();
		Task assignedTask =controller.assignTask(empId,taskId);
		if(assignedTask!=null) {
			System.out.println("Task "+assignedTask.getTaskName()+" Assigned to "+ empId);
		}
		}catch (EmployeeNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
			
		}catch (TaskNotFoundException e) {
			// TODO: handle exception
			System.out.println(" \n" + e.getMessage() + " \n");
			
		}
		
	}

		}




