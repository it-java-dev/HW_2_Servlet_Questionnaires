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
      <% if (request.getAttribute("questionsList")!=null){%>
        <form action="/" method="post">
            <% List<String> questionsList = (List<String>) request.getAttribute("questionsList"); %>
            <% List<List<String>> answersList = (List<List<String>>) request.getAttribute("answersList"); %>
            <%for (int i = 0; i < questionsList.size(); i++) {%>
                <br><t2><%=i + 1 + ". " + questionsList.get(i)%></t2>

                <%for (int j = 0; j < answersList.get(i).size(); j++) {%>
                    <label for="question<%=""+i+j%>"><%=(char) ('a' + j) + ") " + answersList.get(i).get(j)%>
                        <input name="question<%=i%>" id="question<%=""+i+j%>"
                               type="radio"
                               value="<%=(char) ('a' + j) + ") " + answersList.get(i).get(j)%>"
                               checked
                        >
                    </label>
                <%}
            }%>
            <br><input type="submit"/>
        </form>
      <%}%>
    </body>
</html>