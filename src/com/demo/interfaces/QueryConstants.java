/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.interfaces;

public interface QueryConstants {

    String VIEW_EMPLOYEE = "select * from tblemployee "
            + "left join tblposition on tblemployee.position_id=tblposition.position_id "
            + "left join tblsalary on tblposition.salary_grade=tblsalary.salary_grade where is_employed=?";

    String VIEW_DROPEMPLOYEE = "select * from tblemployee "
            + "left join tblposition on tblemployee.position_id=tblposition.position_id "
            + "left join tblsalary on tblposition.salary_grade=tblsalary.salary_grade where is_employed=?";

    String SEARCH_EMPLOYEE = "select * from tblemployee where employee_id=?";
    
    String SEARCH_DROPPEDEMPLOYEE = "select * from tblemployee where employee_id=? and is_employed=0";

    String ADD_EMPLOYEE = "insert into tblemployee(first_name,mid_name,last_name,birth_date,position_id) "
            + "values(?,?,?,?,?)";

    String VIEW_SORTEDPOSITION = "select position_id,position from tblposition order by salary_grade";

    String PROMOTE_EMPLOYEE = "update tblemployee set position_id=? where employee_id=?";

    String DROP_EMPLOYEE = "update tblemployee set is_employed=? where employee_id=?";

    String DELETE_EMPLOYEE = "delete from tblemployee where employee_id=?";

    String INCREASE_SALARY = "update tblsalary set salary=? where salary_grade=?";

    String VIEW_SALARY = "select * from tblsalary";

    String UPDATE_FIRSTNAME = "update tblemployee set first_name=? where employee_id=?";

    String UPDATE_MIDNAME = "update tblemployee set mid_name=? where employee_id=?";

    String UPDATE_LASTNAME = "update tblemployee set last_name=? where employee_id=?";
    
    String UPDATE_BIRTHDATE = "update tblemployee set birth_date=? where employee_id=?";

}
/*

SELECT column_name(s)
FROM table1
LEFT JOIN table2
ON table1.column_name = table2.column_name;










 */
