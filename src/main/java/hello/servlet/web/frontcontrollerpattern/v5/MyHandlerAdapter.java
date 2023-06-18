package hello.servlet.web.frontcontrollerpattern.v5;

import hello.servlet.web.frontcontrollerpattern.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    /**
     * handler의 처리를 지원하는지 여부 반환
     * @param handler
     * @return
     */
    boolean supports(Object handler);

    /**
     * handler 호출
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws ServletException
     * @throws IOException
     */
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
