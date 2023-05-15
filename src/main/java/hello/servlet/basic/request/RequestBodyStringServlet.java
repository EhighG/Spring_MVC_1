package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // Body의 내용을 바이트코드로 읽을 수 있는 stream
        String msgBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// 문자 <-> 바이트코드 상호 변환할땐, 항상 인코딩 방식을 알려줘야 한다.

        System.out.println("msgBody = " + msgBody);
        response.getWriter().write("ok");
    }
}
