<@page import="connections.ConnectionProvider"/>
<html>
<head>
</head>
<body>
<%
String name=request.getParameter("name");
try{
         Connection connection=connections.ConnectionProvider.getConnection();
         PreparedStatement p=connection.prepareStatement("delete from admin where name=?");
         p.setString(1,name);
         p.executeUpdate();
         RequestDispatcher  rt=req.getRequestDispatcher("welcome");
         rt.forward(req,resp);
}catch(Exception e)
{

}
%>
</body>
</html>