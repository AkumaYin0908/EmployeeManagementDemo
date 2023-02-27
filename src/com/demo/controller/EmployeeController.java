/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.controller;

import com.demo.connection.DBConnection;
import com.demo.interfaces.QueryConstants;
import com.demo.model.Employee;
import com.demo.model.Position;
import com.demo.model.Salary;
import com.demo.view.MainForm;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 *
 * @author soface
 */
public class EmployeeController implements QueryConstants {

    private PreparedStatement pStatement;
    private ResultSet result;
    private final DBConnection connection;
    private Scanner scn;

    public EmployeeController(DBConnection connection) {
        this.connection = connection;
        this.connection.connectDB();
    }

    public void viewEmployee(Employee employee, Position position, Salary salary) {
        System.out.println("************************************** EMPLOYEES **************************************");
        try {

            pStatement = connection.getSqlConn().prepareStatement(VIEW_EMPLOYEE);
            pStatement.setInt(1, 1);
            result = pStatement.executeQuery();

            System.out.printf("%s\t%-10s %-15s %-10s\t\t%-20s\t%-40s\t%-5s\t\t%s\n\n", "ID", "First Name", "Middle Name", "Last Name", "Birth Date", "Position", "Salary", "Employment Status");
            while (result.next()) {
                employee.setEmployeeID(result.getInt("employee_id"));
                employee.setFirstName(result.getString("first_name"));
                employee.setMidName(result.getString("mid_name"));
                employee.setLastName(result.getString("last_name"));
                employee.setBirthDate(result.getString("birth_date"));
                position.setPosition(result.getString("position"));
                salary.setSalary(result.getInt("salary"));
                employee.setIsEmployed(result.getBoolean("is_employed"));

                System.out.printf("%d\t%-10s %-15s %-10s\t\t%-20s\t%-40s\t%-15s\t\t%-15s\n", employee.getEmployeeID(),
                        employee.getFirstName(), employee.getMidName(), employee.getLastName(), employee.getBirthDate(), position.getPosition(),
                        new DecimalFormat("###,###.00").format(salary.getSalary()), employee.isIsEmployed() == true ? "Employed" : "Not currently employed");

            }
            System.out.println("***************************************************************************************");
            connection.getSqlConn().close();
            MainForm.init();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addEmployee(Employee employee) {
        scn = new Scanner(System.in);
        System.out.println("************************************** ADD EMPLOYEE **************************************");
        System.out.print("Enter first name: ");
        employee.setFirstName(scn.nextLine());
        System.out.print("Enter middle name: ");
        employee.setMidName(scn.nextLine());
        System.out.print("Enter last name: ");
        employee.setLastName(scn.nextLine());
        System.out.print("Enter birth date(MM/dd/yyyy): ");
        employee.setBirthDate(scn.nextLine());
        System.out.print("Enter position ID: ");
        employee.setPositionID(scn.nextInt());
        scn.nextLine();

        try {
            pStatement = connection.getSqlConn().prepareStatement(ADD_EMPLOYEE);
            pStatement.setString(1, employee.getFirstName());
            pStatement.setString(2, employee.getMidName());
            pStatement.setString(3, employee.getLastName());
            pStatement.setString(4, employee.getBirthDate());
            pStatement.setInt(5, employee.getPositionID());
            pStatement.executeUpdate();

            System.out.println(employee.getFirstName() + " " + employee.getMidName() + " " + employee.getLastName()
                    + " has been successfully");
            System.out.println("***************************************************************************************");
            connection.getSqlConn().close();
            MainForm.init();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void promoteEmployee(Employee employee) {
        scn = new Scanner(System.in);
        System.out.println("************************************** PROMOTE EMPLOYEE **************************************");
        System.out.print("Enter Employee ID: ");
        employee.setEmployeeID(scn.nextInt());
        try {
            
            pStatement = connection.getSqlConn().prepareStatement(SEARCH_DROPPEDEMPLOYEE);
            pStatement.setInt(1, employee.getEmployeeID());
            result = pStatement.executeQuery();

            if (result.next()) {
                System.out.println("This employee has already resigned/retired.");
                MainForm.init();
            } else {

                System.out.println("************************************** CHOOSE POSITION **************************************");
                System.out.printf("%s\t\t%s\n", "Position ID", "Position");

                pStatement = connection.getSqlConn().prepareStatement(VIEW_SORTEDPOSITION);
                result = pStatement.executeQuery();
                while (result.next()) {
                    System.out.printf("%d\t\t%s\n", result.getInt("position_id"), result.getString("position"));
                }
                System.out.println("***************************************************************************************");

                System.out.print("Enter Position ID: ");
                employee.setPositionID(scn.nextInt());

                pStatement = connection.getSqlConn().prepareStatement(PROMOTE_EMPLOYEE);
                pStatement.setInt(1, employee.getPositionID());
                pStatement.setInt(2, employee.getEmployeeID());
                pStatement.executeUpdate();

                System.out.println("Employee ID No." + employee.getEmployeeID() + " has been promoted");
                System.out.println("***************************************************************************************");
                connection.getSqlConn().close();
                MainForm.init();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void dropEmployee(Employee employee) {
        scn = new Scanner(System.in);
        System.out.println("************************************** DROP EMPLOYEE **************************************");

        System.out.println("Enter Employee ID to drop: ");
        employee.setEmployeeID(scn.nextInt());
        try {
            pStatement = connection.getSqlConn().prepareStatement(SEARCH_DROPPEDEMPLOYEE);
            pStatement.setInt(1, employee.getEmployeeID());
            result = pStatement.executeQuery();

            if (result.next()) {
                System.out.println("This employee has already resigned/retired.");
                MainForm.init();
            } else {
                System.out.println("Are you sure you want to drop this employee?\n[1]Yes\n[2]No");
                int choice = scn.nextInt();

                if (choice == 1) {

                    pStatement = connection.getSqlConn().prepareStatement(DROP_EMPLOYEE);
                    pStatement.setInt(1, 0);
                    pStatement.setInt(2, employee.getEmployeeID());
                    pStatement.executeUpdate();
                    System.out.println("Employee ID No." + employee.getEmployeeID() + " has been dropped");
                    System.out.println("***************************************************************************************");
                    connection.getSqlConn().close();
                    MainForm.init();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void deleteEmployee(Employee employee) {
        scn = new Scanner(System.in);
        System.out.println("************************************** DELETE EMPLOYEE **************************************");

        System.out.println("Enter Employee ID to delete: ");
        employee.setEmployeeID(scn.nextInt());
        try {
            pStatement = connection.getSqlConn().prepareStatement(SEARCH_DROPPEDEMPLOYEE);
            pStatement.setInt(1, employee.getEmployeeID());
            result = pStatement.executeQuery();

            if (result.next()) {
                System.out.println("This employee has already resigned/retired.");
                MainForm.init();
            } else {
                System.out.println("Are you sure you want to delete this employee?\n[1]Yes\n[2]No");
                int choice = scn.nextInt();

                if (choice == 1) {

                    pStatement = connection.getSqlConn().prepareStatement(DELETE_EMPLOYEE);
                    pStatement.setInt(1, employee.getEmployeeID());
                    pStatement.executeUpdate();
                    System.out.println("Employee ID No." + employee.getEmployeeID() + " has been deleted");
                    System.out.println("***************************************************************************************");
                    connection.getSqlConn().close();
                    MainForm.init();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void viewDropEmployee(Employee employee, Position position, Salary salary) {
        System.out.println("************************************** RETIRED/RESIGNED EMPLOYEES **************************************");
        try {

            pStatement = connection.getSqlConn().prepareStatement(VIEW_DROPEMPLOYEE);
            pStatement.setInt(1, 0);
            result = pStatement.executeQuery();

            System.out.printf("%s\t%-10s %-15s %-10s\t\t%-20s\t%-40s\t%-5s\t\t%s\n\n", "ID", "First Name", "Middle Name", "Last Name", "Birth Date", "Position", "Salary", "Employment Status");
            while (result.next()) {
                employee.setEmployeeID(result.getInt("employee_id"));
                employee.setFirstName(result.getString("first_name"));
                employee.setMidName(result.getString("mid_name"));
                employee.setLastName(result.getString("last_name"));
                employee.setBirthDate(result.getString("birth_date"));
                position.setPosition(result.getString("position"));
                salary.setSalary(result.getInt("salary"));
                employee.setIsEmployed(result.getBoolean("is_employed"));

                System.out.printf("%d\t%-10s %-15s %-10s\t\t%-20s\t%-40s\t%-15s\t\t%-15s\n", employee.getEmployeeID(),
                        employee.getFirstName(), employee.getMidName(), employee.getLastName(), employee.getBirthDate(), position.getPosition(),
                        new DecimalFormat("###,###.00").format(salary.getSalary()), employee.isIsEmployed() == true ? "Employed" : "Not currently employed");

            }
            System.out.println("***************************************************************************************");
            connection.getSqlConn().close();
            MainForm.init();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateEmployeeInfo(Employee employee) {

        System.out.println("************************************** UPDATE EMPLOYEE INFO **************************************");
        scn = new Scanner(System.in);
        System.out.println("Enter Employee ID to update: ");
        employee.setEmployeeID(scn.nextInt());
        try {
            pStatement = connection.getSqlConn().prepareStatement(SEARCH_DROPPEDEMPLOYEE);
            pStatement.setInt(1, employee.getEmployeeID());
            result = pStatement.executeQuery();

            if (result.next()) {
                System.out.println("This employee has already resigned/retired.");
                MainForm.init();
            } else {
                System.out.println("Choose information to update:"
                        + "\n[1] First Name"
                        + "\n[2] Middle Name"
                        + "\n[3] Last Name"
                        + "\n[4] Birth Date"
                        + "\n[0] Cancel"
                );

                int choice = scn.nextInt();

                switch (choice) {

                    case 1:
                        updateFirstName(employee.getEmployeeID());
                        break;
                    case 2:
                        updateMidName(employee.getEmployeeID());
                        break;
                    case 3:
                        updateLastName(employee.getEmployeeID());
                        break;
                    case 4:
                        updateBirthDate(employee.getEmployeeID());
                        break;
                    case 0:
                        MainForm.init();
                        break;
                    default:
                        System.out.println("Invalid choice");
                        updateEmployeeInfo(new Employee());
                        break;

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private void updateFirstName(int employeeID) throws SQLException {
        scn = new Scanner(System.in);
        System.out.println("Enter new first name: ");
        String firstName = scn.nextLine();

        pStatement = connection.getSqlConn().prepareStatement(UPDATE_FIRSTNAME);
        pStatement.setString(1, firstName);
        pStatement.setInt(2, employeeID);
        pStatement.executeUpdate();
        System.out.println("Employee ID No." + employeeID + "'s first name has been updated.");
        connection.getSqlConn().close();
        MainForm.init();
    }

    private void updateMidName(int employeeID) throws SQLException {
        scn = new Scanner(System.in);
        System.out.println("Enter new middle name: ");
        String midName = scn.nextLine();

        pStatement = connection.getSqlConn().prepareStatement(UPDATE_FIRSTNAME);
        pStatement.setString(1, midName);
        pStatement.setInt(2, employeeID);
        pStatement.executeUpdate();
        System.out.println("Employee ID No." + employeeID + "'s middle name has been updated.");
        connection.getSqlConn().close();
        MainForm.init();
    }

    private void updateLastName(int employeeID) throws SQLException {
        scn = new Scanner(System.in);
        System.out.println("Enter new last name: ");
        String lastName = scn.nextLine();

        pStatement = connection.getSqlConn().prepareStatement(UPDATE_LASTNAME);
        pStatement.setString(1, lastName);
        pStatement.setInt(2, employeeID);
        pStatement.executeUpdate();
        System.out.println("Employee ID No." + employeeID + "'s last name has been updated.");
        connection.getSqlConn().close();
        MainForm.init();
    }

    private void updateBirthDate(int employeeID) throws SQLException {
        scn = new Scanner(System.in);
        System.out.println("Enter new birth date(MM/dd/yyyy): ");
        String birthDate = scn.nextLine();

        pStatement = connection.getSqlConn().prepareStatement(UPDATE_BIRTHDATE);
        pStatement.setString(1, birthDate);
        pStatement.setInt(2, employeeID);
        pStatement.executeUpdate();
        System.out.println("Employee ID No." + employeeID + "'s birth date has been updated.");
        connection.getSqlConn().close();
        MainForm.init();
    }

}
