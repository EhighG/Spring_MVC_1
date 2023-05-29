<%-- 외부에서 직접 호출을 막고 싶은(항상 컨트롤러를 거치게 하고 싶은) 리소스들은, WEB-INF 안에 두는 게 규칙
-> 이 안에 있는 파일들은, 외부에서 직접 호출시에도 반환되지 않도록 WAS가 처리한다.(404 not found 반환)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">
  username: <input type="text" name="username" />
  age: <input type="text" name="age" />
  <button type="submit">전송</button>
</form>
</body>
</html>