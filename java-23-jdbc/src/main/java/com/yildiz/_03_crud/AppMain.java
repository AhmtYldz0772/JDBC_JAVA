package com.yildiz._03_crud;

import java.sql.*;

public class AppMain {
     static  Connection  connection = null ;
    // bağlantı kurma
    // static doğrudan çağrılıp kullanabilme özelliği ekler
    public static Connection veriTabaninaBaglanti(){
        final String DB_URL = "jdbc:mysql://localhost:3306/firmaveritabani";
        final String DB_USER = "root";
        final String DB_PASS = "root";
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER,DB_PASS);
            if (connection.isClosed()) {
                System.out.println("bağlantı kurulamadı");
            }
            else{
                System.out.println("bağlanti kuruldu....");
            }
            return connection;

        } catch (SQLException e) {
            System.out.println("veri tabanına bağlantı kurulamadı" + e);
            return null;
        }
        finally {
            System.out.println("teşekkürler");
        }

    }


    // read veri okuma
    public String veriOku(){
        veriTabaninaBaglanti();
        String personelBilgisi= null;
        try {
            PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM personel" );
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(
                        resultSet.getInt("personel_id") + " " +
                        resultSet.getString("adi") + " "+
                        resultSet.getString("soyadi")  +" "+
                        resultSet.getInt("maas") +" "+
                        resultSet.getString("eposta")+" "+
                        resultSet.getString("gorevi")
                );
                personelBilgisi = personelBilgisi + resultSet.getInt("personel_id") + " " +
                        resultSet.getString("adi") + " "+
                        resultSet.getString("soyadi")  +" "+
                        resultSet.getInt("maas") +" "+
                        resultSet.getString("eposta")+" "+
                        resultSet.getString("gorevi");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return personelBilgisi;
    }

    // add insert ekleme
    // INSERT INTO `firmaveritabani`.`personel` (`adi`, `soyadi`, `maas`,     `eposta`,       `gorevi`)
    // VALUES (                                  'sena',   'y',   '15000', 'sena@gagga.com', 'developer');   aynı hizaya aynı değerler gelmeli parametre sayısı uymalı
    String durumBilgisi = null;
    void veriEkle(String adi, String soyadi, String eposta, String gorevi, int maas) {

       // Insert Into `firmaveritabani`.`personel` (`adi`, `soyadi`, `maas`,     `eposta`,       `gorevi`) VALUES ( ?,?,?,?,? )
        veriTabaninaBaglanti();

        try {
            String sqlQUERY = "Insert Into `firmaveritabani`.`personel` (`adi`, `soyadi`, `maas`,     `eposta`,       `gorevi`) VALUES ( ?,?,?,?,? )";
            PreparedStatement prepareStatement = connection.prepareStatement(sqlQUERY);
            prepareStatement.setString(1, adi);
            prepareStatement.setString(2, soyadi);
            prepareStatement.setInt(3, maas);
            prepareStatement.setString(4, eposta);
            prepareStatement.setString(5, gorevi);

            // prepareStatement.executeUpdate();    bu ve aşağıdaki iki fonksiyon veriyi ekledikten sonra sana değer dönderiri verinin eklendiğine dair
            boolean result = prepareStatement.execute();
            if (!result) {
                durumBilgisi = "ekleme başarılı";
            }
            else{
                durumBilgisi = "veriEkle olmadı";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            System.out.println("durum bilgisi:" + durumBilgisi);
        }
    }

    // update günceleme edıt düzenleme
    void veriDuzenle(String adi, String soyadi,String maasi, String eposta, String gorevi,  String personelId){

        veriTabaninaBaglanti();

        String durumBilgisi = null;

        try {

            String sqlQuery = "UPDATE personel " +
                    "SET " +
                    "adi = ?, " +
                    "soyadi = ?, " +
                    "maas = ? " +
                    "eposta = ?, " +
                    "gorevi = ?, " +
                    "WHERE personel_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, adi);
            preparedStatement.setString(2, soyadi);
            preparedStatement.setString(3, eposta);
            preparedStatement.setString(4, gorevi);
            preparedStatement.setString(5, maasi);
            preparedStatement.setString(6, personelId);
            //preparedStatement.executeUpdate();

            boolean result = preparedStatement.execute();

            if (!result) {
                durumBilgisi = "Güncelleme başarılı oldu.";
            } else {
                durumBilgisi = "Güncelleme başarısız oldu.";
            }

        } catch (SQLException e) {
            System.out.println("HATA : " + e.getMessage());
        } finally {
            System.out.println(durumBilgisi);
        }

    }


    // delete silme

    void veriSil(String personelId){

        veriTabaninaBaglanti();

        String durumBilgisi = null;

        try {

            String sqlQuery = "UPDATE personel WHERE personel_id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            //preparedStatement.executeUpdate();

            boolean result = preparedStatement.execute();

            if (!result) {
                durumBilgisi = "silme başarılı oldu.";
            } else {
                durumBilgisi = "silme başarısız oldu.";
            }

        } catch (SQLException e) {
            System.out.println("HATA : " + e.getMessage());
        } finally {
            System.out.println(durumBilgisi);
            // TODO BAĞLANTI KAPAT METODU YAPILACAK
        }

    }



    public static void main(String[] args) {
        AppMain appObj = new AppMain();
        //appObj.veriTabaninaBaglanti();
         appObj.veriOku();
        // appObj.veriEkle("Selman", "star",  "selm@gmail.com",  "security", 50000);
        // appObj.veriEkle("emine", "ver",  "emn@gmail.com",  "secreter", 45000);
        //appObj.veriDuzenle("Volkan","Kaytmaz","vvv@kkk.com","CTO","35000","5");
        appObj.veriSil("1");
        System.out.println("===================0");
        appObj.veriOku();
    }
}
