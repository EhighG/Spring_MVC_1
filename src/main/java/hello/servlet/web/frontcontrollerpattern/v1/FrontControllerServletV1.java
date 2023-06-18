package hello.servlet.web.frontcontrollerpattern.v1;

import hello.servlet.web.frontcontrollerpattern.v1.implementation.MemberFormControllerV1;
import hello.servlet.web.frontcontrollerpattern.v1.implementation.MemberListControllerV1;
import hello.servlet.web.frontcontrollerpattern.v1.implementation.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*") // *: All
public class FrontControllerServletV1 extends HttpServlet {

    // 매핑 정보
    private Map<String, ControllerV1> controllerMap = new HashMap<>(); // 다형성의 활용 1

    public FrontControllerServletV1() {
        String basePath = "/front-controller/v1/members";

        controllerMap.put(basePath + "/new-form", new MemberFormControllerV1());
        controllerMap.put(basePath + "/save", new MemberSaveControllerV1());
        controllerMap.put(basePath, new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service"); // 실행 체크

        String requestURI = request.getRequestURI();

        ControllerV1 controller = controllerMap.get(requestURI); // 다형성의 활용 2
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request, response);
    }
}
