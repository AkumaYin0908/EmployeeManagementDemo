/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.view;

import com.demo.connection.DBConnection;
import com.demo.controller.EmployeeController;
import com.demo.controller.SalaryController;
import com.demo.model.Employee;
import com.demo.model.Position;
import com.demo.model.Salary;
import java.util.Scanner;

public class MainForm {
    
    public static void init() {
        System.out.println("EMPLOYEE MANAGEMENT SYSTEM V 1.0.0");
        System.out.println("******* Pet Adoption System v1.12");
        System.out.println("[1] View Employee"
                + "\n[2] Add Employee"
                + "\n[3] Promote Employee"
                + "\n[4] Update Employee"
                + "\n[5] Drop Employee"
                + "\n[6] Delete Employee"
                + "\n[7] Increase Salary"
                + "\n[8] View Retired/Resigned Employee"
                + "\n[0] Exit");
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter choice: ");
        int choice = scn.nextInt();
        
        switch (choice) {
            case 1:
                new EmployeeController(new DBConnection())
                        .viewEmployee(new Employee(), new Position(), new Salary());
                break;
            case 2:
                new EmployeeController(new DBConnection())
                        .addEmployee(new Employee());
                break;
            case 3:                
                new EmployeeController(new DBConnection())
                        .promoteEmployee(new Employee());
                break;
            case 4:
                new EmployeeController(new DBConnection())
                        .updateEmployeeInfo(new Employee());
                break;
            case 5:
                new EmployeeController(new DBConnection())
                        .dropEmployee(new Employee());
                break;
            case 6:
                new EmployeeController(new DBConnection())
                        .deleteEmployee(new Employee());
                break;
            case 7:
                new SalaryController(new DBConnection())
                        .increaseSalary(new Salary());
                break;
            case 8:
                new EmployeeController(new DBConnection())
                        .viewDropEmployee(new Employee(), new Position(), new Salary());
                break;
            case 0:
                System.out.println("The program has been closed");
               System.exit(0);
                break;
                
            default:
                System.out.println("Invalid choice");
                MainForm.init();
                break;   
        }
        
    }
    
}
