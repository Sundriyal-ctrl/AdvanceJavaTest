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

@WebServlet("/welcome")
public class Welcome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext ss = req.getServletContext();
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        resp.setIntHeader("reload",1);
        if (!session.isNew())
            try {
                Connection con = connections.ConnectionProvider.getConnection();

                String profile = (String) ss.getAttribute("profile");
                if (profile.equals("admin")) {
                    PreparedStatement p = con.prepareStatement("select *from admin");
                    ResultSet rt = p.executeQuery();
                    out.println("<table border='2'><thead><tr><th>NAME</th><th>PASSWORD</th><th>PROFILE</th><th>DELETE</th><th>UPDATE</th></thead><tbody>");
                    while (rt.next()) {
                        out.println("<tr><td>" + rt.getString(1) + "</td><td>" + rt.getString(2) + "</td><td>"
                                + rt.getString(3) + "</td><td><a href='de?name=" + rt.getString(1) + "'>Delete</a></td><td><a href='up?name=" + rt.getString(1) +"&pass="+rt.getString(2)+"&profile="+rt.getString(3)+"'>Update</a></td></tr>");
                    }
                    out.println("</tbody></table>");
                } else {

                    PreparedStatement p = con.prepareStatement("select *from admin where name=? and pass=?");
                    p.setString(1, (String) session.getAttribute("name"));
                    p.setString(2, (String) session.getAttribute("pass"));
                    ResultSet rt = p.executeQuery();
                    out.println("<table border='2'><thead><tr><th>NAME</th><th>PASSWORD</th><th>STATUS</th></thead><tbody>");
                    while (rt.next()) {
                        out.println("<tr><td>" + rt.getString(1) + "</td><td>" + rt.getString(2) + "</td><td>"
                                + rt.getString(3) + "</td><td></td></tr>");
                    }
                    out.println("</tbody></table>");
                }
            } catch (Exception e) {

            }
        out.println("<a href='welcome.jsp'>Insert User</>");
        out.println("<a href='sessionn'>logout</>");
    }
    }

