import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/inssert")
public class insertt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String name=req.getParameter("name");
     String pass=req.getParameter("pass");
     String pro=req.getParameter("pro");
   try{
       Connection connection=connections.ConnectionProvider.getConnection();
       PreparedStatement p=connection.prepareStatement("insert into admin values(?,?,?)");
       p.setString(1,name);
       p.setString(2,pass);
       p.setString(3,pro);
       p.executeUpdate();
       RequestDispatcher  rt=req.getRequestDispatcher("welcome");
       rt.forward(req,resp);
   }catch(Exception e)
   {

   }
    }
}
