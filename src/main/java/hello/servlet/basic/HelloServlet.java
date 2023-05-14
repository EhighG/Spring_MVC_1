package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override // 템플릿 메소드 패턴
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.service(req, resp);
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request); // 톰캣, 언더토우 등 WAS들이 HttpServletRequest 인터페이스를 구현한 구현체
        System.out.println("response = " + response);

        // QueryParams 읽기
        String userName = request.getParameter("username");
        System.out.println(userName);

        // 응답 쓰기
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello, " + userName);
    }
}
