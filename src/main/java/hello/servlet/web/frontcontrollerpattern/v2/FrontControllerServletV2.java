package hello.servlet.web.frontcontrollerpattern.v2;

import hello.servlet.web.frontcontrollerpattern.MyViewV2;
import hello.servlet.web.frontcontrollerpattern.v2.implementation.MemberFormControllerV2;
import hello.servlet.web.frontcontrollerpattern.v2.implementation.MemberListControllerV2;
import hello.servlet.web.frontcontrollerpattern.v2.implementation.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*") // *: All
public class FrontControllerServletV2 extends HttpServlet {

    // 매핑 정보
    private Map<String, ControllerV2> controllerMap = new HashMap<>(); // 다형성의 활용 1

    public FrontControllerServletV2() {
        String basePath = "/front-controller/v2/members";

        controllerMap.put(basePath + "/new-form", new MemberFormControllerV2());
        controllerMap.put(basePath + "/save", new MemberSaveControllerV2());
        controllerMap.put(basePath, new MemberListControllerV2());

    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        ControllerV2 controller = controllerMap.get(requestURI); // 다형성의 활용 2
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyViewV2 view = controller.process(request, response);
        // +) ctrl + alt + b(or 클릭) = 구현 클래스로 이동, ctrl + shift + b(or 클릭) = 반환 클래스로 이동
        view.render(request, response);
    }
}
