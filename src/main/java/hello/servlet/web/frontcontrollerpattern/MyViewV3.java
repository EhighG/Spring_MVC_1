package hello.servlet.web.frontcontrollerpattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MyViewV3 {
    private String viewPath;

    public MyViewV3(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // jsp는 request.getAttribute()를 사용한다. -> model의 데이터를 모두 request로 옮겨줘야 한다.

//        model.forEach((key, value) -> request.setAttribute(key ,value));
        parseModelToRequestAttribute(model, request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    private void parseModelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
        model.forEach(request::setAttribute); // 메소드 참조: 람다 표현식이 한 메소드만을 참조하는 경우, 매개변수 부분을 제거하고 사용할 수 있다.
    }
}
