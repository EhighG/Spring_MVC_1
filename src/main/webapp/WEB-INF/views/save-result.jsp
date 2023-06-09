<%--<%@ page import="hello.servlet.domain.member.Member" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>
성공
<ul>
  <%-- 방식 1 --%>
<%--  <li>id=<%=((Member)request.getAttribute("member")).getId()%></li> &lt;%&ndash; 줄 단위 코드 표식 / 내용물을 출력&ndash;%&gt;--%>
<%--  <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>--%>
<%--  <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>
  <%-- 방식 2 - jsp가 제공하는 포맷--%>
  <li>id=${member.id}</li> <%-- 프로퍼티 접근법 - 후에 getter(member.getId())로 변환 --%>
  <li>username=${member.username}</li>
  <li>age=${member.age}</li>
</ul>
</body>
</html>