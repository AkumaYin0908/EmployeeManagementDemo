/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.model;

public class Salary {

    private int salaryGrade;
    private int salary;

    public Salary() {
    }

    public Salary(int salaryGrade, int salary) {
        this.salaryGrade = salaryGrade;
        this.salary = salary;
    }

    public int getSalaryGrade() {
        return salaryGrade;
    }

    public void setSalaryGrade(int salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}
