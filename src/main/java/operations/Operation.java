package operations;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Operation {
    public String insert(int id,String name,String pass)
    {
        try {
            Connection con = connections.ConnectionProvider.getConnection();
            PreparedStatement p = con.prepareStatement("insert into Employee values(?,?,?)");
            p.setInt(1,id);
            p.setString(2,name);
            p.setString(3,pass);
            int d=p.executeUpdate();
            if(d>0)
                return "Inserted";
            else
                return "Not Inserted";
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    public String delete(int id)
    {
        try{
        Connection con = connections.ConnectionProvider.getConnection();
        PreparedStatement p = con.prepareStatement("delete from Employee where id=?");
        p.setInt(1,id);
        int i=p.executeUpdate();
        if(i>0)
            return "Deleted";
        else
            return "Not Deleted";
        }catch(Exception e)
        {

        }
        return null;
    }

    public String update(int id,String name)
    {
        try{
            Connection con = connections.ConnectionProvider.getConnection();
            PreparedStatement p = con.prepareStatement("update Employee set name=? where id=?");
            p.setString(1,name);
            p.setInt(2,id);

            int i=p.executeUpdate();
            if(i>0)
                return "Deleted";
            else
                return "Not Deleted";
        }catch(Exception e)
        {

        }
        return null;
    }
}


