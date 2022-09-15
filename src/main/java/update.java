import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/update1")
public class update extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String pass=req.getParameter("pass");
        String pro=req.getParameter("pro");
        try{
            Connection connection=connections.ConnectionProvider.getConnection();
            PreparedStatement p=connection.prepareStatement("update admin set pass=? where name=?");
            p.setString(1,pass);

            p.setString(2,name);

            p.executeUpdate();
            RequestDispatcher rt=req.getRequestDispatcher("welcome");
            rt.forward(req,resp);
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
