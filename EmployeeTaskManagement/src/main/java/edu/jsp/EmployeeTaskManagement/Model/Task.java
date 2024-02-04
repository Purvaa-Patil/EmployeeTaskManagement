package edu.jsp.EmployeeTaskManagement.Model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Task {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "My_Seq")
		@SequenceGenerator(name = "My_Seq",initialValue = 1000, allocationSize = 10,sequenceName = "My_cust_seq")
		private int taskId;
		
		private String taskName;
		private LocalDate startDate;
		private LocalDate endDate;
		
		
		@ManyToMany(mappedBy = "taskList", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
		List<Employee> employeeList;

		public int getTaskId() {
			return taskId;
		}

		
		public String getTaskName() {
			return taskName;
		}

		public void setTaskName(String taskName) {
			this.taskName = taskName;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public List<Employee> getEmployeeList() {
			return employeeList;
		}

		public void setEmployeeList(List<Employee> employeeList) {
			this.employeeList = employeeList;
		}

		public LocalDate getEndDate() {
			return endDate;
		}


		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}


		public Task() {
			super();
		}


		


		
		
}
