<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exception</title>
</head>
<body>
   <%
       Exception ex = (Exception) request.getAttribute("ex");
      if(ex != null){
          ex.printStackTrace(new PrintWriter(out));
      }
   %>
   <h2>Exception:<%=( ex==null ? "出错" :ex.getMessage()) %></h2>

</body>
</html>
