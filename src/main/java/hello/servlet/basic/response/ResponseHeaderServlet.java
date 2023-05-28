package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // status-line - 응답 코드 지정
        response.setStatus(HttpServletResponse.SC_OK);

        // 헤더
        header(response);
        // 헤더 편의 메소드
        content(response);
        cookie(response);
        redirect(response);
    }

    private void header(HttpServletResponse response) throws IOException {
        // header
        response.setHeader("Content-Type", "text/plain;charset=utf-8"); // 인코딩 미지정 시 브라우저가 기본값을 설정하고, 응답 body에서 한글 깨짐
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello"); // 커스텀 헤더 가능

        PrintWriter writer = response.getWriter();
        writer.write("인코딩테스트");
//        writer.write(" string 합치기 테스트"); // StringBuilder처럼 연속 사용도 된다.
    }

    private void content(HttpServletResponse response) {
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setContentLength(35); // 생략 시 자동 생성 / 한글 하나에 3(바이트)
        // length 잘못 입력했을 때: 헤더는 해당 값이 되며, 작게 입력하면 브라우저가 그만큼만 읽고, 크게 입력하면, (응답이 다 안 온줄 알고?) timeout까지 기다린다!

    }

    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;
//        response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good"); // max-age 내에 클라이언트가 요청을 보내면, 요청의 Cookie 헤더에 해당 내용을 담는다. 이를 읽어서 사용할 수 있다.
        cookie.setMaxAge(600); // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // stat code 302
        // to: /basic/hello-form.html

        response.setStatus(HttpServletResponse.SC_FOUND);
//        response.setHeader("Location", "/basic/hello-form.html"); // 응답의 status-code가 302이면, 브라우저는 지정 location으로 요청을 보낸다. host정보의 기본값은 해당 host
//        response.setHeader("Location", "https://www.naver.com"); // 다른 호스트도 된다
        response.sendRedirect("/basic/hello-form.html");
    }
}
