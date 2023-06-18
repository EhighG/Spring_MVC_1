package hello.servlet.web.frontcontrollerpattern.v5.adapter;

import hello.servlet.web.frontcontrollerpattern.ModelView;
import hello.servlet.web.frontcontrollerpattern.v3.ControllerV3;
import hello.servlet.web.frontcontrollerpattern.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler; // supports() 이후에 실행될 예정 -> 다른 타입인 경우는 X

        Map<String, String> paramMap = createParamMap(request);

        // 4. handler 호출
        return controller.process(paramMap);
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        // requestParams 다 꺼내서 담기
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
