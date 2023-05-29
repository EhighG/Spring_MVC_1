package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
// Controller 역할
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath); // dispatcher: 다른 서블릿이나 jsp로 이동할 때 사용됨.
        /*
        dispatcher.forward()는 "서버 내부 호출"(not redirect)
        -> 지정된 viewPath로 서버가 다시 요청 보냄. 클라이언트 측 url은 변화 없음.
         */
        dispatcher.forward(request, response); // 실제 이동(호출)

    }
}
