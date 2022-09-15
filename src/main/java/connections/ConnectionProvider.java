package connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider{
    private static Connection connection=null;
    public static Connection getConnection()
    {
        if(connection==null){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/anuj","root","root");

        }catch(Exception e)
        {
          System.out.println(e);
        }}
        return connection;
    }
}
