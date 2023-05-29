<%--
jsp의 한계점 : 페이지 하나에 로직, 렌더링 코드가 같이 담기며,
파일에 repository 등 너무 많은 부분이 노출된다.
변경 주기가 다른 관심사들은, 분리하는 게 적절하다.
--%>
<%@ page import="hello.servlet.domain.member.Member" %> <%-- import 구문 --%>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 로직(자바 코드)이 필요한 경우, 이 부분에 작성 --%>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("실행 체크: MemberSaveServlet");

    // jsp는 결국 서블릿으로 변환된다. 따라서 req, res가 예약어로 제공됨
    // 데이터 객체로 파싱 & DB에 저장
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);
%>
<%--코드 표식이 없는 부분들은, 모두 문자열로 반환해버림--%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li> <%-- 줄 단위 코드 표식 / 내용물을 출력--%>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
</body>
</html>
