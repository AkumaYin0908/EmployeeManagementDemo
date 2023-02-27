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
import java.util.Scanner;


public class SalaryController implements QueryConstants{

    private PreparedStatement pStatement;
    private ResultSet result;
    private final DBConnection connection;
    private Scanner scn;

    public SalaryController(DBConnection connection) {
        this.connection=connection;
        this.connection.connectDB();
    }

    public void increaseSalary(Salary salary) {
        scn = new Scanner(System.in);
        System.out.println("************************************** INCREASE SALARY **************************************");
        System.out.println("Enter amount increased per salary: ");
        int increase = scn.nextInt();

        System.out.println("Are you sure you want to increase the salary?\n[1]Yes\n[2]No");
        int choice=scn.nextInt();
        if(choice==1){
            try {
                pStatement=connection.getSqlConn().prepareStatement(VIEW_SALARY);
                result=pStatement.executeQuery();
                
                while(result.next()){
                    pStatement=connection.getSqlConn().prepareStatement(INCREASE_SALARY);
                    pStatement.setInt(1, result.getInt("salary") + increase);
                    pStatement.setInt(2, result.getInt("salary_grade"));
                    pStatement.executeUpdate();
                    
                }
                System.out.println("Salary has been increased");
                System.out.println("***************************************************************************************");
                connection.getSqlConn().close();
                MainForm.init();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }
}
