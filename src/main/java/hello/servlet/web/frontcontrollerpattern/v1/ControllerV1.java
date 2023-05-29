package hello.servlet.web.frontcontrollerpattern.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 { // 다형성 활용 - frontController에서 구현체에 의존하지 않도록

    final String VIEW_PATH_BASE = "/WEB-INF/views";

    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
