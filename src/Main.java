// program for image handling...

import java.sql.*;
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String image_path ="C:\\Users\\LENOVO\\Downloads.jpg";
        String query = "INSERT INTO image_table(image_data) VALUES(?)";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drivers loaded succesfully");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());

        }

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("connection established succesfully");
            FileInputStream fileInputStream = new FileInputStream(image_path);
            byte[] imageData = new byte[fileInputStream.available()];
            fileInputStream.read(imageData);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1,imageData);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0) {
                System.out.println("image inserted succesfully");
            }
            else {
                System.out.println("image not inserted");
            }





        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch (FileNotFoundExecption e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);

        }
    }
}