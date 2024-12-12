package com.yildiz._01_select;

import java.sql.*;

public class MyPostgreSqlConnection {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;  // sql sorgularının yazmak için Statement kullanılır
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "admin" );
            stmt = conn.createStatement();
            String sql = "SELECT * FROM public.musteri";
            String sql3 = "SELECT * FROM public.musteri where yas > 30";
            rs = stmt.executeQuery(sql3);
            while(rs.next()) {
                System.out.println(rs.getString("id"  ));
                System.out.println(rs.getString("ad"));
                System.out.println(rs.getString("soyadi"));
                System.out.println(rs.getString("sehir"));
                System.out.println(rs.getString("yas"));
                System.out.println("----------------");
            }
        }catch (Exception e) {
            System.out.println("hata" +e);

        }finally {
            if(rs != null || stmt != null || conn != null) {
                try {
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }


        }
    }
}
