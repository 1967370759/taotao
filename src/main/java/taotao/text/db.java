package taotao.text;

import java.sql.*;

public class db {
    private String driver;
    private String url;
    private String username;
    private String password;
    private Connection con;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public void setDriver(String driver) {
        this.driver = driver;
    }
    public void setUrl(String url) {
        this.url = url;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public db() {
        driver="com.mysql.jdbc.Driver";
        url="jdbc:mysql://localhost:3306/taotao?characterEncoding=utf-8";
        username="zzw";
        password="123456789";
    }
    private Connection getConnection(){
        try{
            Class.forName(driver);
            con= DriverManager.getConnection(url,username,password);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return con;
    }


    public static void main(String[] args){
       db db=new db();
        System.out.println(db.getConnection());
    }

}
