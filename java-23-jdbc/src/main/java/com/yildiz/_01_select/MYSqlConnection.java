package com.yildiz._01_select;
import java.sql.*;
public class MYSqlConnection {



        public static void main(String[] args) {

            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {

                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/benim_sirketim",
                        "root",
                        "root");

                statement = connection.createStatement();


                String sql1 = "SELECT * FROM musteriler";
                String sql2 = "SELECT * FROM benim_sirketim.musteriler;";
                String sql3 = "SELECT * FROM musteriler WHERE yasi>25";

                resultSet = statement.executeQuery(sql2);

                while (resultSet.next()) {
                /*
                System.out.println(resultSet.getString(1) + "  " +
                         resultSet.getString(2) + "  " +
                         resultSet.getString(3) + "  " +
                         resultSet.getString(4) + "  " +
                         resultSet.getString(5));
                */
                    // id, adi, soyadi, sehir, yasi
                    System.out.println(
                            resultSet.getInt("id") + "  " +
                                    resultSet.getString("ad") + "  " +
                                    resultSet.getString("soyadi") + "  " +
                                    resultSet.getString("sehir") + "  " +
                                    resultSet.getInt("yas"));
                    System.out.println("-------------------------------------");
                }


            } catch (Exception e) {
                System.out.println("Hata: " + e);

            } finally {
                if (resultSet != null || statement != null || connection != null) {
                    try {
                        resultSet.close();
                        statement.close();
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }

            }

        }

}
