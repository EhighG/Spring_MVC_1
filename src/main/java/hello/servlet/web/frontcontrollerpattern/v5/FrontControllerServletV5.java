package hello.servlet.web.frontcontrollerpattern.v5;

import hello.servlet.web.frontcontrollerpattern.ModelView;
import hello.servlet.web.frontcontrollerpattern.MyViewV3;
import hello.servlet.web.frontcontrollerpattern.v3.implementation.MemberFormControllerV3;
import hello.servlet.web.frontcontrollerpattern.v3.implementation.MemberListControllerV3;
import hello.servlet.web.frontcontrollerpattern.v3.implementation.MemberSaveControllerV3;
import hello.servlet.web.frontcontrollerpattern.v4.implementation.MemberFormControllerV4;
import hello.servlet.web.frontcontrollerpattern.v4.implementation.MemberListControllerV4;
import hello.servlet.web.frontcontrollerpattern.v4.implementation.MemberSaveControllerV4;
import hello.servlet.web.frontcontrollerpattern.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontrollerpattern.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private Map<String, Object> handlerMappingMap = new HashMap<>();
    private List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        // v3
        String basePathV3 = getBasePath("v3");
        handlerMappingMap.put(basePathV3 + "/new-form", new MemberFormControllerV3());
        handlerMappingMap.put(basePathV3 + "/save", new MemberSaveControllerV3());
        handlerMappingMap.put(basePathV3, new MemberListControllerV3());

        // v4
        String basePathV4 = getBasePath("v4");
        handlerMappingMap.put(basePathV4 + "/new-form", new MemberFormControllerV4());
        handlerMappingMap.put(basePathV4 + "/save", new MemberSaveControllerV4());
        handlerMappingMap.put(basePathV4, new MemberListControllerV4());
    }

    private String getBasePath(String ver) {
        return "/front-controller/v5/" + ver + "/members";
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. handler 조회
        Object handler = getHandler(request);
        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 2. handler adapter 조회
        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        // 3 ~ 5. handle(handler) ~ ModelView 반환
        ModelView mv = adapter.handle(request, response, handler);
        // 6 ~ 7. viewResolver 호출 ~ MyView 반환
        MyViewV3 view = viewResolver(mv.getViewName());
        // 8. render(model) 호출
        view.render(mv.getModel(), request, response);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("해당 handler를 찾을 수 없습니다:" + handler.getClass());
    }

    private MyViewV3 viewResolver(String viewName) {
        return new MyViewV3("/WEB-INF/views/" + viewName + ".jsp"); // 논리 이름은 앞의 '/'도 포함하지 않으므로, 여기서 추가해줘야 함.
    }
}
