/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaapplication2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Baza {
        private Connection connection;

    public void ucitajDriver() throws RuntimeException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver nije pronadjen!");
        }
    }

    public void otvoriKonekciju() throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:odbc:baza", "", "");
            System.out.println("opa");
        } catch (SQLException ex) {
            throw new RuntimeException("Neuspesno otvaranje konekcije!");
        }
    }

    public void insert() {
        String j= "";
        try {
            String upit = "SELECT * FROM TPrevoznik";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                System.out.println(rs);
            }
            System.out.println(upit);
        } catch (SQLException ex) {
        }
    }

}
