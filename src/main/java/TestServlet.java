import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String username=req.getParameter("username");
            String password=req.getParameter("password");
         resp.setContentType("text/html");
            PrintWriter out=resp.getWriter();
            Connection connection=connections.ConnectionProvider.getConnection();
            PreparedStatement p=connection.prepareStatement("select *from admin where name=? and pass=?");
            p.setString(1,username);
            p.setString(2,password);
            ResultSet rt=p.executeQuery();
            boolean t=rt.next();
            if(t)
            {
                ServletContext s=req.getServletContext();
                s.setAttribute("profile",rt.getString(3));
                HttpSession ss=req.getSession();
                ss.setAttribute("name",username);
                ss.setAttribute("pass",password);
                RequestDispatcher rr=req.getRequestDispatcher("welcome");
                rr.forward(req,resp);
            }
            else {
                out.println("Wrong username and password");
                RequestDispatcher rr=req.getRequestDispatcher("index.jsp");
                rr.include(req,resp);
            }
      }catch(Exception e)
      {
        System.out.println(e);
      }
    }
}
