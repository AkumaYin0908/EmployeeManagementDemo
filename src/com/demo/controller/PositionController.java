/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.controller;

import com.demo.connection.DBConnection;
import com.demo.model.Position;
import com.demo.model.Salary;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author soface
 */
public class PositionController {

    private PreparedStatement pStatement;
    private ResultSet result;
    private final DBConnection connection;
    private Scanner scn;
    
    public PositionController(DBConnection connection){
        this.connection=connection;
        this.connection.connectDB();
    }
    
     
}
