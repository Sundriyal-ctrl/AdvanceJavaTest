import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/up")
public class up extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        resp.setContentType("text/html");
        String name=req.getParameter("name");
        String pass=req.getParameter("pass");
        String pro=req.getParameter("profile");
        out.println("<h1>Update</h1>");
        out.println("<form action='update1'><input type='text' value='"+name+"' readonly='true' name='name'/><br>");
        out.println("<input type='text'  name='pass'/><br>");
        out.println("<br><input type='submit'/></form>");

        }


}
