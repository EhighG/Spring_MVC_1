package hello.servlet.web.frontcontrollerpattern.v4;

import hello.servlet.web.frontcontrollerpattern.ModelView;
import hello.servlet.web.frontcontrollerpattern.MyViewV3;
import hello.servlet.web.frontcontrollerpattern.v4.implementation.MemberFormControllerV4;
import hello.servlet.web.frontcontrollerpattern.v4.implementation.MemberListControllerV4;
import hello.servlet.web.frontcontrollerpattern.v4.implementation.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // *: All
public class FrontControllerServletV4 extends HttpServlet {
    // front controller의 역할은 많아졌으나, Controller들은 매우 간단해졌고, 서블릿 종속성을 없앴다.

    // 매핑 정보
    private Map<String, ControllerV4> controllerMap = new HashMap<>(); // 다형성의 활용 1

    public FrontControllerServletV4() {
        String basePath = "/front-controller/v4/members";

        controllerMap.put(basePath + "/new-form", new MemberFormControllerV4());
        controllerMap.put(basePath + "/save", new MemberSaveControllerV4());
        controllerMap.put(basePath, new MemberListControllerV4());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV4 controller = controllerMap.get(requestURI); // 다형성의 활용 2
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 매개변수를 req, res -> paramMap으로 바꿈으로서, 컨트롤러들의 서블릿 종속성 제거
        Map<String, String> paramMap = createParamMap(request); // level을 맞춰주기 위해서?? 위와 작업단위의 크기를 맞춰준다. 디테일한 코드는 메소드로 추출해서 적당히 크게.
        Map<String, Object> model = new HashMap<>();

        // 논리이름 -> 물리적 주소로 변환
        String viewName = controller.process(paramMap, model);
        MyViewV3 view = viewResolver(viewName);

        view.render(model, request, response); // ModelView 존재 이유; 기존에 Controller -> view까지 데이터를 전달하던 request의 역할을 대체

    }

    private MyViewV3 viewResolver(String viewName) {
        return new MyViewV3("/WEB-INF/views/" + viewName + ".jsp"); // 논리 이름은 앞의 '/'도 포함하지 않으므로, 여기서 추가해줘야 함.
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        // requestParams 다 꺼내서 담기
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
