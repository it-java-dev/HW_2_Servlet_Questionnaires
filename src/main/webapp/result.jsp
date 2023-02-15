<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
  <head>
      <title>Title</title>
      <style>
        <%@include file="style.css"%>
      </style>
  </head>
  <body>
    <%
      if (request.getAttribute("questionsList")!=null){
      List<String> questionsList = (List<String>) request.getAttribute("questionsList");
      List<String> answers = (List<String>) request.getAttribute("answers");
    %>
        <div>
          <h2>Results:</h2>
          <table>
            <tr><td>Questions</td><td>Answers</td></tr>
            <%for (int i = 0; i < questionsList.size(); i++) { %>
               <tr>
                 <td> <%=(i+1) + ". " + questionsList.get(i)%> </td>
                 <td> <%=answers.get(i)%> </td>
               </tr>
              <%}%>
          </table>
        </div>
    <%}
    %>
  </body>
</html>
