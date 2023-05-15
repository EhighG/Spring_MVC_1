package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 파라미터 전송 기능
 * URI : request-param?username=kang&age=26
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printAllParams(request);
        printParam(request);
        printDuplicatedParam(request);

        response.getWriter().write("ok");
    }

    private static void printAllParams(HttpServletRequest request) {
        System.out.println("[전체 파라미터 조회] -- START\n");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + ": " + request.getParameter(paramName)));

        System.out.println("\n[전체 파라미터 조회] -- END\n");
    }

    private void printParam(HttpServletRequest request) {
        System.out.println("[단일 파라미터 조회] -- START");

        String userName = request.getParameter("username"); // 중복 값이 있을 땐 사용 X! 아주 가끔 있다. / 첫번째 값만 반환
        String age = request.getParameter("age"); // POST방식에서 쿼리 파라미터 형태로 온 데이터도 접근 가능

        System.out.println("userName = " + userName);
        System.out.println("age = " + age);

        System.out.println("[단일 파라미터 조회] -- END\n");
    }

    private void printDuplicatedParam(HttpServletRequest request) {
        System.out.println("[key가 같은 파라미터 조회] -- START");

        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }

        System.out.println("[key가 같은 파라미터 조회] -- END\n");
    }
}
